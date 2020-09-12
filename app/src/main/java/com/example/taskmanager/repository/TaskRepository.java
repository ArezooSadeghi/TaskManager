package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sInstance;
    private List<Task> mTasks = new ArrayList<>();

    private TaskRepository() {
    }

    public static TaskRepository getInstance() {
        if (sInstance == null) {
            sInstance = new TaskRepository();
        }
        return sInstance;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public void create(Task task) {
        mTasks.add(task);
    }

    public Task read(UUID id) {
        for (Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void update(Task task) {
        Task findTask = read(task.getId());
        findTask.setTitle(task.getTitle());
        findTask.setState(task.getState());
        findTask.setDescription(task.getDescription());
    }

    public void delete(Task task) {
        for (int i = 0; i < mTasks.size(); i++) {
            if (mTasks.get(i).getId().equals(task.getId())) {
                mTasks.remove(task);
                return;
            }
        }
    }
}
