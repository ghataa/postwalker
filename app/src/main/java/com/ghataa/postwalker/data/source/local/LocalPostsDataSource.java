package com.ghataa.postwalker.data.source.local;

import androidx.annotation.NonNull;

import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.data.source.PostsDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class LocalPostsDataSource implements PostsDataSource {

    private final PostsDao postsDao;

    @Inject
    public LocalPostsDataSource(@NonNull PostsDao postsDao) {
        this.postsDao = postsDao;
    }

    @Override
    public Single<List<Post>> getPosts() {
        return postsDao.getPosts();
    }

    @Override
    public Single<Post> getPost(@NonNull String postId) {
        return postsDao.getPostById(postId);
    }

    @Override
    public void refreshPosts() {
        // Not required because the PostsRepository handles the logic of refreshing the
        // posts from all the available data sources.
    }

    @Override
    public void deleteAllPosts() {
        postsDao.deletePosts();
    }

    @Override
    public void deletePost(@NonNull String postId) {
        postsDao.deletePostkById(postId);
    }
}
