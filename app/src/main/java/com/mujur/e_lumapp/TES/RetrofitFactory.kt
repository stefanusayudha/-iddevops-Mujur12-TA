package com.mujur.e_lumapp.TES

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    fun create(context: Context): API {
//        val token = Preferences.getToken(context)
        val builder = OkHttpClient().newBuilder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.connectTimeout(15, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY

//        builder.addInterceptor(logging)
//        builder.addInterceptor { chain ->
//            val request = chain.request()
//                .newBuilder()
//                .addHeader("token", token.toString()).build()
//            chain.proceed(request)
//        }

        val client = builder.build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://obscure-cove-42758.herokuapp.com/")
            .client(client)
            .build()
        return retrofit.create(API::class.java)
    }
}