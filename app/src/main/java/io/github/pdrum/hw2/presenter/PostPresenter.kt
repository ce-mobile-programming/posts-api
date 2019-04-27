package io.github.pdrum.hw2.presenter

import io.github.pdrum.hw2.data.Post
import io.github.pdrum.hw2.http.PostClient
import io.github.pdrum.hw2.presenter.utils.Presenter
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator
import javax.inject.Inject

interface PostPresenter: Presenter<PostView> {
    fun onFetchRequested()
    fun onPostSelected(post: Post)
}

interface PostView {
    fun showProgress(): ProgressIndicator
    fun showError()
    fun changePosts(posts: List<Post>)
    fun startCommentsActivity(postId: Int)
}

class PostPresenterImpl @Inject constructor(private val postClient: PostClient): PostPresenter {
    private lateinit var view: PostView

    override fun setView(view: PostView) {
        this.view = view
    }

    override fun onFetchRequested() {
        val indicator = view.showProgress()
        postClient.fetchPosts(
            onSuccess = {
                println("SUCCESS")
                println("SUCCESS")
                println("SUCCESS")
                println("SUCCESS")
                println("SUCCESS")
                indicator.hide()
                view.changePosts(it)
            },
            onFailure = {
                println("failure")
                println("failure")
                println("failure")
                println("failure")
                println("failure")
                println("failure")
                view.showError()
                indicator.hide()
            }
        )
    }

    override fun onPostSelected(post: Post) {
        view.startCommentsActivity(post.id)
    }
}