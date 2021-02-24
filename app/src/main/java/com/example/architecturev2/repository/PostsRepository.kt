package com.example.architecturev2.repository

import com.example.architecturev2.api.RetrofiteInstance
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.models.PostsResponse
import retrofit2.awaitResponse


class PostsRepository/* @Inject constructor*/(
 // val postsDB: PostsDB
) {
    suspend fun getPosts() = RetrofiteInstance.api.getPosts()

/*    fun insertUserPostLocal(userId: Int, title: String, body: String) {
        postsDB.getPostDao().insertPost(
            PostsResponse(
                id = postsDB.getPostDao().getMinLocalUserPostId() - 1,
                userId = userId,
                title = title,
                body = body
            )
        )
    }*/

}