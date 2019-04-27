package io.github.pdrum.hw2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.pdrum.hw2.App
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.data.Comment
import io.github.pdrum.hw2.presenter.CommentPresenter
import io.github.pdrum.hw2.presenter.CommentView
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator
import io.github.pdrum.hw2.view.utils.showProgress
import io.github.pdrum.hw2.view.utils.toastErr
import javax.inject.Inject

class CommentActivity : AppCompatActivity(), CommentView {

    companion object {
        const val POST_ID_KEY = "post_id"
    }

    @Inject
    lateinit var presenter: CommentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        App.injector.inject(this)
        presenter.setView(this)
        val postId = intent!!.extras!!.getInt(POST_ID_KEY)
        presenter.onFetchRequested(postId)
    }

    override fun changeComments(comments: List<Comment>) {
        println("Comments are")
        println(comments)
    }

    override fun showProgress(): ProgressIndicator = showProgress(this)

    override fun showError() = toastErr(this, getString(R.string.error))
}
