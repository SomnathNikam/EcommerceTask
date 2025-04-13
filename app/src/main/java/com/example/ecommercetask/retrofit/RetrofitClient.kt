package com.example.ecommercetask.retrofit

import com.example.ecommercetask.listener.ProductApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://klinq.com/"

    private val okHttpClient:OkHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

}