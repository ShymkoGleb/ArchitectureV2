package com.example.architecturev2.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity (tableName = "post_response_table")

data class PostsResponse (
  //  @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int? = null,
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("body") val body: String? = null
)