package com.example.puckodynamics.ui.toVostok;

import androidx.appcompat.app.AppCompatActivity;
import com.example.puckodynamics.R;
import com.example.puckodynamics.ui.groupRecycler.GroupFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;

public class toVostokActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_vostok);
        LinearLayout wait = findViewById(R.id.ActivityToVostok_wait);
        LinearLayout done = findViewById(R.id.ActivityToVostok_done);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                wait.setVisibility(View.GONE);
                done.setVisibility(View.VISIBLE);


            }
        }, 3000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 5000);

    }
}