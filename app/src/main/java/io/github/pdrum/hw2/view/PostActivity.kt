package io.github.pdrum.hw2.view

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : AppCompatActivity(), PostView {

    lateinit var presenter: PostPresenter
    private val posts = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        App.injector.inject(this)
        presenter.setView(this)
        this.post_recycler_list.adapter = PostRecyclerAdapter(posts) {
            presenter.onPostSelected(it)
        }
    }

    override fun showProgress(): ProgressIndicator = showProgress(this)

    override fun showError() = toastErr(this, getString(R.string.error))

    override fun changePosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
    }

    override fun startCommentsActivity(postId: Int) {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra(CommentActivity.POST_ID_KEY, postId)
        startActivity(intent)
    }
}
