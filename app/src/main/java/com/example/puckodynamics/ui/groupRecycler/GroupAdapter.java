package com.example.puckodynamics.ui.groupRecycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puckodynamics.R;
import com.example.puckodynamics.data.model.Group;
import com.example.puckodynamics.ui.LaunchActivity;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {

    List<Group> groupList;
    GroupFragment activity;

    public GroupAdapter(GroupFragment activity, List<Group> groupList) {
        this.groupList = groupList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_group, parent, false);

        return new GroupHolder(view).build(this);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        holder.bind(groupList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }



    public void onRecyclerViewItemClick(int position) {
        activity.onRecyclerViewItemClick(position);
    }
}
