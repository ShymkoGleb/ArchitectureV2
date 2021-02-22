package com.example.architecturev2.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import com.example.architecturev2.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(val postsRepository: PostsRepository) : ViewModel() {

    val posts: MutableLiveData<List<PostsResponse>> = MutableLiveData()
    // var breakingNewsPage = 1

    init {
        getPosts()
    }

    fun getPosts() = GlobalScope.launch(Dispatchers.IO) {
        // posts.postValue(Resource.Loading())
        val response = postsRepository.getPosts()
        posts.postValue(response.body())
    }

   /* private fun handleBreakingNewsResponse(response: Response<PostsResponse>) : Resource<PostsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }*/
}
