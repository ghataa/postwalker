package com.ghataa.postwalker.posts;

import androidx.annotation.Nullable;

import com.ghataa.postwalker.data.source.PostsRepository;
import com.ghataa.postwalker.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class PostsPresenter implements PostsContract.Presenter {

    private final PostsRepository postsRepository;

    @Nullable
    private PostsContract.View postsView;

    private boolean firstLoad = true;

    @Inject
    PostsPresenter(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // TODO
    }

    @Override
    public void loadPosts(boolean forceUpdate) {
        // TODO
    }

    @Override
    public void clearPosts() {
        // TODO
    }

    @Override
    public void takeView(PostsContract.View view) {
        // TODO
    }

    @Override
    public void dropView() {
        // TODO
    }
}
