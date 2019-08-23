package com.ghataa.postwalker.data.source;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ghataa.postwalker.data.Post;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class PostsRepository implements PostsDataSource {

    private final PostsDataSource localPostsDataSource;

    /**
     * We cache posts in memory for solving UI faster
     */
    Map<String, Post> cachedPosts;

    boolean memoryCacheIsDirty = false;

    @Inject
    public PostsRepository(@Local PostsDataSource localPostsDataSource) {
        this.localPostsDataSource = localPostsDataSource;
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<List<Post>> getPosts() {
        if (cachedPosts != null && !memoryCacheIsDirty) {
            return Single.just(new ArrayList<>(cachedPosts.values()));
        }

        if (memoryCacheIsDirty) {
            return localPostsDataSource.getPosts(); // TODO fetch from remote source
        } else {
            return Single.create(emitter -> localPostsDataSource.getPosts()
                    .subscribe(posts -> {
                        refreshMemoryCache(posts);
                        emitter.onSuccess(posts);
                    }, throwable -> {
                        emitter.onError(throwable); // TODO fetch from remote source
                    }));
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<Post> getPost(@NonNull String postId) {
        final Post cachedPost = getPostWithId(postId);

        if (cachedPost != null) {
            return Single.just(cachedPost);
        }

        return Single.create(emitter -> localPostsDataSource.getPost(postId)
                .subscribe(post -> {
                    if (cachedPosts == null) {
                        cachedPosts = new LinkedHashMap<>();
                    }

                    cachedPosts.put(post.getId(), post);

                    emitter.onSuccess(post);
                }, throwable -> {
                    emitter.onError(throwable); // TODO fetch from remote source
                }));
    }

    @Override
    public void refreshPosts() {
        memoryCacheIsDirty = true;
    }

    @Override
    public void deleteAllPosts() {
        localPostsDataSource.deleteAllPosts();

        if (cachedPosts == null) {
            cachedPosts = new LinkedHashMap<>();
        }

        cachedPosts.clear();
    }

    @Override
    public void deletePost(@NonNull String postId) {
        localPostsDataSource.deletePost(postId);

        cachedPosts.remove(postId);
    }

    private void refreshMemoryCache(List<Post> posts) {
        if (cachedPosts == null) {
            cachedPosts = new LinkedHashMap<>();
        }

        cachedPosts.clear();

        for (Post post : posts) {
            cachedPosts.put(post.getId(), post);
        }

        memoryCacheIsDirty = false;
    }

    @Nullable
    private Post getPostWithId(@NonNull String id) {
        if (cachedPosts == null || cachedPosts.isEmpty()) {
            return null;
        }

        return cachedPosts.get(id);
    }
}
