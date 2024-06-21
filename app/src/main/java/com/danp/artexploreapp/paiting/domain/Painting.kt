package com.danp.artexploreapp.paiting.domain

import com.google.gson.annotations.SerializedName

data class Painting(
    val id: Int,
    val title: String,
    val category: String,
    @SerializedName("image_url") val imageURL: String,
    val major: Int,
    val technique: String,
)
