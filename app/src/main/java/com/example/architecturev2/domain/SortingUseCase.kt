package com.example.architecturev2.domain

import com.example.architecturev2.models.PostsResponse

class SortingUseCase (
    private val postsResponse: List<PostsResponse>?
        ){
   fun sortPostsResponse () : List<PostsResponse>? {
       return  postsResponse?.sortedByDescending {it.userId}
    }
}