package com.example.architecturev2.repository

import com.example.architecturev2.api.RetrofiteInstance


class PostsRepository(
 // val db: PostsDB
) {
    suspend fun getPosts() = RetrofiteInstance.api.getPosts()
}