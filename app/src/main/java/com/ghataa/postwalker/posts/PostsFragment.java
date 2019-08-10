package com.ghataa.postwalker.posts;

import com.ghataa.postwalker.BaseFragment;
import com.ghataa.postwalker.BasePresenter;
import com.ghataa.postwalker.R;
import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.di.ActivityScoped;

import java.util.List;

import javax.inject.Inject;

@ActivityScoped
public class PostsFragment extends BaseFragment implements PostsContract.View {

    @Inject
    PostsContract.Presenter presenter;

    @Inject
    public PostsFragment() {
        // required for Dagger
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_posts;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        // TODO
    }

    @Override
    public void showPosts(List<Post> posts) {
        // TODO
    }

    @Override
    public void showLoadingPostsError() {
        // TODO
    }

    @Override
    public void showNoPosts() {
        // TODO
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
