package com.example.Goalden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Activity> taskList;
    private DatabaseReference myRef,mCondition;
    public TaskAdapter(ArrayList<Activity> taskList) {
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
        Activity task = taskList.get(position);
        holder.taskTitle.setText(task.getName());
        holder.taskCategory.setText(task.getType().toString());
        holder.taskCheckbox.setChecked(task.isComplete());
        holder.taskCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setComplete(isChecked);
                myRef = FirebaseDatabase.getInstance().getReference();
                mCondition = myRef.child("login/users");
                mCondition.child(UserInfo.loggedUser.getUserId()).child("activities").child(task.getActivity_id()).child("activityComplete").setValue(task.isComplete());

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle;
        TextView taskCategory;
        TextView taskDueDate;
        CheckBox taskCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskCategory = itemView.findViewById(R.id.task_category);
            taskCheckbox = itemView.findViewById(R.id.task_checkbox);
        }
    }
}
