package com.ghataa.postwalker.posts;

import com.ghataa.postwalker.di.ActivityScoped;
import com.ghataa.postwalker.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PostsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PostsFragment postsFragment();

    @ActivityScoped
    @Binds
    abstract PostsContract.Presenter postsPresenter(PostsPresenter presenter);
}
