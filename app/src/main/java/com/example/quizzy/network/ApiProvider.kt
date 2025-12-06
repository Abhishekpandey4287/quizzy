package com.example.quizzy.network

import com.example.quizzy.data.model.GeneralResponse
import com.example.quizzy.data.model.StudentDashboard
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val BaseClient = OkHttpClient()
    val restService: EndPoint by lazy {
        val restClient = BaseClient.newBuilder().apply {
            readTimeout(1, TimeUnit.MINUTES)
            connectTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
        }.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer your_token_here")
                .build()
            chain.proceed(request)
        }.apply {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }.build()

        Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(restClient)
            .build()
            .create(EndPoint::class.java)
    }


    suspend fun getDashboardData(): StudentDashboard {
        return withContext(Dispatchers.IO) {
            restService.getStudentDashboard()
        }
    }


    suspend fun <T> executeNetworkRequest(
        call: suspend () -> GeneralResponse<T>
    ): GeneralResponse<T> {
        return withContext(Dispatchers.IO) {
            runCatching { call.invoke() }.getOrElse { t ->
                when (t) {
                    is ConnectException -> GeneralResponse(false, 503, "No Internet Connection")
                    is UnknownHostException -> GeneralResponse(false, 400, "Connection Reset")
                    is SocketTimeoutException -> GeneralResponse(false, 408, "Timeout")
                    is HttpException -> {
                        val code = t.code()
                        GeneralResponse(false, code, "Http Error $code")
                    }
                    else -> GeneralResponse(false, -1, "Unknown Error: ${t.localizedMessage}")
                }
            }
        }
    }
}

