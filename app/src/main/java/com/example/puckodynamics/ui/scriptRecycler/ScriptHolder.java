package com.example.puckodynamics.ui.scriptRecycler;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.puckodynamics.R;
import com.example.puckodynamics.data.model.Group;
import com.example.puckodynamics.data.model.Script;

public class ScriptHolder extends RecyclerView.ViewHolder {

    ScriptAdapter groupAdapter;
    Button mTextView;
    int position;

    public ScriptHolder(View view) {
        super(view);

        mTextView = view.findViewById(R.id.group_text);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAdapter.onRecyclerViewItemClick(position);
            }
        });
    }

    public void bind(Script group, int position) {
        mTextView.setText(group.getName());
        this.position = position;
    }

    public ScriptHolder build(ScriptAdapter groupAdapter) {
        this.groupAdapter = groupAdapter;
        return this;
    }
}
