package io.github.pdrum.hw2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.pdrum.hw2.App
import io.github.pdrum.hw2.R
import io.github.pdrum.hw2.data.Post
import io.github.pdrum.hw2.presenter.PostPresenter
import io.github.pdrum.hw2.presenter.PostView
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator
import io.github.pdrum.hw2.view.recycler.PostRecyclerAdapter
import io.github.pdrum.hw2.view.utils.showProgress
import io.github.pdrum.hw2.view.utils.toastErr
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : AppCompatActivity(), PostView {

    @Inject
    lateinit var presenter: PostPresenter
    private val posts = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setTitle(R.string.posts)
        App.injector.inject(this)
        presenter.setView(this)
        this.post_recycler.layoutManager = LinearLayoutManager(this)
        this.post_recycler.adapter = PostRecyclerAdapter(posts) {
            presenter.onPostSelected(it)
        }
        presenter.onFetchRequested()
    }

    override fun showProgress(): ProgressIndicator = showProgress(this)

    override fun showError() = toastErr(this, getString(R.string.error))

    override fun changePosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        this.post_recycler.adapter!!.notifyDataSetChanged()
    }

    override fun startCommentsActivity(postId: Int) {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra(CommentActivity.POST_ID_KEY, postId)
        startActivity(intent)
    }
}
