package com.danp.artexploreapp.paiting.domain

import com.google.gson.annotations.SerializedName

data class Painting(
    @SerializedName("id")val id: Int,
    @SerializedName("title")val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("description")val description: String,
    @SerializedName("technique")val technique: String,
    @SerializedName("category") val category: String,
    @SerializedName("image_url") val imageURL: String,
    @SerializedName("major") val major: Int,
)
