package com.example.Goalden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends ArrayAdapter<HourEvent> {

    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents) {
        super(context, 0, hourEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HourEvent activity = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell, parent, false);
        }

        setHour(convertView, activity.time);
        setEvents(convertView, activity.activities);

        return convertView;
    }

    private void setEvents(View convertView, ArrayList<Activity> activities) {
        TextView event1 = convertView.findViewById(R.id.event1);
        TextView event2 = convertView.findViewById(R.id.event2);
        TextView event3 = convertView.findViewById(R.id.event3);

        if(activities.size() == 0) {
            hideEvent(event1);
            hideEvent(event2);
            hideEvent(event3);
        } else if (activities.size() == 1){
            setEvent(event1, activities.get(0));
            hideEvent(event2);
            hideEvent(event3);
        }else if (activities.size() == 2){
            setEvent(event1, activities.get(0));
            setEvent(event2, activities.get(1));
            hideEvent(event3);
        }else if (activities.size() == 3){
            setEvent(event1, activities.get(0));
            setEvent(event2, activities.get(1));
            setEvent(event3, activities.get(2));
        } else{
            setEvent(event1, activities.get(0));
            setEvent(event2, activities.get(1));
            event3.setVisibility(View.VISIBLE);
            String eventsNotShown = String.valueOf(activities.size() - 2);
            eventsNotShown += "More Events";
            event3.setText(eventsNotShown);
        }

    }

    private void setEvent(TextView textView, Activity activity) {
        textView.setText(activity.getName());
        textView.setVisibility(View.VISIBLE);
    }

    private void hideEvent(TextView tv) {
        tv.setVisibility(View.INVISIBLE);
    }

    private void setHour(View convertView, LocalTime time) {
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        timeTV.setText(CalendarUtils.formattedShortTime(time));
    }
}
