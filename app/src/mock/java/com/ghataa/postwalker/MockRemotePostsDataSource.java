package com.ghataa.postwalker;

import androidx.annotation.NonNull;

import com.ghataa.postwalker.data.Post;
import com.ghataa.postwalker.data.source.PostsDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class MockRemotePostsDataSource implements PostsDataSource {

    private static final String POST_1_KEY = "key_1";
    private static final String POST_2_KEY = "key_2";
    private static final String POST_3_KEY = "key_3";
    private static final String POST_4_KEY = "key_4";
    private static final String POST_5_KEY = "key_5";
    private static final String POST_6_KEY = "key_6";
    private static final String POST_7_KEY = "key_7";
    private static final String POST_8_KEY = "key_8";

    private Map<String, Post> postsServiceData = new LinkedHashMap<>();

    @Inject
    public MockRemotePostsDataSource() {
        Post post1 = new Post(UUID.randomUUID().toString(), "1234", "Title one", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec dui eget ligula sodales eleifend.");
        Post post2 = new Post(UUID.randomUUID().toString(), "4321", "Title two", "Sed et tellus pulvinar, scelerisque diam et, suscipit justo. Donec tincidunt, enim non tincidunt blandit, est sem sodales libero, et volutpat ex massa id nisi. Suspendisse nec ex quis neque vehicula iaculis. Proin fringilla vel ante non molestie.");
        Post post3 = new Post(UUID.randomUUID().toString(), "1212", "Title three", "Nullam convallis rhoncus sapien vel ultrices.");
        Post post4 = new Post(UUID.randomUUID().toString(), "2121", "Title four", "Quisque pulvinar justo eget ultricies lacinia. Morbi eget lorem non nisl fringilla imperdiet.");
        Post post5 = new Post(UUID.randomUUID().toString(), "1111", "Title five", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec dui eget ligula sodales eleifend.");
        Post post6 = new Post(UUID.randomUUID().toString(), "2222", "Title six", "Sed et tellus pulvinar, scelerisque diam et, suscipit justo. Donec tincidunt, enim non tincidunt blandit, est sem sodales libero, et volutpat ex massa id nisi. Suspendisse nec ex quis neque vehicula iaculis. Proin fringilla vel ante non molestie.");
        Post post7 = new Post(UUID.randomUUID().toString(), "3333", "Title seven", "Quisque pulvinar justo eget ultricies lacinia. Morbi eget lorem non nisl fringilla imperdiet.");
        Post post8 = new Post(UUID.randomUUID().toString(), "4444", "Title eight", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec dui eget ligula sodales eleifend.");

        postsServiceData.put(POST_1_KEY, post1);
        postsServiceData.put(POST_2_KEY, post2);
        postsServiceData.put(POST_3_KEY, post3);
        postsServiceData.put(POST_4_KEY, post4);
        postsServiceData.put(POST_5_KEY, post5);
        postsServiceData.put(POST_6_KEY, post6);
        postsServiceData.put(POST_7_KEY, post7);
        postsServiceData.put(POST_8_KEY, post8);
    }

    @Override
    public Single<List<Post>> getPosts() {
        return Single.just(new ArrayList<>(postsServiceData.values()));
    }

    @Override
    public Single<Post> getPost(@NonNull String postId) {
        return Single.just(postsServiceData.get(postId));
    }

    @Override
    public Completable savePost(@NonNull Post post) {
        return Completable.create(emitter -> {
            postsServiceData.put(post.getId(), post);
            emitter.onComplete();
        });
    }

    @Override
    public void refreshPosts() {
        // Not required because the PostsRepository handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteAllPosts() {
        postsServiceData.clear();
    }

    @Override
    public void deletePost(@NonNull String postId) {
        postsServiceData.remove(postId);
    }
}
