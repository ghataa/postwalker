package com.ghataa.postwalker.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ghataa.postwalker.data.Post;

@Database(entities = {Post.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {

    public abstract PostsDao postsDao();
}
