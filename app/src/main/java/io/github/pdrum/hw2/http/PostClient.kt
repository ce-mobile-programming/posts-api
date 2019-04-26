package io.github.pdrum.hw2.http

import io.github.pdrum.hw2.data.Comment
import io.github.pdrum.hw2.data.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostClient {
    fun fetchPosts(onSuccess: (posts: List<Post>) -> Unit, onFailure: () -> Unit)
    fun fetchComments(postId: Int, onSuccess: (comments: List<Comment>) -> Unit, onFailure: () -> Unit)
}

interface RetrofitPostClient {
    @GET("/posts")
    fun fetchPosts(): Call<List<Post>>
    @GET("/comments")
    fun fetchComments(@Query("postId") postId: Int): Call<List<Comment>>
}

class PostClientImpl(private val retrofit: RetrofitPostClient): PostClient {
    override fun fetchPosts(onSuccess: (posts: List<Post>) -> Unit, onFailure: () -> Unit) {
        val callback = MyCallback<List<Post>>(
            onSuccess = {onSuccess(it!!)},
            onNetworkFailure = onFailure,
            onHttpFailure = {onFailure()}
        )
        retrofit.fetchPosts().enqueue(callback)
    }

    override fun fetchComments(postId: Int, onSuccess: (comments: List<Comment>) -> Unit, onFailure: () -> Unit) {
        val callback = MyCallback<List<Comment>>(
            onSuccess = {onSuccess(it!!)},
            onHttpFailure = {onFailure()},
            onNetworkFailure = onFailure
        )
        retrofit.fetchComments(postId).enqueue(callback)
    }
}
