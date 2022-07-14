package com.example.puckodynamics.ui.scriptRecycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puckodynamics.R;
import com.example.puckodynamics.data.model.Group;
import com.example.puckodynamics.data.model.Script;
import com.example.puckodynamics.ui.LaunchActivity;
import com.example.puckodynamics.ui.groupRecycler.GroupFragment;

import java.util.List;

public class ScriptAdapter extends RecyclerView.Adapter<ScriptHolder> {

    List<Script> groupList;
    ScriptFragment activity;

    public ScriptAdapter(ScriptFragment activity, List<Script> groupList) {
        this.groupList = groupList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ScriptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_group, parent, false);

        return new ScriptHolder(view).build(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScriptHolder holder, int position) {
        holder.bind(groupList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void onRecyclerViewItemClick(int position) {
        activity.onRecyclerViewItemClick(position);
    }


//
//    public void onRecyclerViewItemClick(int position) {
//        ((LaunchActivity) activity).onRecyclerViewItemClick(position);
//    }
}
