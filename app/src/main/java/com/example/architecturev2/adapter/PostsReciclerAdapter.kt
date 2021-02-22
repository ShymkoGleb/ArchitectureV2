package com.example.architecturev2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturev2.R
import com.example.architecturev2.models.PostsResponse
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsReciclerAdapter : RecyclerView.Adapter<PostsReciclerAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<PostsResponse>() {
        override fun areItemsTheSame(oldItem: PostsResponse, newItem: PostsResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostsResponse, newItem: PostsResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_posts, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    //   private var onItemClickListener: ((PostsResponse) -> Unit)? = null

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            tvUserID.text = article.userId.toString()
            tvTitle.text = article.title
            tvBody.text = article.body
//            setOnClickListener {
//                onItemClickListener?.let { it(article) }
//            }
        }
    }

    /*fun setOnItemClickListener(listener: (PostsResponse) -> Unit) {
        onItemClickListener = listener
    }*/
}
