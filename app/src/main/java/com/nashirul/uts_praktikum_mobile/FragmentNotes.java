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

public class FragmentNotes extends Fragment {
    private RecyclerView rvNotes;
    private ArrayList<Notes> list = new ArrayList<>();
    View view;
    public FragmentNotes(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.notes_fragment, container, false);
            rvNotes = view.findViewById(R.id.rv_notes);
            rvNotes.setHasFixedSize(true);
            rvNotes.setLayoutManager(new LinearLayoutManager(getActivity()));

            list.addAll(getListNotes());

            ListNotesAdapter adapter = new ListNotesAdapter(list);
            rvNotes.setAdapter(adapter);

            adapter.setOnItemClickCallback(this::showSelectedItems);
        }
        return view;
    }
    public ArrayList<Notes> getListNotes(){
        String[] dataName = getResources().getStringArray(R.array.note_name);
        String[] dataDescription = getResources().getStringArray(R.array.note_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.note_photo);
        ArrayList<Notes> listNotes = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++){
            Notes notes = new Notes();
            notes.setName(dataName[i]);
            notes.setDescription(dataDescription[i]);
            notes.setPhoto(dataPhoto.getResourceId(i, -1));
            listNotes.add((notes));
        }
        return  listNotes;
    }

    private void showRecyclerList(){
        rvNotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        ListNotesAdapter listNotesAdapter = new ListNotesAdapter(list);

        listNotesAdapter.setOnItemClickCallback(this::showSelectedItems);
    }
    private void showSelectedItems (Notes notes){
        Intent intent = new Intent(requireActivity(), NotesDetailActivity.class);

        intent.putExtra("notes_name", notes.getName());
        intent.putExtra("notes_description", notes.getDescription());
        intent.putExtra("notes_photo", notes.getPhoto());

        startActivity(intent);
    }
}
