package com.ghataa.postwalker.data.source;

import androidx.annotation.NonNull;

import com.ghataa.postwalker.data.Post;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PostsDataSource {

    Single<List<Post>> getPosts();

    Single<Post> getPost(@NonNull String postId);

    Completable savePost(@NonNull Post post);

    void refreshPosts();

    void deleteAllPosts();

    void deletePost(@NonNull String postId);
}
