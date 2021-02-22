package com.example.architecturev2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecturev2.repository.PostsRepository
import java.nio.file.attribute.PosixFileAttributeView

class PostViewModelProviderFactory(
    val postsRepository: PostsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(postsRepository) as T
    }
}