package com.example.architecturev2.ui.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.databinding.FragmentSecondBinding
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.ui.PostsActivity
import com.example.architecturev2.ui.PostsViewModel
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class SecondFragment : BaseFragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_second, container, false)
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as PostsActivity).viewModel
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAddPost.setOnClickListener {
            val id: Int = binding.etId.text.toString().toInt()
            val title:String = binding.etAddTitle.text.toString()
            val body:String = binding.etAddBody.text.toString()
            createPost(id, title, body)
        }
    }

    private fun createPost(id: Int, title: String, body: String) {
        if (viewModel.createPost(id,title, body)
        ) {
            Toast.makeText(requireContext(), "Post created", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Error. Please, check inputs", Toast.LENGTH_SHORT)
                .show()
        }
    }
}