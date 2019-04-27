package io.github.pdrum.hw2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.data.Post
import kotlinx.android.synthetic.main.post_layout.view.*

class PostRecyclerAdapter(private val items: List<Post>, private val listener: (Post) -> Unit) :
    RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size
}

class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(post: Post, onClick: (Post) -> Unit) {
        view.post_card_title.text = post.title
        view.post_card_body.text = post.body
        view.setOnClickListener { onClick(post) }
    }
}
