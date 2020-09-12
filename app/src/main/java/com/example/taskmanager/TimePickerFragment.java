package com.example.taskmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimePickerFragment extends DialogFragment {

    public TimePickerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_time_picker, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setPositiveButton(android.R.string.ok, null).setPositiveButton(android.R.string.ok, null).setView(view).create();
        return alertDialog;

    }
}