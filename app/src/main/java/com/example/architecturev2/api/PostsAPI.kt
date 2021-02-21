package com.example.architecturev2.api

import com.example.architecturev2.models.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostsAPI {
    @GET("/posts")
    suspend fun getPosts(): Response<PostsResponse>
}