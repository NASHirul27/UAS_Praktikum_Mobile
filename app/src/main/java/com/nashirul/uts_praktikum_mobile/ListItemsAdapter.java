package com.nashirul.uts_praktikum_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.ListViewHolder> {
    private ArrayList<Items> listItems;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListItemsAdapter(ArrayList<Items> list){
        this.listItems = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Items items = listItems.get(position);
        holder.imgPhoto.setImageResource(items.getPhoto());
        holder.tvName.setText(items.getName());
        holder.tvDescription.setText(items.getDescription());
        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(listItems.get(holder.getAdapterPosition()));
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(Items data);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription;
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
        }
    }
}
