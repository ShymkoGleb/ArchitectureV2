package com.example.architecturev2.repository

import com.example.architecturev2.api.RetrofiteInstance
import com.example.architecturev2.db.PostsDB
import retrofit2.awaitResponse


class PostsRepository/* @Inject constructor*/(
  //val db: PostsDB
) {
    suspend fun getPosts() = RetrofiteInstance.api.getPosts()

}