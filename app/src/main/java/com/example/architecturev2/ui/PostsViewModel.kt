package com.example.architecturev2.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(
    val postsRepository: PostsRepository
): ViewModel() {

    val posts: MutableLiveData<Response<PostsResponse>> = MutableLiveData()
  //  val articleTest: List<PostsResponse>

    init {
      //  getPost()
    }

//    private fun getPost() = viewModelScope.launch {
//        var response = postsRepository.getPosts()
//        posts.postValue(response)
//   }
}