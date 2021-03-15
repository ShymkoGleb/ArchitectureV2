package com.example.architecturev2.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturev2.R
import com.example.architecturev2.domain.ColorSetup
import com.example.architecturev2.models.PostsResponse
import kotlinx.android.synthetic.main.item_posts.view.*
import javax.inject.Inject

class PostsReciclerAdapter @Inject constructor() : RecyclerView.Adapter<PostsReciclerAdapter.PostsViewHolder>() {

    private val items = mutableListOf<PostsResponse>()

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        Log.d("LOGD", "PostsReciclerAdapter -> onCreateViewHolder()")
        return PostsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_posts, parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        Log.d("LOGD", "PostsReciclerAdapter -> onBindViewHolder()")
        val article = items[position]
        holder.itemView.apply {
            tvUserID.text = article.userId.toString()
            tvTitle.text = article.title
            tvBody.text = article.body
            val color = ColorSetup(article)
            rvGeneralID.setBackgroundColor(Color.parseColor(color.colorSetup()))
        }
    }

    fun updateAdapter(postsResponse: List<PostsResponse>) {
        Log.d("LOGD", "PostsReciclerAdapter -> updateAdapter()")
        items.clear()
        items.addAll(postsResponse)
        notifyDataSetChanged()
    }
}
