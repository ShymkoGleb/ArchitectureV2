package com.example.architecturev2.domain

import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.UserRepository

class ColorSetup(
   private val postsResponse: PostsResponse)
{
    private fun colorSetup(){
        if (postsResponse.userId in UserRepository.ListOfIdForUserWithBann){

        }
    }
}