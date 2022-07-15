package com.example.puckodynamics.ui.groupRecycler;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.puckodynamics.AppDelegate;
import com.example.puckodynamics.R;
import com.example.puckodynamics.ui.scriptRecycler.ScriptFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {
    RecyclerView mGroupRecycler;
    AppDelegate mAppContext;
    FloatingActionButton mFab;
    // TODO: Rename parameter arguments, choose names that match


    public GroupFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mAppContext = (AppDelegate) getContext().getApplicationContext();
        mGroupRecycler = v.findViewById(R.id.groupRecycler);

        mGroupRecycler.setAdapter(new GroupAdapter(this, mAppContext.getGroups()));
        mGroupRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //mGroupRecycler.addItemDecoration();

        mFab = v.findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(v1 -> {
            View customView = getLayoutInflater().inflate(R.layout.group_create_popup, null);

            Button closePopupBtn = customView.findViewById(R.id.groupCreateButton);

            EditText edit = customView.findViewById(R.id.groupName);

            //instantiate popup window
            PopupWindow popupWindow = new PopupWindow(customView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setFocusable(true);
            //display the popup window
            popupWindow.showAtLocation(v1, Gravity.CENTER, 0, 0);

            //close the popup window on button click
            closePopupBtn.setOnClickListener(v11 -> {
                FileOutputStream fos;
                try {
                    fos = getContext().openFileOutput(edit.getText().toString(), Context.MODE_PRIVATE);
                    fos.write("aa".getBytes(StandardCharsets.UTF_8));
                    fos.close();
                    mAppContext.revalidateGroups();
                    mGroupRecycler.requestLayout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popupWindow.dismiss();
            });
        });

    }

    public void onRecyclerViewItemClick(int position) {
        Toast.makeText(getContext(), "aaa" + position, Toast.LENGTH_SHORT).show();
        getParentFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, ScriptFragment.newInstance(position)).commit();

    }
}