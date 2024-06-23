package com.example.myapplication.view;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.OnSourceClickListener;
import com.example.myapplication.R;
import com.example.myapplication.model.Sources;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView jobNameTextView;
    private final TextView statusTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        jobNameTextView = itemView.findViewById(R.id.descriptionTextView);
        statusTextView = itemView.findViewById(R.id.statusTextView);
    }

    public void bind(final Sources source, final OnSourceClickListener listener) {
        jobNameTextView.setText(source.getName());
        statusTextView.setText(source.getStatus());
        itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSourceClick(source);
            }
        });
    }
}