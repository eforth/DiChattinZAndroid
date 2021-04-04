package com.vectorjm.dichattinzandroid;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ChatBout> chatBouts;

    public HomeAdapter(List<ChatBout> chatBouts) {
        this.chatBouts = chatBouts;
    }

    @Override
    public int getItemViewType(int position) {
        ChatBout current = chatBouts.get(position);

        if (current.getType().equals("video")) return 1;

        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.holder_chat_bout_image, parent, false);

        return new ChatBoutImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatBout current = chatBouts.get(position);
        Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.logo);

        if (holder instanceof ChatBoutImageViewHolder) {
            ChatBoutImageViewHolder viewHolder = (ChatBoutImageViewHolder) holder;
            ChatBoutImage chatBoutImage = (ChatBoutImage)current;
            viewHolder.textView.setText(current.getDescription());
            //viewHolder.dateTimeView.setText(current.getDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            viewHolder.avatarView.setImageDrawable(drawable);
            Glide.with(viewHolder.imageView.getContext())
                    .load(chatBoutImage.getImageUrl())
                    .into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return chatBouts.size();
    }

    static class ChatBoutImageViewHolder extends RecyclerView.ViewHolder {
        CircularImageView avatarView;
        TextView nameView;
        TextView dateTimeView;
        TextView textView;
        ImageView imageView;

        public ChatBoutImageViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.profile);
            nameView = itemView.findViewById(R.id.name);
            dateTimeView = itemView.findViewById(R.id.details);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.photo);
        }
    }
}
