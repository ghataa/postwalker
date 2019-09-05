package com.ghataa.postwalker.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghataa.postwalker.BaseFragment;
import com.ghataa.postwalker.BasePresenter;
import com.ghataa.postwalker.R;
import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.di.ActivityScoped;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@ActivityScoped
public class PostsFragment extends BaseFragment implements PostsContract.View {

    @Inject
    PostsContract.Presenter presenter;

    @BindView(R.id.posts_recycler_view)
    RecyclerView postsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

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
        postsRecyclerView.setAdapter(new PostsAdapter(posts));
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
