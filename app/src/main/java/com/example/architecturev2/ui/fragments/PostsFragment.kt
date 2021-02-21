package com.example.architecturev2.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.ui.PostsActivity
import com.example.architecturev2.ui.PostsViewModel
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : Fragment(R.layout.fragment_posts) {

    lateinit var viewModel: PostsViewModel
    lateinit var postsAdapter: PostsReciclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PostsActivity).viewModel1
        setupRecyclerView()


        (viewModel as PostsViewModel).posts.observe(viewLifecycleOwner, Observer { response ->
            postsAdapter.differ.submitList(listOf(PostsResponse()))
        })
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsReciclerAdapter()
        rvPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}


/*    post.observe(viewLifecycleOwner, Observer { response ->
        when(response) {
            is Resource.Success -> {
                hideProgressBar()
                response.data?.let { newsResponse ->
                    newsAdapter.differ.submitList(newsResponse.articles)
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                response.message?.let { message ->
                    Log.e(TAG, "An error occured: $message")
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
    })*/
