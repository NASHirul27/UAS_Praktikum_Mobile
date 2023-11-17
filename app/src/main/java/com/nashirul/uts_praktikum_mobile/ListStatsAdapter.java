package com.nashirul.uts_praktikum_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListStatsAdapter extends RecyclerView.Adapter<ListStatsAdapter.ListViewHolder> {
    private ArrayList<Stats> listStats;

    private ListStatsAdapter.OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(ListStatsAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListStatsAdapter(ArrayList<Stats> list){
        this.listStats = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.stats_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Stats stats = listStats.get(position);
        holder.imgPhoto.setImageResource(stats.getPhoto());
        holder.tvName.setText(stats.getName());
        holder.tvDescription.setText(stats.getDescription());
        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(listStats.get(holder.getAdapterPosition()));
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(Stats data);
    }


    @Override
    public int getItemCount() {
        return listStats.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription;
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_stats_photo);
            tvName = itemView.findViewById(R.id.tv_stats_name);
            tvDescription = itemView.findViewById(R.id.tv_stats_description);
        }
    }
}
