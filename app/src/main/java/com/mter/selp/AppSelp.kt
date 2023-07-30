package com.mter.selp

import android.app.Application
import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.room.Room
import com.google.gson.Gson
import com.mter.selp.data.db.SelpDatabase
import com.mter.selp.data.network.AuthRestService
import com.mter.selp.data.network.UserRestService
import com.mter.selp.data.network.response.JwtResponse
import com.mter.selp.model.AuthService
import com.mter.selp.model.MoodRepository
import com.mter.selp.model.ResultTestRepository
import com.mter.selp.model.SleepRepository
import com.mter.selp.model.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.Date


class AppSelp: Application() {

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(this, SelpDatabase::class.java, "selp_database")
            .build()
        MoodRepository.initRepository(database)
        SleepRepository.initDatabase(database)
        ResultTestRepository.initDatabase(database)
        FailedRequestToServerRepository.initRepository(database)

        initRetrofit()
    }

    private fun initRetrofit() {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()

                if (!originalRequest.url.encodedPath.contains("/auth")) {
                    val request = originalRequest.newBuilder()
                    val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)

                    if (Date().time > setting.getLong("expireTime", Long.MIN_VALUE)) {
                        Log.i("REFRESH_TOKENS", "Start refresh tokens for user")
                        val refreshRequest: Request = Request.Builder()
                            .url("${BASE_URL}api/v1/auth/refresh?token=${getRefreshToken()}")
                            .get()
                            .build()

                        val response = OkHttpClient().newCall(refreshRequest).execute()
                        if (!response.isSuccessful) {
                            throw IOException("Запрос к серверу не был успешен: ${response.code} ${response.message}")
                        } else {
                            Log.i("REFRESH_TOKENS", "Success refresh tokens for user")
                        }

                        val tokens = Gson().fromJson(response.body?.string(), JwtResponse::class.java)
                        setting.edit()
                            .putLong("expireTime", tokens.expireTime)
                            .putString("refreshToken", Base64.encodeToString(tokens.refreshToken.toByteArray(), Base64.DEFAULT))
                            .putString("accessToken", Base64.encodeToString(tokens.accessToken.toByteArray(), Base64.DEFAULT))
                            .putString("paidSubscriptionCode", Base64.encodeToString(tokens.paidSubscriptionCode.toByteArray(), Base64.DEFAULT))
                            .putString("role", Base64.encodeToString(tokens.role.toByteArray(), Base64.DEFAULT))
                            .apply()
                    }

                    request.addHeader("Authorization", "Bearer ${String(Base64.decode(setting.getString("accessToken", "")?.toByteArray(), Base64.DEFAULT))}")
                    chain.proceed(request.build())
                } else {
                    chain.proceed(originalRequest)
                }
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
            }
        )
        UserService.init(userRestService = userRestService)
    }

    private fun refreshToken(jwtResponse: JwtResponse) {
        val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
        setting.edit()
            .putLong("expireTime", jwtResponse.expireTime)
            .putString("refreshToken", Base64.encodeToString(jwtResponse.refreshToken.toByteArray(), Base64.DEFAULT))
            .putString("accessToken", Base64.encodeToString(jwtResponse.accessToken.toByteArray(), Base64.DEFAULT))
            .putString("paidSubscriptionCode", Base64.encodeToString(jwtResponse.paidSubscriptionCode.toByteArray(), Base64.DEFAULT))
            .putString("role", Base64.encodeToString(jwtResponse.role.toByteArray(), Base64.DEFAULT))
            .apply()
    }

    private fun getRefreshToken(): String {
        val setting = this.applicationContext.getSharedPreferences(SETTING_INTERNET, Context.MODE_PRIVATE)
        return String(Base64.decode(setting.getString("refreshToken", "")?.toByteArray(), Base64.DEFAULT))
    }

    companion object {
        const val SETTING_INTERNET = "SETTING_INTERNET"
        // Чтобы протестировать бэкенд: запускаете SELP_back,
        // узнаете ip адрес компьютера (ipconfig в терминале)
        // указываете ip адрес и порт (по умолчанию 8080)
        // Для установки также нужно заменить ip адрес в MainActivity.kt
        const val BASE_URL = "http://192.168.1.102:8080/"
    }
}