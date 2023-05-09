package com.example.Goalden;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {
    protected BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    protected void setupBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_item:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.calendar_item:
                    Intent intent = new Intent(BaseActivity.this, CalendarView.class);
                    startActivity(intent);
                    break;
                case R.id.tasks_item:
                    replaceFragment(new TasksFragment());
                    break;
                case R.id.profile_item:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }
    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_layout_frame, fragment)
                .commit();
    }
}