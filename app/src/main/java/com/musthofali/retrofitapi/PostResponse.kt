package com.musthofali.retrofitapi

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("albumId")
    val albumId: Int?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)
