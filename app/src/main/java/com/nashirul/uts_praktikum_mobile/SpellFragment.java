package com.nashirul.uts_praktikum_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.VolleyError;
import java.util.List;

public class SpellFragment extends Fragment implements ApiRequest.ApiListener, SpellAdapter.OnItemClickListener{

    private RecyclerView recyclerView;

    public SpellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spell, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ApiRequest.makeApiRequest(requireContext(), this);
    }

    @Override
    public void onSuccessSpells(List<Spell> spells) {
        SpellAdapter adapter = new SpellAdapter(spells, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onError(VolleyError error) {
        // Handle the error here
    }

    @Override
    public void onItemClick(Spell spell) {
        // Handle item click, start a new activity, and pass the selected spell's data
        // You can choose to handle this in MainActivity or SpellFragment
        Intent intent = new Intent(requireContext(), SpellDetailActivity.class);
        intent.putExtra("name", spell.getName());
        intent.putExtra("desc", spell.getDesc());
        startActivity(intent);
    }
}