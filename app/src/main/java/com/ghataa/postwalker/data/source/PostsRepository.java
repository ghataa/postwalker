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

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

@Singleton
public class PostsRepository implements PostsDataSource {

    private final PostsDataSource localPostsDataSource;
    private final PostsDataSource remotePostsDataSource;

    /**
     * We cache posts in memory for solving UI faster
     */
    Map<String, Post> cachedPosts;

    boolean memoryCacheIsDirty = false;

    @Inject
    public PostsRepository(@Local PostsDataSource localPostsDataSource,
                           @Remote PostsDataSource remotePostsDataSource) {
        this.localPostsDataSource = localPostsDataSource;
        this.remotePostsDataSource = remotePostsDataSource;
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<List<Post>> getPosts() {
        if (cachedPosts != null && !memoryCacheIsDirty) {
            return Single.just(new ArrayList<>(cachedPosts.values()));
        }

        if (memoryCacheIsDirty) {
            return getPostsFromRemoteSource();
        } else {
            return Single.create(emitter -> localPostsDataSource.getPosts()
                    .flatMap((Function<List<Post>, SingleSource<List<Post>>>) posts -> {
                        if (posts.size() == 0) {
                            return getPostsFromRemoteSource();
                        }

                        return Single.just(posts);
                    })
                    .onErrorResumeNext(getPostsFromRemoteSource())
                    .subscribe(posts -> {
                        refreshMemoryCache(posts);
                        emitter.onSuccess(posts);
                    }, emitter::onError));
        }
    }

    private Single<List<Post>> getPostsFromRemoteSource() {
        return Single.create(emitter -> remotePostsDataSource.getPosts()
                .subscribe(posts -> {
                    refreshMemoryCache(posts);
                    refreshLocalDatabase(posts);
                    emitter.onSuccess(posts);
                }, emitter::onError));
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<Post> getPost(@NonNull String postId) {
        final Post cachedPost = getPostWithId(postId);

        if (cachedPost != null) {
            return Single.just(cachedPost);
        }

        return Single.create(emitter -> localPostsDataSource.getPost(postId)
                .flatMap((Function<Post, SingleSource<Post>>) post -> {
                    if (post == null) {
                        return getPostFromRemoteSource(postId);
                    }

                    return Single.just(post);
                })
                .onErrorResumeNext(getPostFromRemoteSource(postId))
                .subscribe(post -> {
                    if (cachedPosts == null) {
                        cachedPosts = new LinkedHashMap<>();
                    }

                    cachedPosts.put(post.getId(), post);

                    emitter.onSuccess(post);
                }, emitter::onError));
    }

    @Override
    public Completable savePost(@NonNull Post post) {
        return Completable.create(emitter -> {
            remotePostsDataSource.savePost(post).subscribe();
            localPostsDataSource.savePost(post).subscribe();

            if (cachedPosts == null) {
                cachedPosts = new LinkedHashMap<>();
            }

            cachedPosts.put(post.getId(), post);

            emitter.onComplete();
        });
    }

    private Single<Post> getPostFromRemoteSource(@NonNull String postId) {
        return Single.create(emitter -> remotePostsDataSource.getPost(postId)
                .subscribe(post -> {
                    if (cachedPosts == null) {
                        cachedPosts = new LinkedHashMap<>();
                    }

                    cachedPosts.put(post.getId(), post);

                    emitter.onSuccess(post);
                }, emitter::onError));
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

    private void refreshLocalDatabase(List<Post> posts) {
        for (Post post : posts) {
            localPostsDataSource.savePost(post).subscribe();
        }
    }

    @Nullable
    private Post getPostWithId(@NonNull String id) {
        if (cachedPosts == null || cachedPosts.isEmpty()) {
            return null;
        }

        return cachedPosts.get(id);
    }
}
