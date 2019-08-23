package com.ghataa.postwalker.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "comments")
public final class Comment {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @NonNull
    @ColumnInfo(name = "post_id")
    private final String postId;

    @NonNull
    @ColumnInfo(name = "name")
    private final String name;

    @Nullable
    @ColumnInfo(name = "email")
    private final String email;

    @NonNull
    @ColumnInfo(name = "body")
    private final String body;

    @Ignore
    public Comment(@NonNull String postId, @NonNull String name, @NonNull String body) {
        this(UUID.randomUUID().toString(), postId, name, null, body);
    }

    public Comment(@NonNull String id, @NonNull String postId, @NonNull String name,
                   @Nullable String email, @NonNull String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getPostId() {
        return postId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) &&
                postId.equals(comment.postId) &&
                name.equals(comment.name) &&
                Objects.equals(email, comment.email) &&
                body.equals(comment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, name, email, body);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
