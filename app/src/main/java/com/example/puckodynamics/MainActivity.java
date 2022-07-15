package com.example.puckodynamics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.puckodynamics.ui.toVostok.toVostokActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.puckodynamics.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
                        View customView = getLayoutInflater().inflate(R.layout.group_create_popup, null);

            Button closePopupBtn = customView.findViewById(R.id.groupCreateButton);

            EditText edit = customView.findViewById(R.id.groupName);

            //instantiate popup window
            PopupWindow popupWindow = new PopupWindow(customView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setFocusable(true);
            //display the popup window
            popupWindow.showAtLocation(item.getActionView(), Gravity.CENTER, 0, 0);

            //close the popup window on button click
            closePopupBtn.setOnClickListener(v11 -> {
                FileOutputStream fos;
                try {
                    fos = getApplicationContext().openFileOutput(edit.getText().toString(), Context.MODE_PRIVATE);
                    fos.write("aa".getBytes(StandardCharsets.UTF_8));
                    fos.close();
                    ((AppDelegate)getApplicationContext()).revalidateGroups();
                   // mGroupRecycler.requestLayout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                popupWindow.dismiss();
            });
            return true;
        }

        else if(id == R.id.action_export) {
            startActivity(new Intent(MainActivity.this, toVostokActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
//NavHostFragment.findNavController(FirstFragment.this)
//                .navigate(R.id.action_FirstFragment_to_SecondFragment));
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}