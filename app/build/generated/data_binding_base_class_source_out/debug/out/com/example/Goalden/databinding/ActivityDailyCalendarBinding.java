// Generated by view binder compiler. Do not edit!
package com.example.Goalden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Goalden.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDailyCalendarBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView dayOfWeekTV;

  @NonNull
  public final ListView hourListView;

  @NonNull
  public final TextView monthDayText;

  private ActivityDailyCalendarBinding(@NonNull LinearLayout rootView,
      @NonNull TextView dayOfWeekTV, @NonNull ListView hourListView,
      @NonNull TextView monthDayText) {
    this.rootView = rootView;
    this.dayOfWeekTV = dayOfWeekTV;
    this.hourListView = hourListView;
    this.monthDayText = monthDayText;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDailyCalendarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDailyCalendarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_daily_calendar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDailyCalendarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dayOfWeekTV;
      TextView dayOfWeekTV = ViewBindings.findChildViewById(rootView, id);
      if (dayOfWeekTV == null) {
        break missingId;
      }

      id = R.id.hourListView;
      ListView hourListView = ViewBindings.findChildViewById(rootView, id);
      if (hourListView == null) {
        break missingId;
      }

      id = R.id.monthDayText;
      TextView monthDayText = ViewBindings.findChildViewById(rootView, id);
      if (monthDayText == null) {
        break missingId;
      }

      return new ActivityDailyCalendarBinding((LinearLayout) rootView, dayOfWeekTV, hourListView,
          monthDayText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
