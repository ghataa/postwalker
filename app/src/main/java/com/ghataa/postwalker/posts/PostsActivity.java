package com.ghataa.postwalker.posts;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ghataa.postwalker.BaseActivity;
import com.ghataa.postwalker.R;
import com.ghataa.postwalker.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;

public class PostsActivity extends BaseActivity {

    @Inject
    Lazy<PostsFragment> postsFragmentProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFragment();
    }

    private void setContentFragment() {
        PostsFragment postsFragment = (PostsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (postsFragment == null) {
            // Get the fragment from Dagger
            postsFragment = postsFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), postsFragment, R.id.fragment_container);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_posts;
    }

    @Override
    protected void setUpToolbar() {
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
