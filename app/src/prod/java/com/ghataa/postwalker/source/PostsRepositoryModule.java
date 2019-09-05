package com.ghataa.postwalker.source;

import android.app.Application;

import androidx.room.Room;

import com.ghataa.postwalker.data.source.Local;
import com.ghataa.postwalker.data.source.PostsDataSource;
import com.ghataa.postwalker.data.source.Remote;
import com.ghataa.postwalker.data.source.local.LocalPostsDataSource;
import com.ghataa.postwalker.data.source.local.PostDatabase;
import com.ghataa.postwalker.data.source.local.PostsDao;
import com.ghataa.postwalker.data.source.remote.RemotePostsDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class PostsRepositoryModule {

    @Singleton
    @Binds
    @Local
    abstract PostsDataSource provideLocalPostsDataSource(LocalPostsDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract PostsDataSource provideRemotePostsDataSource(RemotePostsDataSource dataSource);

    @Singleton
    @Provides
    static PostDatabase provideDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), PostDatabase.class, "Posts.db")
                .build();
    }

    @Singleton
    @Provides
    static PostsDao providePostsDao(PostDatabase database) {
        return database.postsDao();
    }
}
