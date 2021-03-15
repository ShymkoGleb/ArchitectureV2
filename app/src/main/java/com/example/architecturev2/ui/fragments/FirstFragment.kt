package com.example.architecturev2.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturev2.R
import com.example.architecturev2.adapter.PostsReciclerAdapter
import com.example.architecturev2.databinding.FragmentFirstBinding
import com.example.architecturev2.di.AppModule
import com.example.architecturev2.di.DaggerAppComponent
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import com.example.architecturev2.ui.PostsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class FirstFragment : Fragment(R.layout.fragment_first) {

    @Inject
    lateinit var postsRepository: PostsRepository

    @Inject
    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var postsReciclerAdapter: PostsReciclerAdapter
    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("LOGD", "FirstFragment -> onCreateView()")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDagger()
        setupRecyclerView()
        Log.d("LOGD", "FirstFragment -> onViewCreated()")
        observeRepos()
        RXJAVA()
    }


    private fun RXJAVA() {
        val compositeDisposable = CompositeDisposable()
        Log.d("LOGD", "FirstFragment -> RXJAVA()")
        compositeDisposable.add(
            postsRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
//                    if (postsRepository.postsDB.getPostDao().getAllPosts().isEmpty()) {
//                        println("RXJAVA -> if")
                    Log.d("LOGD", "FirstFragment -> RXJAVA() -> result")
                    // reposCheck()
                    onResponse(result)
//                    } else {
//                        println("RXJAVA -> else")
//                    }
                }, { t ->
                    Log.d("LOGD", "FirstFragment -> onFailure")
                    onFailure(t)
                })
        )
    }


    private fun reposCheck() {
        var check = postsRepository.postsDB.getPostDao().getAllPosts().isEmpty()
        Log.d("LOGD", "FirstFragment -> reposCheck() $check")
    }


    private fun onFailure(t: Throwable) {
        Log.d("LOGD", "FirstFragment -> onFailure()")
    }



    //    fun saveDataToLocal(listOfPosts: List<PostsResponse>?) {
//        listOfPosts?.forEach { postsResponse ->
//            insertUserPostFromApi(
//                PostsResponse(
//                    title = postsResponse.title,
//                    body = postsResponse.body,
//                    userId = postsResponse.userId
//                )
//            )
//        }
//
//    }
//
//    //
    fun insertUserPostFromApi(postsResponse: PostsResponse) {
        Log.d("LOGD", "FirstFragment -> insertUserPostFromApi()")
        postsRepository.insertUserPostFromApi(postsResponse)
    }

    //
//
    private fun observeRepos() {
        Log.d("LOGD", "FirstFragment -> observeRepos()")
        viewModel.post.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Log.d("LOGD", "FirstFragment -> observeRepos()")
            updateRepos(it)
        })
    }

    private fun setupRecyclerView() {
        Log.d("LOGD", "FirstFragment -> setupRecyclerView()")
        postsReciclerAdapter = PostsReciclerAdapter()
        binding.rvPosts.apply {
            adapter = postsReciclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun updateRepos(items: List<PostsResponse>) {
        Log.d("LOGD", "FirstFragment -> updateRepos() __ Shows response in UI ")
        postsReciclerAdapter?.updateAdapter(items)
    }

    fun setupDagger() {
        Log.d("LOGD", "FirstFragment -> setupDagger()")
        DaggerAppComponent
            .builder()
            .appModule(AppModule(requireContext()))
            .build()
            .injectFragment1(this)
    }
}