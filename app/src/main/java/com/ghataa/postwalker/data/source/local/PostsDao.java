package com.ghataa.postwalker.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ghataa.postwalker.data.Post;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PostsDao {

    @Query("SELECT * FROM Posts")
    Single<List<Post>> getPosts();

    @Query("SELECT * FROM Posts WHERE id = :postId")
    Single<Post> getPostById(String postId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(Post post);

    @Update
    int updatePost(Post post);

    @Query("DELETE FROM Posts WHERE id = :postId")
    int deletePostkById(String postId);

    @Query("DELETE FROM Posts")
    void deletePosts();
}
