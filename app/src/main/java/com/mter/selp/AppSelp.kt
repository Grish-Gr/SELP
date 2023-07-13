package com.mter.selp

import android.app.Application
import android.content.Context
import android.util.Base64
import androidx.room.Room
import com.mter.selp.data.db.SelpDatabase
import com.mter.selp.data.network.AuthRestService
import com.mter.selp.data.network.UserRestService
import com.mter.selp.data.network.response.JwtResponse
import com.mter.selp.model.AuthService
import com.mter.selp.model.MoodRepository
import com.mter.selp.model.SleepRepository
import com.mter.selp.model.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

class AppSelp: Application() {

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(this, SelpDatabase::class.java, "selp_database")
            .build()
        MoodRepository.initRepository(database)
        SleepRepository.initDatabase(database)

        initRetrofit()
    }

    private fun initRetrofit() {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()

                if (!originalRequest.url.encodedPath.contains("/auth")) {
                    val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
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

        val authRestService = retrofit.create(AuthRestService::class.java)
        val userRestService = retrofit.create(UserRestService::class.java)

        AuthService.init(
            authRestService = authRestService,
            successLogin = {
                refreshToken(it)
            },
            getRefreshToken = {
                getRefreshToken()
            },
            successRefreshTokens = {
                refreshToken(it)
            }
        )
        UserService.init(
            userRestService = userRestService,
            checkRefreshToken = {
                checkNeedRefreshTokens()
            }
        )
    }

    private fun checkNeedRefreshTokens(): Boolean {
        val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
        val expireTime = setting.getLong("expireTime", Long.MAX_VALUE)
        return Date().time > expireTime
    }

    private fun refreshToken(jwtResponse: JwtResponse) {
        val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
        setting.edit()
            .putLong("expireTime", jwtResponse.expireTime)
            .putString("refreshToken", Base64.encodeToString(jwtResponse.refreshToken.toByteArray(), Base64.DEFAULT))
            .putString("accessToken", Base64.encodeToString(jwtResponse.accessToken.toByteArray(), Base64.DEFAULT))
            .apply()
    }

    private fun getRefreshToken(): String {
        val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
        return String(Base64.decode(setting.getString("refreshToken", "")?.toByteArray(), Base64.DEFAULT))
    }

    companion object {
        const val SETTING_INTERNET = "SETTING_INTERNET"
        const val BASE_URL = "http://192.168.0.101:8080/"
    }
}