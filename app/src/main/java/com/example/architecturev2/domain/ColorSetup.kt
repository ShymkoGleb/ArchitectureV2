package com.example.architecturev2.domain

import android.util.Log
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.UserRepository
import javax.inject.Inject

class ColorSetup @Inject constructor(private val postsResponse: PostsResponse)
{
    fun colorSetup():String{
        Log.d("LOGD", "ColorSetup ->colorSetup()")
        val color = "#FFFFFF"
        if (postsResponse.userId in UserRepository.ListOfIdForUserWithBann) {
            Log.d("LOGD", "ColorSetup ->colorSetup() ->if()")
            return "#FF0000"
        }
        else if (postsResponse.userId in UserRepository.ListOfIdForUserWithWarning){
            Log.d("LOGD", "ColorSetup ->colorSetup() ->else if()")
            return "#FFFF00"
        }
        return color
    }
}