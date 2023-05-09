package com.example.Goalden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTitle.setText(task.getTitle());
        holder.taskDescription.setText(task.getDescription());
        holder.taskDueDate.setText("Due Date: " + task.getDueDate());
        holder.taskCheckbox.setChecked(task.isCompleted());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle;
        TextView taskDescription;
        TextView taskDueDate;
        CheckBox taskCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskDescription = itemView.findViewById(R.id.task_description);
            taskDueDate = itemView.findViewById(R.id.task_due_date);
            taskCheckbox = itemView.findViewById(R.id.task_checkbox);
        }
    }
}
