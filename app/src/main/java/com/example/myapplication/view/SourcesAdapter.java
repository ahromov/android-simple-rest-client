package com.example.myapplication.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.OnSourceClickListener;
import com.example.myapplication.R;
import com.example.myapplication.model.Sources;

import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Sources> sourcesList;
    private OnSourceClickListener listener;

    public SourcesAdapter(List<Sources> sourcesList, OnSourceClickListener listener) {
        this.sourcesList = sourcesList;
        this.listener = listener;
    }

    public void setSourcesList(List<Sources> sourcesList) {
        this.sourcesList = sourcesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sources source = sourcesList.get(position);
        holder.bind(source, listener);
    }

    @Override
    public int getItemCount() {
        return sourcesList == null ? 0 : sourcesList.size();
    }

}
