package com.example.architecturev2.ui

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.domain.SortingUseCase
import com.example.architecturev2.domain.ValidationPost
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel /*@Inject constructor */(val postsRepository: PostsRepository) : ViewModel() {

    val _posts = MutableLiveData<List<PostsResponse>>()
    val post: LiveData<List<PostsResponse>> = _posts

    /* fun getPosts() = viewModelScope.launch(Dispatchers.Main) {
         val response = postsRepository.getPosts()
         _posts.setValue(response.body())
     }*/

    fun createPost(userId: Int, title: String, body: String): Boolean {
        return if (ValidationPost(userId, title, body).invoke()/* && userId > 10*/) {
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

    fun insertPost(userId: Int, title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            println("PostsViewModel ->insertPost()")
            postsRepository.insertUserPostLocal(userId, title, body)
            val postsResponseFromDB = postsRepository.getPostsFromDB()
            _posts.postValue(postsResponseFromDB)
        }
    }

    fun insertPostfromApi(getPostFlag: Boolean) {

        viewModelScope.launch(Dispatchers.IO) {
            if (postsRepository.postsDB.getPostDao().getAllPosts().isEmpty()) {
                val response = postsRepository.getPosts()
                //  _posts.postValue(response.body())
                println("PostsViewModel ->insertPost()" + response.body())
                val sortedPostsResponse = SortingUseCase(response.body()).sortPostsResponse()
                saveDataToLocal(sortedPostsResponse)
            } else {
                val postsResponseFromDB = postsRepository.getPostsFromDB()
                _posts.postValue(postsResponseFromDB)
            }
        }
    }

    suspend fun saveDataToLocal(listOfPosts: List<PostsResponse>?) {
        listOfPosts?.forEach { postsResponse ->
            insertUserPostFromApi(
                PostsResponse(
                    title = postsResponse.title,
                    body = postsResponse.body,
                    userId = postsResponse.userId
                )
            )
        }
        val postsResponseFromDB = postsRepository.getPostsFromDB()
        _posts.postValue(postsResponseFromDB)
    }

    private suspend fun insertUserPostFromApi(postsResponse: PostsResponse) {
        postsRepository.insertUserPostFromApi(postsResponse)
    }
}


