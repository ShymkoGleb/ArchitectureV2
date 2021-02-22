package com.example.architecturev2.repository

import com.example.architecturev2.api.RetrofiteInstance
import retrofit2.awaitResponse


class PostsRepository(
 // val db: PostsDB
) {
    suspend fun getPosts() = RetrofiteInstance.api.getPosts()

}