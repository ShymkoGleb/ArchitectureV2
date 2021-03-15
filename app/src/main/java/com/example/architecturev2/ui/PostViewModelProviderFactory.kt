package com.example.architecturev2.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecturev2.repository.PostsRepository
import java.nio.file.attribute.PosixFileAttributeView
import javax.inject.Inject

class PostViewModelProviderFactory @Inject constructor(
    val postsRepository: PostsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.d("LOGD", "PostViewModelProviderFactory -> create()")
        return PostsViewModel(postsRepository) as T
    }
}