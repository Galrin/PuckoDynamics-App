package com.example.puckodynamics.ui.groupRecycler;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.puckodynamics.R;

import com.example.puckodynamics.data.model.Group;

public class GroupHolder extends RecyclerView.ViewHolder {

    GroupAdapter groupAdapter;
    Button mTextView;
    int position;

    public GroupHolder(View view) {
        super(view);

        mTextView = view.findViewById(R.id.group_text);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAdapter.onRecyclerViewItemClick(position);
            }
        });
    }

    public void bind(Group group, int position) {
        mTextView.setText(group.getName());
        this.position = position;
    }

    public GroupHolder build(GroupAdapter groupAdapter) {
        this.groupAdapter = groupAdapter;
        return this;
    }
}
