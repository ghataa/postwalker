package com.ghataa.postwalker.di;

import com.ghataa.postwalker.posts.PostsActivity;
import com.ghataa.postwalker.posts.PostsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = PostsModule.class)
    abstract PostsActivity postsActivity();
}
