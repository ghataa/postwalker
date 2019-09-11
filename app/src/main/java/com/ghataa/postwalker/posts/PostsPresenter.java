package com.ghataa.postwalker.posts;

import androidx.annotation.Nullable;

import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.data.source.PostsRepository;
import com.ghataa.postwalker.di.ActivityScoped;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScoped
public class PostsPresenter implements PostsContract.Presenter {

    private final PostsRepository postsRepository;
    private CompositeDisposable compositeDisposable;

    @Nullable
    private PostsContract.View postsView;

    @Inject
    PostsPresenter(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadPosts(boolean forceUpdate) {
        if (forceUpdate) {
            postsRepository.refreshPosts();
        }

        compositeDisposable.add(postsRepository.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (postsView != null) {
                        postsView.setLoadingIndicator(true);
                    }
                })
                .doOnTerminate(() -> {
                    if (postsView != null) {
                        postsView.setLoadingIndicator(false);
                    }
                })
                .subscribe(this::showPosts, throwable -> {
                    if (postsView != null) {
                        postsView.showLoadingPostsError();
                    }
                }));
    }

    private void showPosts(List<Post> posts) {
        if (posts == null || posts.isEmpty()) {
            if (postsView != null) {
                postsView.showNoPosts();
            }
        } else if (postsView != null) {
            postsView.showPosts(posts);
        }
    }

    @Override
    public void takeView(PostsContract.View view) {
        postsView = view;
        loadPosts(false);
    }

    @Override
    public void dropView() {
        postsView = null;
        compositeDisposable.dispose();
    }
}
