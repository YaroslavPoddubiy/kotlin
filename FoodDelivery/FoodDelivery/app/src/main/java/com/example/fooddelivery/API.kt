package com.example.fooddelivery

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Restaurant(
    val id: Int,
    val name: String,
    val city: String,
    val address: String,
    val imageUrl: String
)

data class Item(
    val id: Int,
    val name: String,
    val ingredients: String,
    val price: Double,
    val restaurantId: Int,
    val imageUrl: String
)

data class ApiResponse(val message: String)

interface FastApiService {
    @GET("/restaurants/{city}")
    suspend fun getRestaurants(@Path("city") city: String): List<Restaurant>

    @GET("/restaurants/")
    suspend fun getAllRestaurants(): List<Restaurant>

    @GET("/menu/{restaurant_id}")
    suspend fun getItems(@Path("restaurant_id") id: Int): List<Item>

    @GET("/menu/")
    suspend fun getAllItems(): List<Item>

    @GET("/item/{id}")
    suspend fun getItemById(@Path("id") id: Int): Item

    @GET("/hello/")
    suspend fun helloWorld(): ApiResponse
}

object FastApiClient {
    const val BASE_URL = "http://192.168.0.108:8000"

    val apiService: FastApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FastApiService::class.java)
    }
}
