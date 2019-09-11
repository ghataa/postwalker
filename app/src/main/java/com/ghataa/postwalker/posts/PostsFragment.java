package com.ghataa.postwalker.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghataa.postwalker.BaseFragment;
import com.ghataa.postwalker.BasePresenter;
import com.ghataa.postwalker.R;
import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.di.ActivityScoped;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@ActivityScoped
public class PostsFragment extends BaseFragment implements PostsContract.View {

    @Inject
    PostsContract.Presenter presenter;

    @BindView(R.id.screen_container)
    ConstraintLayout screenContainerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.no_posts_textview)
    TextView noPostsTextView;
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
        progressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPosts(List<Post> posts) {
        noPostsTextView.setVisibility(View.GONE);
        postsRecyclerView.setAdapter(new PostsAdapter(posts));
    }

    @Override
    public void showLoadingPostsError() {
        noPostsTextView.setVisibility(View.VISIBLE);
        Snackbar.make(screenContainerView, R.string.posts_loading_posts_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showNoPosts() {
        noPostsTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
