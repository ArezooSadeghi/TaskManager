package com.example.taskmanager.controller.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.taskmanager.DatePickerFragment;
import com.example.taskmanager.R;
import com.example.taskmanager.TimePickerFragment;
import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

import java.util.List;

public class DialogFragment extends androidx.fragment.app.DialogFragment {
    private EditText mEditTextSubject, mEditTextDescription;
    private Button mButtonDate, mButtonTime;
    private CheckBox mCheckBoxState;
    private TaskRepository mTaskRepository;
    private Task mTask;


    public DialogFragment() {
    }

    public static DialogFragment newInstance() {
        DialogFragment dialogFragment = new DialogFragment();
        Bundle args = new Bundle();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_dialog, null);
        findViews(view);
        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "Tag");
            }
        });
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getActivity().getSupportFragmentManager(), "Tag");
            }
        });
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle("Title")
                .setPositiveButton(android.R.string.ok
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mTaskRepository = TaskRepository.getInstance();
                                List<Task> tasks = mTaskRepository.getTasks();
                                Task task = new Task();
                                task.setTitle(mEditTextSubject.getText().toString());
                                task.setDescription(mEditTextDescription.getText().toString());
                                if (mCheckBoxState.isChecked())
                                    task.setState(State.DONE);
                                mTaskRepository.create(task);
                            }
                        })
                .setNegativeButton(android.R.string.cancel, null).setView(view).create();
        return alertDialog;

    }

    private void findViews(View view) {
        mEditTextSubject = view.findViewById(R.id.txt_subject);
        mEditTextDescription = view.findViewById(R.id.txt_description);
        mButtonDate = view.findViewById(R.id.btn_date);
        mButtonTime = view.findViewById(R.id.btn_time);
        mCheckBoxState = view.findViewById(R.id.checkbox_state);
    }
}