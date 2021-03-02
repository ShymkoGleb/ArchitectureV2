package com.example.architecturev2.di

import android.content.Context
import com.example.architecturev2.ui.PostViewModelProviderFactory
import com.example.architecturev2.ui.PostsActivity
import com.example.architecturev2.ui.fragments.FirstFragment
import com.example.architecturev2.ui.fragments.SecondFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun context():Context
    fun inject(activity: PostsActivity)
    fun injectFragment2(fragment: SecondFragment)
    fun injectFragment1(fragment: FirstFragment)
    fun getPostViewFactory(): PostViewModelProviderFactory
}