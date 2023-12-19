package com.nashirul.uts_praktikum_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.ViewHolder> {
    private List<Spell> spells;
    private SpellAdapter.OnItemClickListener onItemClickListener;

    public SpellAdapter(List<Spell> spells, OnItemClickListener onItemClickListener) {
        this.spells = spells;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Spell spell = spells.get(position);

        holder.textViewName.setText(spell.getName());
        holder.textViewDesc.setText(spell.getDesc());
    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Spell spell);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_item_name);
            textViewDesc = itemView.findViewById(R.id.tv_item_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            SpellAdapter.this.onItemClickListener.onItemClick(spells.get(position));
                        }
                    }
                }
            });
        }
    }
}


