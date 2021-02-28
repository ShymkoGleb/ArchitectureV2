package com.example.architecturev2.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.architecturev2.R
import com.example.architecturev2.databinding.ActivityPostsBinding.bind
import com.example.architecturev2.databinding.FragmentFirstBinding.bind
import com.example.architecturev2.databinding.FragmentSecondBinding.bind
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.UserStatus
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsReciclerAdapter : RecyclerView.Adapter<PostsReciclerAdapter.PostsViewHolder>() {

    private val items = mutableListOf<PostsResponse>()

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_posts, parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val article = items[position]
        holder.itemView.apply {
            tvUserID.text = article.userId.toString()
            tvTitle.text = article.title
            tvBody.text = article.body
            rvGeneralID

        }
    }

    fun updateAdapter(newList: List<PostsResponse>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
/*
    @SuppressLint("ResourceType")
    fun bind(model: UsersPostUIModel) {
        binding.xml = model
        if (model.status == UserStatus.NORMAL) {
            binding.tvBody.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        else if (model.status == UserStatus.WARNING) {
            binding.tvBody.setBackgroundColor(Color.parseColor("#FFD900"))
        }
        else if (model.status == UserStatus.BANNED) {
            binding.tvBody.setBackgroundColor(Color.parseColor("#FF0000"))
        }
    }*/





}
