package com.example.puckodynamics.ui.scriptRecycler;

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
import android.widget.Toast;

import com.example.puckodynamics.AppDelegate;
import com.example.puckodynamics.R;
import com.example.puckodynamics.ui.groupRecycler.GroupAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScriptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScriptFragment extends Fragment {

    RecyclerView mGroupRecycler;
    AppDelegate mAppContext;
    FloatingActionButton mFab;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
//    private String mParam2;

    public ScriptFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ScriptFragment newInstance(int position) {
        ScriptFragment fragment = new ScriptFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
    //        mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_script, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mAppContext = (AppDelegate) getContext().getApplicationContext();
        mGroupRecycler = v.findViewById(R.id.groupRecycler);

        mGroupRecycler.setAdapter(new ScriptAdapter(this, mAppContext.getScripts()));
        mGroupRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //mGroupRecycler.addItemDecoration();

        mFab = v.findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    }
}