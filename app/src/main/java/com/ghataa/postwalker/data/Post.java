package com.ghataa.postwalker.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "posts")
public final class Post {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @NonNull
    @ColumnInfo(name = "user_id")
    private final String userId;

    @NonNull
    @ColumnInfo(name = "title")
    private final String title;

    @Nullable
    @ColumnInfo(name = "body")
    private final String body;

    @Ignore
    public Post(@NonNull String userId, @NonNull String title) {
        this(UUID.randomUUID().toString(), userId, title, null);
    }

    @Ignore
    public Post(@NonNull String id, @NonNull String userId, @NonNull String title) {
        this(id, userId, title, null);
    }

    public Post(@NonNull String id, @NonNull String userId,
                @NonNull String title, @Nullable String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                userId.equals(post.userId) &&
                title.equals(post.title) &&
                Objects.equals(body, post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, body);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
