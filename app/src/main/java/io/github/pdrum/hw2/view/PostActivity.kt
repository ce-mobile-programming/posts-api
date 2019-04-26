package io.github.pdrum.hw2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.github.pdrum.hw2.App
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.data.Post
import io.github.pdrum.hw2.presenter.PostPresenter
import io.github.pdrum.hw2.presenter.PostView
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator
import io.github.pdrum.hw2.view.utils.showProgress
import io.github.pdrum.hw2.view.utils.toastErr
import javax.inject.Inject

class PostActivity : AppCompatActivity(), PostView {

    lateinit var presenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        App.injector.inject(this)
        presenter.setView(this)
    }

    override fun showProgress(): ProgressIndicator = showProgress(this)

    override fun showError() = toastErr(this, getString(R.string.error))

    override fun changePosts(posts: List<Post>) {
        println("Posts are ")
        println(posts)
    }
}
