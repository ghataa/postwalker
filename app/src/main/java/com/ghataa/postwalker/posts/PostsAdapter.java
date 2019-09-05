package com.ghataa.postwalker.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ghataa.postwalker.R;
import com.ghataa.postwalker.data.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onPostItemClick(Post item, int position);
    }

    private List<Post> items = new ArrayList<>();
    private PostsAdapter.ItemClickListener itemClickListener;

    private int itemLayoutId;

    public PostsAdapter(List<Post> items) {
        this.items.addAll(items);
        this.itemLayoutId = R.layout.item_post;
        setHasStableIds(true);
        updateItems(items);
    }

    public void updateItems(List<Post> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItemClickListener(PostsAdapter.ItemClickListener listener) {
        itemClickListener = listener;
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        return new PostsAdapter.ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position + 1L;
    }

    public Post getItem(int position) {
        if (position >= 0 && position < getItemCount()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView titleTextView;
        @BindView(R.id.description)
        TextView descriptionTextView;

        private final PostsAdapter.ItemClickListener itemClickListener;
        private Post item;
        private int position;

        public ViewHolder(View itemView, PostsAdapter.ItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemClickListener = itemClickListener;
        }

        private void bind(Post item, int position) {
            this.item = item;
            this.position = position;

            titleTextView.setText(item.getTitle());
            descriptionTextView.setText(item.getBody());
        }

        @OnClick(R.id.container)
        void onItemClick() {
            if (itemClickListener != null) {
                itemClickListener.onPostItemClick(item, position);
            }
        }
    }
}
