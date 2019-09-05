package com.ghataa.postwalker.data.source.remote;

import androidx.annotation.NonNull;

import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.data.source.PostsDataSource;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class RemotePostsDataSource implements PostsDataSource {

    private final PostsApiService postsApiService;

    @Inject
    public RemotePostsDataSource() {
        postsApiService = PostsApiService.retrofit.create(PostsApiService.class);
    }

    @Override
    public Single<List<Post>> getPosts() {
        return postsApiService.getPosts();
    }

    @Override
    public Single<Post> getPost(@NonNull String postId) {
        return postsApiService.getPost(postId);
    }

    @Override
    public Completable savePost(@NonNull Post post) {
        return Completable.create(emitter -> {
            Random random = new Random();

            if (random.nextBoolean()) {
                emitter.onComplete();
            } else {
                emitter.onError(new Throwable());
            }
        });
    }

    @Override
    public void refreshPosts() {
        // ignore (should call server API)
    }

    @Override
    public void deleteAllPosts() {
        // ignore (should call server API)
    }

    @Override
    public void deletePost(@NonNull String postId) {
        // ignore (should call server API)
    }
}
