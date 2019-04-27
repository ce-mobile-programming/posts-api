package io.github.pdrum.hw2.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.data.Comment
import kotlinx.android.synthetic.main.comment_layout.view.*

class CommentRecyclerAdapter(private val items: List<Comment>) :
    RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.comment_layout, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

class CommentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(comment: Comment) {
        view.comment_card_email.text = comment.email
        view.comment_card_name.text = comment.name
        view.comment_card_body.text = comment.body
    }
}
