package com.ghataa.postwalker.posts;

import com.ghataa.postwalker.BasePresenter;
import com.ghataa.postwalker.BaseView;
import com.ghataa.postwalker.data.Post;

import java.util.List;

public interface PostsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showPosts(List<Post> posts);

        void showLoadingPostsError();

        void showNoPosts();

        boolean isActive();
    }

    interface Presenter extends BasePresenter<View> {

        void loadPosts(boolean forceUpdate);
    }
}
