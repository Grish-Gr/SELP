package com.mter.selp

import android.app.Application
import android.content.Context
import android.util.Base64
import androidx.room.Room
import com.mter.selp.data.db.SelpDatabase
import com.mter.selp.data.network.AuthService
import com.mter.selp.data.network.TestService
import com.mter.selp.model.MoodRepository
import com.mter.selp.model.SleepRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.Date
import javax.crypto.Cipher

class AppSelp: Application() {

    lateinit var authService: AuthService
        private set
    lateinit var testingService: TestService
        private set

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(this, SelpDatabase::class.java, "selp_database")
            .build()
        MoodRepository.initRepository(database)
        SleepRepository.initDatabase(database)

        initRetrofit()
    }

    private fun initRetrofit() {
        createAuthService()

        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                var originalRequest = chain.request()

                if (!originalRequest.url.encodedPath.contains("/auth")) {
                    val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
                    val expireTime = setting.getLong("expireTime", Long.MAX_VALUE)
                    if (Date().time > expireTime) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val refreshToken = Base64.decode(setting.getString("refreshToken", "")?.toByteArray(), Base64.DEFAULT)
                            val jwtResponse = authService.refreshToken(String(refreshToken));
                            setting.edit()
                                .putLong("expireTime", jwtResponse.expireTime)
                                .putString("refreshToken", Base64.encodeToString(jwtResponse.refreshToken.toByteArray(), Base64.DEFAULT))
                                .putString("accessToken", Base64.encodeToString(jwtResponse.accessToken.toByteArray(), Base64.DEFAULT))
                                .apply()
                        }
                    }
                    originalRequest.headers.plus(Pair("Authorization", "Bearer ${setting.getString("accessToken", "") ?: ""}"))
                }

                chain.proceed(originalRequest)
            })
            .addInterceptor(httpLogging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        testingService = retrofit.create(TestService::class.java)
    }

    private fun createAuthService() {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        authService = retrofit.create(AuthService::class.java)
    }

    companion object {
        const val SETTING_INTERNET = "SETTING_INTERNET"
        const val BASE_URL = "http://192.168.0.101:8080/"
    }
}