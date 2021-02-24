package com.example.architecturev2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*
import com.example.architecturev2.models.PostsResponse


@Dao
interface PostsDao {

    @Query("SELECT * FROM post_response_table")
    fun getAllPosts(): List<PostsResponse>

    @Insert
    fun insertPosts(posts: List<PostsResponse>)

    @Insert
    fun insertPost(post: PostsResponse)

    @Query("SELECT id FROM post_response_table WHERE id ORDER BY id ASC LIMIT 1")
    fun getMinLocalUserPostId(): Int
}
