package com.example.puckodynamics.ui.scriptRecycler;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.puckodynamics.R;
import com.example.puckodynamics.data.model.Device;

public class DeviceHolder extends RecyclerView.ViewHolder {

    DeviceAdapter groupAdapter;
    Button mTextView;
    int position;

    public DeviceHolder(View view) {
        super(view);

        mTextView = view.findViewById(R.id.group_text);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAdapter.onRecyclerViewItemClick(position);
            }
        });
    }

    public void bind(Device group, int position) {
        mTextView.setText(group.getName());
        this.position = position;
    }

    public DeviceHolder build(DeviceAdapter groupAdapter) {
        this.groupAdapter = groupAdapter;
        return this;
    }
}
