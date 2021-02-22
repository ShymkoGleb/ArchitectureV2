package com.example.architecturev2.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturev2.models.PostsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PostsAPI {
    @GET("/posts")
   suspend fun getPosts(): Response<List<PostsResponse>>
}