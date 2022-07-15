package com.example.puckodynamics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.puckodynamics.AppDelegate;
import com.example.puckodynamics.R;
import com.example.puckodynamics.data.model.Device;
import com.example.puckodynamics.ui.groupRecycler.GroupFragment;
import com.example.puckodynamics.ui.scriptRecycler.DeviceFragment;
import com.example.puckodynamics.ui.splashscreen.SplashFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class LaunchActivity extends AppCompatActivity {

    RecyclerView mGroupRecycler;
    AppDelegate mAppContext;
    FloatingActionButton mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.ActivityMain_fragment_container, SplashFragment.newInstance()).commit();

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, DeviceFragment.newInstance()).commit();

                }
            }, 3000);

        }

        mAppContext = (AppDelegate) getApplicationContext();
//        TabLayout tabLayout = findViewById(R.id.mainTab);
//        tabLayout.addTab(tabLayout.newTab().setText("Усройства"));
//        tabLayout.addTab(tabLayout.newTab().setText("Сценарии"));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        //bottomNavigationView.setOnNavigationItemSelectedListener();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.scripts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, GroupFragment.newInstance()).commit();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, DeviceFragment.newInstance()).commit();
                        break;
                }
                return false;
            }
        });
        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.scripts:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, GroupFragment.newInstance()).commit();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ActivityMain_fragment_container, DeviceFragment.newInstance()).commit();
                        break;
                }
            }
        });

    }

}