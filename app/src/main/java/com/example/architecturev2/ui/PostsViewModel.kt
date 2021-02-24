package com.example.architecturev2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturev2.domain.CheckPostRulesUseCase
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel /*@Inject constructor */(val postsRepository: PostsRepository) : ViewModel() {

    val _posts = MutableLiveData<List<PostsResponse>>()
    val post: LiveData<List<PostsResponse>> = _posts
    // var breakingNewsPage = 1

    /* init {
        getPosts()
    }*/

    fun getPosts() = viewModelScope.launch(Dispatchers.Main) {
        // posts.postValue(Resource.Loading())
        val response = postsRepository.getPosts()
        _posts.setValue(response.body())
    }

  /*  fun createPost(title: String, body: String, userId: Int): Boolean {
        return if (CheckPostRulesUseCase(title, body).invoke() && userId > 0) {
            insertPost(
                userId = userId,
                title = title,
                body = body
            )
            true
        } else {
            false
        }
    }*/

 /*   private fun insertPost(userId: Int, title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            postsRepository.insertUserPostLocal(userId, title, body)
        }
        getPosts()
    }*/
}