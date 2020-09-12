package com.example.taskmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Date;

public class DatePickerFragment extends DialogFragment {
    private DatePicker mDatePicker;

    public DatePickerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_date_picker, null);
        findViews(view);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setPositiveButton(android.R.string.ok, null).setView(view).create();
        return alertDialog;

    }

    private void findViews(View view) {
        mDatePicker = view.findViewById(R.id.datepicker);
    }
}