package com.example.ecommercetask.listener

import com.example.ecommercetask.model.ProductResponse
import com.example.ecommercetask.retrofit.APIEndPointConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {
    @GET(APIEndPointConstants.apiEndPoint)
    suspend fun getProductDetails(
        @Path("productId") productId: String,
        @Path("customerId") customerId: String,
        @Query("lang") language: String,
        @Query("store") store: String
    ): Response<ProductResponse>
}