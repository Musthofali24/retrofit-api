package com.musthofali.retrofitapi

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("photos")
    fun getPosts(): Call<ArrayList<PostResponse>>
}