package com.example.productsapp.api

import com.example.productsapp.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {
    @GET("products")
    suspend fun getAllProducts() : Response<ProductsResponse>

    @GET("/products/category/jewelery")
    suspend fun getJewelery() : Response<ProductsResponse>

    @GET("/products/category/electronics")
    suspend fun getElectronics() : Response<ProductsResponse>
}