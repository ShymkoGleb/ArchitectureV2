package com.example.architecturev2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.*
import com.example.architecturev2.models.PostsResponse
import io.reactivex.Observable


@Dao
interface PostsDao {
//    @Query("SELECT * FROM post_response_table ORDER BY idKey DESC")
//    fun getAllPosts(): Observable<List<PostsResponse>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUserPost(post: PostsResponse)
}
