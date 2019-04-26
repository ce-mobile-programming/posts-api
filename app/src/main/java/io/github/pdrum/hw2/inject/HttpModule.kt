package io.github.pdrum.hw2.inject

import dagger.Module
import dagger.Provides
import io.github.pdrum.hw2.http.PostClient
import io.github.pdrum.hw2.http.PostClientImpl
import io.github.pdrum.hw2.http.RetrofitPostClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class HttpModule {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePostClient(): PostClient =
        PostClientImpl(retrofit.create(RetrofitPostClient::class.java))
}
