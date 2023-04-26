package com.example.Goalden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;


public class HomeScreen extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // Get the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin the fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create a new instance of HomeFragment
        HomeFragment homeFragment = new HomeFragment();

        // Add the fragment to the fragment container
        fragmentTransaction.add(R.id.fragment_container, homeFragment);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}