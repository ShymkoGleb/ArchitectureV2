package com.example.architecturev2.repository

import com.example.architecturev2.api.RetrofiteInstance
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.models.PostsResponse
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


class PostsRepository(
  val postsDB: PostsDB
) {
    suspend fun getPosts() = RetrofiteInstance.api.getPosts()

    suspend fun insertUserPostLocal(userId: Int, title: String, body: String) {
         println("PostsRepository -> insertUserPostLocal()")
        var testPost= PostsResponse( null,
        userId,
        title,
        body)
        postsDB.getPostDao().insertUserPost(post = testPost)
    }

    suspend fun insertUserPostFromApi(postsResponse: PostsResponse) {
        println("PostsRepository -> insertUserPostFromApi()")
        postsDB.getPostDao().insertUserPost(postsResponse)
    }
}