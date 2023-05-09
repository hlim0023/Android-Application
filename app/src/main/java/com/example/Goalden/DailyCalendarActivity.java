package com.example.Goalden;

import static com.example.Goalden.CalendarUtils.selectedDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DailyCalendarActivity extends BaseActivity {

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_calendar);
        setupBottomNavigationView();
        initWidgets();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDayView();
    }

    private void setDayView() {
        monthDayText.setText(CalendarUtils.monthDayFromDate(selectedDate));
        String dayOfWeek = selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter();
    }

    private void setHourAdapter() {
        HourAdapter  hourAdapter = new HourAdapter(getApplicationContext(), hourEventList());
        hourListView.setAdapter(hourAdapter);
    }

    private ArrayList<HourEvent> hourEventList() {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0; hour < 24; hour++){
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Activity> activities = Activity.activitiesForDateAndTime(selectedDate, time);
            HourEvent hourEvent = new HourEvent(time, activities);
            list.add(hourEvent);
        }

        return list;
    }

    private void initWidgets() {
        monthDayText = findViewById(R.id.monthDayText);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        hourListView = findViewById(R.id.hourListView);
    }

    public void previousDayAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
        setDayView();
    }

    public void newActivityAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void finishAction(View view) {
        finish();
    }
}