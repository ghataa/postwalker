package com.ghataa.postwalker.posts;

import com.ghataa.postwalker.BaseActivity;
import com.ghataa.postwalker.R;

public class PostsActivity extends BaseActivity {

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
