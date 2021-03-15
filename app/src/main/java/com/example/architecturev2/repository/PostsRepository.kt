package com.example.architecturev2.repository

import android.util.Log
import com.example.architecturev2.api.RetrofiteInstance
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.models.PostsResponse
import io.reactivex.Observable
import javax.inject.Inject


class PostsRepository @Inject constructor(
    val postsDB: PostsDB
) {

    fun getPosts() = RetrofiteInstance.api.getPosts()

    fun insertUserPostLocal(userId: Int?, title: String?, body: String?) {
        Log.d("LOGD", "PostsRepository -> insertUserPostLocal()")
        println("")
        var testPost = PostsResponse(
            null,
            userId,
            title,
            body
        )
        postsDB.getPostDao().insertUserPost(post = testPost)
    }

    fun insertUserPostFromApi(postsResponse: PostsResponse) {
        Log.d("LOGD", "PostsRepository -> insertUserPostFromApi()")
        postsDB.getPostDao().insertUserPost(postsResponse)
    }

     fun getPostsFromDB():List<PostsResponse>{
         Log.d("LOGD", "PostsRepository -> getPostsFromDB()")
         return postsDB.getPostDao().getAllPosts()
     }
}