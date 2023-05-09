package com.example.Goalden;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    PieChart pieChart;

    public HomeFragment() {
        // Required empty public constructor
    }

    // ... (other methods, such as newInstance, onCreate, etc.)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the PieChart in the inflated layout
        pieChart = rootView.findViewById(R.id.pieChart);

        // Call the setupPieChart() method to set up the pie chart
        setupPieChart();

        return rootView;
    }

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(25f, "Acceptance"));
        entries.add(new PieEntry(15f, "Cognitive Defusion"));
        entries.add(new PieEntry(35f, "Being Present"));
        entries.add(new PieEntry(10f, "Self as Context"));
        entries.add(new PieEntry(30f, "Values"));
        entries.add(new PieEntry(20f, "Committed Action"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        String[] categories = {"Acceptance", "Cognitive Defusion", "Being Present", "Self as Context", "Values", "Committed Action"};

        // Define custom colors for the pie chart
        int[] customColors = {
                Color.rgb(244, 67, 54), // Acceptance
                Color.rgb(156, 39, 176), // Cognitive Defusion
                Color.rgb(3, 169, 244), // Being Present
                Color.rgb(255, 193, 7), // Self as Context
                Color.rgb(76, 175, 80), // Values
                Color.rgb(255, 87, 34) // Committed Action
        };

        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setColors(customColors);
        dataSet.setSliceSpace(2f); // Add a gap between categories
        dataSet.setSelectionShift(5f); // Set the amount a slice is shifted when selected

        dataSet.setValueTextSize(16f);

        PieData data = new PieData(dataSet);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/new_font.ttf");
        dataSet.setValueTypeface(typeface);
        pieChart.setEntryLabelTypeface(typeface);
        pieChart.setData(data);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.animateXY(500, 500);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));
//        pieChart.setDrawEntryLabels(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();

        // Customize the legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
//        Legend legend = pieChart.getLegend();
//        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        legend.setDrawInside(false);
//        legend.setXEntrySpace(7f);
//        legend.setYEntrySpace(0f);
//        legend.setYOffset(0f);
//        legend.setTypeface(typeface);
//        legend.setTextSize(14f);
//        legend.setTextColor(Color.WHITE);


        // Set a listener for when a value is selected
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) h.getX();
                String category = categories[index];
                openCategoryDetailFragment(category);
            }

            @Override
            public void onNothingSelected() {
                // Do nothing when nothing is selected
            }
        });

    }

    private void openCategoryDetailFragment(String category) {
        CategoryDetailFragment categoryDetailFragment = CategoryDetailFragment.newInstance(category);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.home_layout_frame, categoryDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}

//    //todo: link user's data
//    AnyChartView anyChartView;
//    String[] placeholders1 = {"1","2","3","4"};
//    int[] placeholders2 = {20000,10000,50000,40000};
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        // Find the AnyChartView in the inflated layout
//        anyChartView = rootView.findViewById(R.id.anyChartView);
//
//        // Call the setupChartView() method to set up the chart view
//        setupChartView();
//
//        return rootView;
//    }
//
//
//    private void setupChartView() {
//        Pie pie = AnyChart.pie();
//        List<DataEntry> dataEntries = new ArrayList<>();
//
//        for (int i=0; i<placeholders1.length; i++){
//            dataEntries.add(new ValueDataEntry(placeholders1[i],placeholders2[i]));
//        }
//        pie.data(dataEntries);
//        pie.title("Activities");
//        anyChartView.setChart(pie);
//    }
//}