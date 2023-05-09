package com.example.Goalden;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {
    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        taskAdapter = new TaskAdapter(UserInfo.loggedUser.getActivities());
        tasksRecyclerView.setAdapter(taskAdapter);

        Button addTaskButton = view.findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(v -> {
            // Add new task logic here
        });

        return view;
    }
}