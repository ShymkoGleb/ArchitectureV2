package com.example.architecturev2.api

import com.example.architecturev2.models.PostsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface PostsAPI {
    @GET("/posts")
   fun getPosts(): Observable<List<PostsResponse>>
}