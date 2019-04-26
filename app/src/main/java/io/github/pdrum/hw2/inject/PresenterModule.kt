package io.github.pdrum.hw2.inject

import dagger.Binds
import dagger.Module
import io.github.pdrum.hw2.presenter.CommentPresenter
import io.github.pdrum.hw2.presenter.CommentPresenterImpl
import io.github.pdrum.hw2.presenter.PostPresenter
import io.github.pdrum.hw2.presenter.PostPresenterImpl

@Module
interface PresenterModule {
    @Binds
    fun bindPostPresenter(presenter: PostPresenterImpl): PostPresenter

    @Binds
    fun bindCommentPresenter(presenter: CommentPresenterImpl): CommentPresenter
}
