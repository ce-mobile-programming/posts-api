package io.github.pdrum.hw2.presenter.utils

interface Presenter<T: Any> {
    fun setView(view: T)
}
