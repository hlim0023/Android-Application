package com.example.Goalden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class EventEditActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button timeButton;

    private Spinner mySpinner;

    int hour, minute;
    private EditText eventNameET;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        myRef = FirebaseDatabase.getInstance().getReference().child("login/users").child(UserInfo.loggedUser.getUserId());

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(displaySelectedDate());

        timeButton = findViewById(R.id.timeButton);

        LocalTime currentTime = LocalTime.now();
        timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", currentTime.getHour(), currentTime.getMinute()));

        initWidgets();

        initCategoryPicker();
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

    private void initCategoryPicker(){
        mySpinner = findViewById(R.id.my_spinner);
        String[] options = new String[ActivityType.values().length];

        for(int i = 0; i < ActivityType.values().length; i++){
            options[i] = ActivityType.values()[i].toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + String.format("%02d", day) + " " + year;
    }

    private String getMonthFormat(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }


    public void saveActivityAction(View view) {
        String activityName = eventNameET.getText().toString();
//        Activity activity = new Activity(
//                activityName,
//                LocalTime.parse(timeButton.getText().toString()),
//                CalendarUtils.toDate(dateButton.getText().toString()),
//                ActivityType.valueOf(mySpinner.getSelectedItem().toString())
//        );
        String activityKey = myRef.child("activities").push().getKey();
        DatabaseReference act = myRef.child("activities").child(activityKey);
        HashMap<String, Object> keyValuePairs = new HashMap<>();
        keyValuePairs.put("activityName", activityName);
        keyValuePairs.put("activityTime", timeButton.getText().toString());
        keyValuePairs.put("activityDate", dateButton.getText().toString());
        keyValuePairs.put("activityCategory", mySpinner.getSelectedItem().toString());
        act.setValue(keyValuePairs);
//        act.child("activityName").setValue(activityName);
//        act.child("activityTime").setValue(timeButton.getText().toString());
//        act.child("activityDate").setValue(dateButton.getText().toString());
//        act.child("activityCategory").setValue(mySpinner.getSelectedItem().toString());
        Log.d("SAVEACTIVITY123", "saveActivityAction: DONE");
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

        LocalTime currentTime = LocalTime.now();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, currentTime.getHour(), currentTime.getMinute(), true);
        //timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
        //timePickerDialog.setTitle(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
        timePickerDialog.show();
    }
}