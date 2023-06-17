package com.example.timetodo.ToDoAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timetodo.AddNewTask;
import com.example.timetodo.MainActivity;
import com.example.timetodo.R;
import com.example.timetodo.ToDoModel.ToDoModel;
import com.example.timetodo.Utils.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> todoList;
    private MainActivity activity;

    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, MainActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        db.openDatabase();
        ToDoModel item = todoList.get(holder.getAdapterPosition());
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int currentPosition = holder.getAdapterPosition();
                if (isChecked) {
                    db.updateStatus(todoList.get(currentPosition).getId(), 1);
                } else {
                    db.updateStatus(todoList.get(currentPosition).getId(), 0);
                }
            }
        });

        if (item.getDueTime() != null && !item.getDueTime().isEmpty()) {
            holder.dueDateLabel.setVisibility(View.VISIBLE);
            holder.dueDateTime.setVisibility(View.VISIBLE);
            holder.dueDateTime.setText("Napraviti do: " + item.getDueTime());
        } else {
            holder.dueDateLabel.setVisibility(View.GONE);
            holder.dueDateTime.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    editItem(currentPosition);
                }
            }
        });
    }

    public int getItemCount() {
        return todoList.size();
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return activity;
    }

    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;
        TextView dueDateLabel;
        TextView dueDateTime;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckbox);
            dueDateLabel = view.findViewById(R.id.todoDueDateLabel);
            dueDateTime = view.findViewById(R.id.todoTime);
        }
    }
}
