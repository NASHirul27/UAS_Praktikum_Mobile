package com.nashirul.uts_praktikum_mobile;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FragmentItems extends Fragment {
    private RecyclerView rvItems;
    private FloatingActionButton item_add_button;
    private ArrayList<Items> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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
            adapter.setOnItemDelClickCallback(this::delItem);
            item_add_button = view.findViewById(R.id.item_add_button);

            db.collection("users")
                            .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            list.clear();
                                            if (task.isSuccessful()){
                                                for (QueryDocumentSnapshot document : task.getResult()){
                                                    Items items = new Items(document.getString("name"), document.getString("Description"), document.getId());
                                                    list.add(items);
                                                }
                                                adapter.notifyDataSetChanged();
                                            }else {
                                                Toast.makeText(getActivity().getApplicationContext(), "Data gagal di ambil!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

            item_add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog();
                }
            });
        }
        return view;
    }

    public void openDialog(){
        ItemDialog itemDialog = new ItemDialog();
        itemDialog.show(getActivity().getSupportFragmentManager(), "item dialog" );
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

        startActivity(intent);
    }
    private void delItem(Items items){
        String id = items.getItemId();
        db.collection("users").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getActivity().getApplicationContext(), "Failed Delete Item", Toast.LENGTH_SHORT).show();
                        }else {
                            getActivity().recreate();
                        }
                    }
                });
    }
}
