package com.example.puckodynamics.ui.scriptRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puckodynamics.R;
import com.example.puckodynamics.data.model.Device;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceHolder> {

    List<Device> groupList;
    DeviceFragment activity;

    public DeviceAdapter(DeviceFragment activity, List<Device> groupList) {
        this.groupList = groupList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_group, parent, false);

        return new DeviceHolder(view).build(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {
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
