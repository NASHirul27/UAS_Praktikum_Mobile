package com.nashirul.uts_praktikum_mobile;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentStats extends Fragment {
    private RecyclerView rvStats;
    private ArrayList<Stats> list = new ArrayList<>();
    View view;
    public FragmentStats() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.stats_fragment, container, false);
            rvStats = view.findViewById(R.id.rv_stats);
            rvStats.setHasFixedSize(true);
            rvStats.setLayoutManager(new LinearLayoutManager(getActivity()));

            list.addAll(getListStats());

            ListStatsAdapter adapter = new ListStatsAdapter(list);
            rvStats.setAdapter(adapter);

            adapter.setOnItemClickCallback(this::showSelectedItems);
        }
        return view;
    }

    public ArrayList<Stats> getListStats() {
        String[] dataName = getResources().getStringArray(R.array.stat_name);
        String[] dataDescription = getResources().getStringArray(R.array.stat_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.stat_photo);
        ArrayList<Stats> listStats = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Stats stats = new Stats();
            stats.setName(dataName[i]);
            stats.setDescription(dataDescription[i]);
            stats.setPhoto(dataPhoto.getResourceId(i, -1));
            listStats.add((stats));
        }
        return listStats;
    }

    private void showRecyclerList(){
        rvStats.setLayoutManager(new LinearLayoutManager(requireContext()));
        ListStatsAdapter listStatsAdapter = new ListStatsAdapter(list);

        listStatsAdapter.setOnItemClickCallback(this::showSelectedItems);
    }
    private void showSelectedItems (Stats stats){
        Intent intent = new Intent(requireActivity(), StatsDetailActivity.class);

        intent.putExtra("stats_name", stats.getName());
        intent.putExtra("stats_description", stats.getDescription());
        intent.putExtra("stats_photo", stats.getPhoto());

        startActivity(intent);
    }
}

