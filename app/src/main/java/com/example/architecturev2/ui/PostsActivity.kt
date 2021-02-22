package com.example.architecturev2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.databinding.ActivityPostsBinding
import com.example.architecturev2.repository.PostsRepository
import com.example.architecturev2.ui.fragments.FirstFragment
import com.example.architecturev2.ui.fragments.SecondFragment

class PostsActivity : AppCompatActivity() {

    lateinit var viewModel: PostsViewModel
    lateinit var postsAdapter: PostsReciclerAdapter
    lateinit var binding: ActivityPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        setupbinding()

        val newsRepository = PostsRepository(/*PostsDB(this)*/)
        val viewModelProviderFactory = PostViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PostsViewModel::class.java)
        //bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())


        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentLayout, firstFragment)
            commit()
        }

        binding.btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentLayout, firstFragment)
                commit()
            }
        }

        binding.btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentLayout, secondFragment)
                commit()
            }
        }
    }

    private fun setupbinding() {
        binding = ActivityPostsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}