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

public class FragmentItems extends Fragment {
    private RecyclerView rvItems;
    private ArrayList<Items> list = new ArrayList<>();
    View view;
    public FragmentItems(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.items_fragment, container, false);
            rvItems = view.findViewById(R.id.rv_items);
            rvItems.setHasFixedSize(true);
            rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));

            list.addAll(getListItems());

            ListItemsAdapter adapter = new ListItemsAdapter(list);
            rvItems.setAdapter(adapter);

            adapter.setOnItemClickCallback(this::showSelectedItems);
        }
        return view;
    }
    public ArrayList<Items> getListItems(){
        String[] dataName = getResources().getStringArray(R.array.item_name);
        String[] dataDescription = getResources().getStringArray(R.array.item_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.item_photo);
        ArrayList<Items> listItems = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            Items items = new Items();
            items.setName(dataName[i]);
            items.setDescription(dataDescription[i]);
            items.setPhoto(dataPhoto.getResourceId(i, -1));
            listItems.add((items));
        }
        return  listItems;
    }

    private void showRecyclerList(){
        rvItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        ListItemsAdapter listItemsAdapter = new ListItemsAdapter(list);

        listItemsAdapter.setOnItemClickCallback(this::showSelectedItems);
    }
    private void showSelectedItems (Items items){
        Intent intent = new Intent(requireActivity(), ItemDetailActivity.class);

        intent.putExtra("item_name", items.getName());
        intent.putExtra("item_description", items.getDescription());
        intent.putExtra("item_photo", items.getPhoto());

        startActivity(intent);
    }
}
