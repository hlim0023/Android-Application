package com.example.Goalden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormatSymbols;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

public class EventEditActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;

    int hour, minute;
    private EditText eventNameET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);


        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(displaySelectedDate());

        timeButton = findViewById(R.id.timeButton);

        initWidgets();
    }

    private String displaySelectedDate() {
        int year = CalendarUtils.selectedDate.getYear();
        int month = CalendarUtils.selectedDate.getMonthValue();
        int day = CalendarUtils.selectedDate.getDayOfMonth();
        return makeDateString(day, month, year);
    }

    private void initWidgets() {
        eventNameET = findViewById(R.id.activityName);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = makeDateString(day, month+1, year);
                dateButton.setText(date);
            }
        };

        int year = CalendarUtils.selectedDate.getYear();
        int month = CalendarUtils.selectedDate.getMonthValue();
        month -= 1;
        int day = CalendarUtils.selectedDate.getDayOfMonth();
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }


    public void saveActivityAction(View view) {
        String activityName = eventNameET.getText().toString();
        Activity activity = new Activity(activityName, LocalTime.parse(timeButton.getText().toString()), CalendarUtils.toDate(dateButton.getText().toString()));
        Activity.activitiesList.add(activity);
        finish();
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}