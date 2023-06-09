package com.example.Goalden;

import static com.example.Goalden.CalendarUtils.daysInWeekArray;
import static com.example.Goalden.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements  CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecycleView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();
    }

    private void initWidgets() {
        calendarRecycleView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecycleView.setLayoutManager(layoutManager);
        calendarRecycleView.setAdapter(calendarAdapter );
        setEventAdapter();
    }



    public void previousWeekAction(View view){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setWeekView();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Activity> dailyActivities = Activity.activitiesForDate(CalendarUtils.selectedDate);
        EventAdapter adapter = new EventAdapter(getApplicationContext(), dailyActivities);
        eventListView.setAdapter(adapter);
    }

    public void newActivityAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void dailyAction(View view) {
        startActivity(new Intent(this, DailyCalendarActivity.class));
    }

    public void finishAction(View view) {
        finish();
    }
}