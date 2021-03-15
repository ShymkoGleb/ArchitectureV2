package com.example.architecturev2.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.databinding.ActivityPostsBinding
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.di.AppModule
import com.example.architecturev2.di.DaggerAppComponent
import com.example.architecturev2.repository.PostsRepository
import com.example.architecturev2.ui.fragments.FirstFragment
import com.example.architecturev2.ui.fragments.SecondFragment
import javax.inject.Inject

@Suppress("DEPRECATION")
class PostsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var viewModelProviderFactory: PostViewModelProviderFactory

    @Inject
    lateinit var newsRepository: PostsRepository
    lateinit var binding: ActivityPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        Log.d("LOGD", "PostsActivity -> onCreate()")
        setupDagger()
        setupbinding()
        setupListener()
    }

    private fun setupDagger() {
        Log.d("LOGD", "PostsActivity -> setupDagger()")
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

    private fun setupListener() {
        Log.d("LOGD", "PostsActivity -> setupListener()")
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction().apply {
            Log.d("LOGD", "PostsActivity -> setupListener() -> supportFragmentManager.beginTransaction()")
            replace(R.id.flFragmentLayout, firstFragment)
            commit()
        }

        binding.btnFragment1.setOnClickListener {
            Log.d("LOGD", "PostsActivity -> setupListener() -> btnFragment1.setOnClickListener")
            supportFragmentManager.beginTransaction().apply {
                Log.d("LOGD", "PostsActivity -> setupListener() -> btnFragment1.setOnClickListener -> supportFragmentManager.beginTransaction()")
                replace(R.id.flFragmentLayout, firstFragment)
                commit()
            }
        }

        binding.btnFragment2.setOnClickListener {
            Log.d("LOGD", "PostsActivity -> setupListener() -> btnFragment2.setOnClickListener")
            supportFragmentManager.beginTransaction().apply {
                Log.d("LOGD", "PostsActivity -> setupListener() -> btnFragment2.setOnClickListener -> supportFragmentManager.beginTransaction()")
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