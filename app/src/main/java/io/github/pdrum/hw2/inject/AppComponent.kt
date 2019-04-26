package io.github.pdrum.hw2.inject

import dagger.Component
import io.github.pdrum.hw2.view.CommentActivity
import io.github.pdrum.hw2.view.PostActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(postActivity: PostActivity)
    fun inject(commentActivity: CommentActivity)
}
