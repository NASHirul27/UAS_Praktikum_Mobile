package com.nashirul.uts_praktikum_mobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ItemDialog extends AppCompatDialogFragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        EditText editTextName;
        EditText editTextDesc;

        editTextName = view.findViewById(R.id.edit_name);
        editTextDesc = view.findViewById(R.id.edit_desc);

        builder.setView(view)
                .setTitle("Add New Item")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editTextName.getText().length()>0 && editTextDesc.getText().length()>0){
                            saveData(editTextName.getText().toString(), editTextDesc.getText().toString());
                            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            getActivity().recreate();
                        }else {
                            Toast.makeText(getActivity(), "Please fill in the necessary data!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

    private void saveData(String name, String desc) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("Description", desc);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    if (getContext() != null) {

                    }
                })
                .addOnFailureListener(e -> {
                    if (getContext() != null) {

                    }
                });
    }


}
