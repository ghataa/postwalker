package com.ghataa.postwalker;

import androidx.annotation.NonNull;

import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.data.source.PostsDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class MockRemotePostsDataSource implements PostsDataSource {

    private static final Map<String, Post> POSTS_SERVICE_DATA = new LinkedHashMap<>();

    @Inject
    public MockRemotePostsDataSource() {
    }

    @Override
    public Single<List<Post>> getPosts() {
        return Single.just(new ArrayList<>(POSTS_SERVICE_DATA.values()));
    }

    @Override
    public Single<Post> getPost(@NonNull String postId) {
        return Single.just(POSTS_SERVICE_DATA.get(postId));
    }

    @Override
    public void refreshPosts() {
        // Not required because the PostsRepository handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllPosts() {
        POSTS_SERVICE_DATA.clear();
    }

    @Override
    public void deletePost(@NonNull String postId) {
        POSTS_SERVICE_DATA.remove(postId);
    }
}
