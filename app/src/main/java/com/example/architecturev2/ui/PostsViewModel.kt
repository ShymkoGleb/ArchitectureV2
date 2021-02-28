package com.example.architecturev2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturev2.domain.CheckPostRulesUseCase
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostsViewModel /*@Inject constructor */(val postsRepository: PostsRepository) : ViewModel() {

    val _posts = MutableLiveData<List<PostsResponse>>()
    val post: LiveData<List<PostsResponse>> = _posts

    fun getPosts() = viewModelScope.launch(Dispatchers.Main) {
        val response = postsRepository.getPosts()
        _posts.setValue(response.body())
    }

    fun createPost( userId: Int,title: String, body: String): Boolean {
        return if (CheckPostRulesUseCase(title, body).invoke() && userId > 10) {
            println("PostsViewModel ->createPost() ->if")
            insertPost(
                userId = userId,
                title = title,
                body = body
            )
        println("PostsViewModel ->createPost() ->true")
        return true
            true
        } else {
            println("PostsViewModel ->createPost() ->false")
            false
        }
    }

    private fun insertPost(userId: Int, title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            println("PostsViewModel ->insertPost()")
            postsRepository.insertUserPostLocal(userId, title, body)
        }
        //getPosts()
    }
}