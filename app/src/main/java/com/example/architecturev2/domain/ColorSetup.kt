package com.example.architecturev2.domain

import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.UserRepository
import javax.inject.Inject

class ColorSetup @Inject constructor(private val postsResponse: PostsResponse)
{
    fun colorSetup():String{
        val color = "#FFFFFF"
        if (postsResponse.userId in UserRepository.ListOfIdForUserWithBann) {
            return "#FF0000"
        }
        else if (postsResponse.userId in UserRepository.ListOfIdForUserWithWarning){
            return "#FFFF00"
        }
        return color
    }
}