package com.example.architecturev2.di

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.architecturev2.api.PostsAPI
import com.example.architecturev2.db.PostsDB
import com.example.architecturev2.models.PostsResponse
import com.example.architecturev2.repository.PostsRepository
import com.example.architecturev2.ui.PostViewModelProviderFactory
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(@NonNull private val context: Context) {


    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }




    @Provides
    @Singleton
    fun provideViewModelFactory(repository: PostsRepository): PostViewModelProviderFactory =
        PostViewModelProviderFactory(repository)

    @Singleton
    @Provides
    @NonNull
    fun context(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideUserPostsDatabase(): PostsDB =
        PostsDB.invoke(context)

    @Provides
    @Singleton
    fun postsRepository(
        postsDatabase: PostsDB
    ): PostsRepository =
        PostsRepository(
            postsDB = postsDatabase
        )
}