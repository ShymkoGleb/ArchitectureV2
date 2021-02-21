package com.example.architecturev2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.architecturev2.R
import com.example.architecturev2.repository.PostsRepository

class PostsActivity : AppCompatActivity() {

        lateinit var viewModel1: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val postsRepository = PostsRepository()
        val viewModelProviderFactory = PostViewModelProviderFactory(postsRepository)
        viewModel1 = ViewModelProvider(this, viewModelProviderFactory).get(PostsViewModel::class.java)
    }
} 