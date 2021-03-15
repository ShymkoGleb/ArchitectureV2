package com.example.architecturev2.ui.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.databinding.FragmentSecondBinding
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.di.AppModule
import com.example.architecturev2.di.DaggerAppComponent
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import com.example.architecturev2.ui.PostViewModelProviderFactory
import com.example.architecturev2.ui.PostsActivity
import com.example.architecturev2.ui.PostsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@Suppress("DEPRECATION")
class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var binding: FragmentSecondBinding
    @Inject
    lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        Log.d("LOGD", "SecondFragment -> onCreateView()")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LOGD", "SecondFragment -> onViewCreated()")
        setupDagger()
        setupListeners()
    }

    fun setupDagger() {
        Log.d("LOGD", "SecondFragment -> setupDagger()")
        DaggerAppComponent
            .builder()
            .appModule(AppModule(requireContext()))
            .build()
            .injectFragment2(this)
    }

    private fun setupListeners() {
        Log.d("LOGD", "SecondFragment -> setupListeners()")
        binding.btnAddPost.setOnClickListener {
            Log.d("LOGD", "SecondFragment -> setupListeners() -> btnAddPost.setOnClickListener()")
            val id: String? = binding.etId.text.toString()
            val title: String? = binding.etAddTitle.text.toString()
            val body: String? = binding.etAddBody.text.toString()
            if (id.isNullOrEmpty() || title.isNullOrEmpty() || body.isNullOrEmpty()){
                Log.d("LOGD", "SecondFragment -> setupListeners() ->  if (id.isNullOrEmpty() || title.isNullOrEmpty() || body.isNullOrEmpty())")
            }else{
            val idInt: Int? = id.toInt()
                id.toString()
                createPost(idInt, title, body)
            }
        }
    }

    private fun createPost(id: Int?, title: String?, body: String?) {
        Log.d("LOGD", "SecondFragment -> createPost()")
        if (viewModel.createPost(id, title, body)
        ) {
            Log.d("LOGD", "SecondFragment -> createPost() ->if()")
            Toast.makeText(requireContext(), "Post created", Toast.LENGTH_SHORT)
                .show()
        } else {
            Log.d("LOGD", "SecondFragment -> createPost() ->else()")
            Toast.makeText(requireContext(), "Error. Please, check inputs", Toast.LENGTH_SHORT)
                .show()
        }
    }
}