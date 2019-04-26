package io.github.pdrum.hw2.presenter

import io.github.pdrum.hw2.data.Comment
import io.github.pdrum.hw2.http.PostClient
import io.github.pdrum.hw2.presenter.utils.Presenter
import io.github.pdrum.hw2.presenter.utils.ProgressIndicator
import javax.inject.Inject

interface CommentPresenter: Presenter<CommentView> {
    fun onFetchRequested(postId: Int)
}

interface CommentView {
    fun changeComments(comments: List<Comment>)
    fun showProgress(): ProgressIndicator
    fun showError()
}

class CommentPresenterImpl @Inject constructor(private val postClient: PostClient): CommentPresenter {
    private lateinit var view: CommentView

    override fun setView(view: CommentView) {
        this.view = view
    }

    override fun onFetchRequested(postId: Int) {
        val indicator = view.showProgress()
        postClient.fetchComments(postId,
            onSuccess = {
                indicator.hide()
                view.changeComments(it)
            },
            onFailure = {
                indicator.hide()
                view.showError()
            }
        )
    }

}
