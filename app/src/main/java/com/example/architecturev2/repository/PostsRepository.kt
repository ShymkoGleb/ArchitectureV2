package com.example.architecturev2.repository

import com.example.architecturev2.api.RetrofiteInstance
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.models.PostsResponse
import io.reactivex.Observable
import javax.inject.Inject


class PostsRepository @Inject constructor(
    val postsDB: PostsDB
) {

    fun getPosts() = RetrofiteInstance.api.getPosts()

//    fun insertUserPostLocal(userId: Int, title: String, body: String) {
//        println("PostsRepository -> insertUserPostLocal()")
//        var testPost = PostsResponse(
//            null,
//            userId,
//            title,
//            body
//        )
//        postsDB.getPostDao().insertUserPost(post = testPost)
//    }

//    fun insertUserPostFromApi(postsResponse: Observable<PostsResponse>) {
//        println("PostsRepository -> insertUserPostFromApi()")
//        postsDB.getPostDao().insertUserPost(postsResponse)
//    }
//
//    suspend fun getPostsFromDB()=  postsDB.getPostDao().getAllPosts()
}