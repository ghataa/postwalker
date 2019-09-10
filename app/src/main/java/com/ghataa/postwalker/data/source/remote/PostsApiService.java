package com.ghataa.postwalker.data.source.remote;

import com.ghataa.postwalker.data.Post;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsApiService {

    int TIMEOUT_IN_MILLIS = 10000;
    String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    @GET("/posts")
    Single<List<Post>> getPosts();

    @GET("/posts/{postId}")
    Single<Post> getPost(@Path("postId") String postId);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)
            .connectTimeout(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)
            .build();

    Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
}
