package com.example.architecturev2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.databinding.FragmentFirstBinding
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.ui.PostsActivity
import com.example.architecturev2.ui.PostsViewModel


class FirstFragment : Fragment(R.layout.fragment_first) {

    companion object{
        private var getPostFlag:Boolean = false
    }

    lateinit var viewModel: PostsViewModel
    lateinit var postsReciclerAdapter: PostsReciclerAdapter
    lateinit var binding: FragmentFirstBinding

    val TAG = "BreakingNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PostsActivity).viewModel
        observeGitHubRepos()
        setupRecyclerView()
        viewModel.insertPostfromApi(getPostFlag)
        getPostFlag=true
    }

    private fun observeGitHubRepos() {
        viewModel.post.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            updateGitHubRepos(it)
        })
    }

    private fun setupRecyclerView() {
        postsReciclerAdapter = PostsReciclerAdapter()
        binding.rvPosts.apply {
            adapter = postsReciclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun updateGitHubRepos(items: List<PostsResponse>) {
        postsReciclerAdapter?.updateAdapter(items)
    }
}
