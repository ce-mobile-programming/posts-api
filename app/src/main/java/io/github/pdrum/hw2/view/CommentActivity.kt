package io.github.pdrum.hw2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.pdrum.hw2.App
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.data.Comment
import io.github.pdrum.hw2.presenter.CommentPresenter
import io.github.pdrum.hw2.presenter.CommentView
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator
import io.github.pdrum.hw2.view.recycler.CommentRecyclerAdapter
import io.github.pdrum.hw2.view.utils.showProgress
import io.github.pdrum.hw2.view.utils.toastErr
import kotlinx.android.synthetic.main.activity_comment.*
import javax.inject.Inject

class CommentActivity : AppCompatActivity(), CommentView {

    companion object {
        const val POST_ID_KEY = "post_id"
    }

    @Inject
    lateinit var presenter: CommentPresenter
    private val comments = ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        setTitle(R.string.comments)
        App.injector.inject(this)
        presenter.setView(this)
        this.comment_recycler.layoutManager = LinearLayoutManager(this)
        this.comment_recycler.adapter = CommentRecyclerAdapter(comments)
        val postId = intent!!.extras!!.getInt(POST_ID_KEY)
        presenter.onFetchRequested(postId)
    }

    override fun changeComments(comments: List<Comment>) {
        this.comments.clear()
        this.comments.addAll(comments)
        this.comment_recycler.adapter!!.notifyDataSetChanged()
    }

    override fun showProgress(): ProgressIndicator = showProgress(this)

    override fun showError() = toastErr(this, getString(R.string.error))
}
