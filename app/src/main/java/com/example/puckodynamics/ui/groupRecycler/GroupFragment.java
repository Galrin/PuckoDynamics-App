package com.example.puckodynamics.ui.groupRecycler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.puckodynamics.AppDelegate;
import com.example.puckodynamics.R;
import com.example.puckodynamics.ui.scriptRecycler.ScriptFragment;
import com.example.puckodynamics.ui.toVostok.toVostokActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";


    public GroupFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GroupFragment newInstance() {
        GroupFragment fragment = new GroupFragment();
//       Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_group, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mAppContext = (AppDelegate) getContext().getApplicationContext();
       mGroupRecycler = v.findViewById(R.id.groupRecycler);

        mGroupRecycler.setAdapter(new GroupAdapter(this, mAppContext.getGroups()));
        mGroupRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //mGroupRecycler.addItemDecoration();

        mFab = v.findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), toVostokActivity.class));
            }
        });

        MaterialToolbar materialToolbar = v.findViewById(R.id.toolbar3);
        //materialToolbar.inflateMenu(R.menu.material_main);
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addGroup:
                        Toast.makeText(getContext(), "addGroup", Toast.LENGTH_SHORT).show();

                        break;
                }
                return false;
            }
        });

    }

    public void onRecyclerViewItemClick(int position) {
        Toast.makeText(getContext(), "aaa" + position, Toast.LENGTH_SHORT).show();
        getParentFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, ScriptFragment.newInstance(position)).commit();

        //new PopupWindow(getContext(), )
    }
}