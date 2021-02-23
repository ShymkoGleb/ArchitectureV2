package com.example.architecturev2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.architecturev2.models.PostsResponse


@Dao
interface PostsDao {

    @Query("SELECT * FROM post_response_table")
    fun getAllPosts(): List<PostsResponse>

    @Insert
    fun insertPosts(posts: List<PostsResponse>)

    @Insert
    fun insertPost(post: PostsResponse)
}
