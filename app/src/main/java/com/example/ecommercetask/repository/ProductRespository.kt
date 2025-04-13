package com.example.ecommercetask.repository

import com.example.ecommercetask.model.ProductResponse
import com.example.ecommercetask.retrofit.RetrofitClient
import retrofit2.Response

class ProductRepository {
    suspend fun getProductDetails(
        productId: String,
        customerId: String,
        lang: String,
        store: String
    ): Response<ProductResponse> {
        return RetrofitClient.apiService.getProductDetails(productId, customerId, lang, store)
    }
}

