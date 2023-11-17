package com.nashirul.uts_praktikum_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.ListViewHolder> {
    private ArrayList<Notes> listNotes;

    private ListNotesAdapter.OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(ListNotesAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public ListNotesAdapter(ArrayList<Notes> list){
        this.listNotes = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.notes_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Notes notes = listNotes.get(position);
        holder.imgPhoto.setImageResource(notes.getPhoto());
        holder.tvName.setText(notes.getName());
        holder.tvDescription.setText(notes.getDescription());
        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(listNotes.get(holder.getAdapterPosition()));
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(Notes data);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription;
        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_notes_photo);
            tvName = itemView.findViewById(R.id.tv_notes_name);
            tvDescription = itemView.findViewById(R.id.tv_notes_description);
        }
    }
}
