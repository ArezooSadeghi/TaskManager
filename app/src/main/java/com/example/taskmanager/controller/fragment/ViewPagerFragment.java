package com.example.taskmanager.controller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ViewPagerFragment extends Fragment {
    private FloatingActionButton mActionButtonAdd;
    private RecyclerView mRecyclerView;
    private TaskRepository mTaskRepository;
    private TaskAdapter mTaskAdapter;

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment confirmedOrdersFragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        confirmedOrdersFragment.setArguments(args);
        return confirmedOrdersFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        findViews(view);
        initViews();
        mActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "Tag");
            }
        });
        return view;
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTaskRepository = TaskRepository.getInstance();
        updateUI();

    }


    private void updateUI() {
        List<Task> tasks = mTaskRepository.getTasks();
        if (mTaskAdapter == null) {
            mTaskAdapter = new TaskAdapter(tasks);
        } else {
            mTaskAdapter.notifyDataSetChanged();
        }
        mRecyclerView.setAdapter(mTaskAdapter);
    }

    private void findViews(View view) {
        mActionButtonAdd = view.findViewById(R.id.btn_add);
        mRecyclerView = view.findViewById(R.id.recyclerView);
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewDate;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txt_row_title);
            mTextViewDate = itemView.findViewById(R.id.txt_row_date);
        }

        public void bindTask(Task task) {
            mTextViewTitle.setText(task.getTitle());
        }
    }

    public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.row_task, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            holder.bindTask(mTasks.get(position));

        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}