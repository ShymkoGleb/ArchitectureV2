package com.example.architecturev2.ui

import android.content.Context
import android.view.View
import android.widget.Toast
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
import javax.inject.Inject

class PostsViewModel @Inject constructor(val postsRepository: PostsRepository) : ViewModel() {

    val _posts = MutableLiveData<List<PostsResponse>>()
    val post: LiveData<List<PostsResponse>> = _posts

    fun createPost(userId: Int, title: String, body: String): Boolean {
        return if (ValidationPost(userId, title, body).invoke()) {
            println("PostsViewModel ->createPost() ->if")
//            insertPost(
//                userId = userId,
//                title = title,
//                body = body
//            )
            println("PostsViewModel ->createPost() ->true")
            return true
            true
        } else {
            println("PostsViewModel ->createPost() ->false")
            false
        }
    }

//    fun insertPost(userId: Int, title: String, body: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            println("PostsViewModel ->insertPost()")
//            postsRepository.insertUserPostLocal(userId, title, body)
//            val postsResponseFromDB = postsRepository.getPostsFromDB()
//            _posts.postValue(postsResponseFromDB)
//        }
//    }

//    fun insertPostfromApi() {
////        viewModelScope.launch(Dispatchers.IO) {
////            if (postsRepository.postsDB.getPostDao().getAllPosts().isEmpty()) {
//        val compositeDisposable = CompositeDisposable()
//        compositeDisposable?.add(
//            postsRepository.getPosts()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
//        )
//

//                val response = postsRepository.getPosts()
//                println("PostsViewModel ->insertPost()" + response.body())
//                val sortedPostsResponse = SortingUseCase(response.body()).sortPostsResponse()
//                saveDataToLocal(sortedPostsResponse)
//            } else {
//                val postsResponseFromDB = postsRepository.getPostsFromDB()
//                _posts.postValue(postsResponseFromDB)
    }

//}

//
//fun saveDataToLocal(listOfPosts: List<PostsResponse>?) {
//    listOfPosts?.forEach { postsResponse ->
//        insertUserPostFromApi(
//            PostsResponse(
//                title = postsResponse.title,
//                body = postsResponse.body,
//                userId = postsResponse.userId
//            )
//        )
//    }
//    val postsResponseFromDB = postsRepository.getPostsFromDB()
//    _posts.postValue(postsResponseFromDB)
//}
//
//fun insertUserPostFromApi(postsResponse: PostsResponse) {
//    postsRepository.insertUserPostFromApi(postsResponse)
//}



