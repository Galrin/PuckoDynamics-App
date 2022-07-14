package com.example.puckodynamics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.puckodynamics.AppDelegate;
import com.example.puckodynamics.R;
import com.example.puckodynamics.ui.groupRecycler.GroupFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LaunchActivity extends AppCompatActivity {

    RecyclerView mGroupRecycler;
    AppDelegate mAppContext;
    FloatingActionButton mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.ActivityMain_fragment_container, GroupFragment.newInstance()).commit();

        }

        mAppContext = (AppDelegate) getApplicationContext();



    }

}