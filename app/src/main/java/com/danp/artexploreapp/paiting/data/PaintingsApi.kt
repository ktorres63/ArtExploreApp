package com.danp.artexploreapp.paiting.data

import com.danp.artexploreapp.paiting.domain.Painting
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface PaintingsApi {
    @GET("items")
    suspend fun getPaintings(): List<Painting>
}

object RetrofitInstance {
    private const val BASE_URL = "https://pa34fd8qcb.execute-api.us-east-2.amazonaws.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: PaintingsApi = retrofit.create(PaintingsApi::class.java)
}
