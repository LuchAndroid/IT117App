package com.example.it117app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

public class DisplayDialog extends AppCompatDialogFragment {

    private String title, message;
    public DisplayDialog(String title, String message){
        this.title = title;
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", (dialog, which) -> {

        });
        return builder.create();
    }

    public static void openDialog(String title, String message, FragmentManager manager, String tags){
        DisplayDialog display = new DisplayDialog(title, message);
        display.show(manager, tags);
    }
}
