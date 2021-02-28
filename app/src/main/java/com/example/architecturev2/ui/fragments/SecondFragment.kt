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
import com.example.architecturev2.ui.toast
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
class SecondFragment : BaseFragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: PostsViewModel
    lateinit var postsReciclerAdapter: PostsReciclerAdapter

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
        postsReciclerAdapter = PostsReciclerAdapter()

        binding.rvPosts.apply {
            adapter = postsReciclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        binding.btnAddPost.setOnClickListener {
            val id: Int = binding.etId.text.toString().toInt()
            val title:String = binding.etAddTitle.text.toString()
            val body:String = binding.etAddBody.text.toString()
            println("SecondFragment ->setupListeners()")
            createPost(id, title, body)
        }
        binding.btnGetPost.setOnClickListener {
            internalCoroutineGetPost()
        }
    }

    private fun createPost(id: Int, title: String, body: String) {
        if (viewModel.createPost(id,title, body)
        ) {
            println("SecondFragment ->createPost()-if")
        } else {
            println("SecondFragment ->createPost()-else")
            Toast.makeText(requireContext(), "Error. Please, check inputs", Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }

    private fun saveNote(postsResponse: PostsResponse) {
        class SaveNote : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                //  PostsDB.invoke(activity!!).getPostDao().insertUserPost(postsResponse)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                println("    Toast.makeText(activity,\"Finally\",Toast.LENGTH_LONG).show()")
                Toast.makeText(activity, "Finally", Toast.LENGTH_LONG).show()
            }
        }
        SaveNote().execute()
    }

    fun internalCoroutineAddPost() {
        launch {
            val postsResponse = PostsResponse(
                null,
                20,
                "internalCoroutineAddPost___Title",
                "internalCoroutineAddPost___Body"
            )
            context?.let {
                PostsDB(it).getPostDao().insertUserPost(postsResponse)
                println("YES!)1")
                it.toast("YES!)")
            }
        }
    }

    fun internalCoroutineGetPost() {
        launch {
            context?.let {
                val postsResponse = PostsDB(it).getPostDao().getAllPosts()
                postsReciclerAdapter.updateAdapter(postsResponse)
            }
        }
    }
}