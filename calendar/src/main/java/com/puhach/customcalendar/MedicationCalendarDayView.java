package com.puhach.customcalendar;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.puhach.customcalendar.models.BaseCalendarDayModel;
import com.puhach.customcalendar.models.CalendarDayModel;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 10/24/2016.
 */

public class MedicationCalendarDayView extends AbstractCalendarDayView {

    private CalendarDayModel calendarDayModel;
    private TextView txtDayNumber;
    private ImageView imgIcon;
    private View rootView;

    public MedicationCalendarDayView(Context context) {
        super(context);
    }

    public MedicationCalendarDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MedicationCalendarDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MedicationCalendarDayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    int getLayoutResource() {
        return R.layout.view_calendar_day;
    }

    @Override
    void configDayView(View rootDayView) {
        txtDayNumber = (TextView) rootDayView.findViewById(R.id.txt_day_of_month);
        imgIcon = (ImageView) rootDayView.findViewById(R.id.img_icon);
        rootView = rootDayView;
    }

    @Override
    void setData(BaseCalendarDayModel baseCalendarModel) {
        if (baseCalendarModel == null)
            return;
        this.calendarDayModel = (CalendarDayModel) baseCalendarModel;
        if (calendarDayModel.getDayDate() != null) {
            if (MedicationCalendarMonthView.isTheSameDay(calendarDayModel.getDayDate(), new Date())) {
                rootView.setBackground(getResources().getDrawable(R.drawable.background_calendar_target_date));
                txtDayNumber.setTextColor(getResources().getColor(R.color.blue));
                txtDayNumber.setTypeface(null, Typeface.BOLD);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(calendarDayModel.getDayDate());
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            txtDayNumber.setText(String.valueOf(dayOfMonth));
            if (calendarDayModel.getDayState() == CalendarDayModel.DayState.UNKNOWN) {
                imgIcon.setVisibility(INVISIBLE);
            } else if (calendarDayModel.getDayState() == CalendarDayModel.DayState.RESOLVED_TRUE) {
                imgIcon.setVisibility(VISIBLE);
                imgIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
            } else if (calendarDayModel.getDayState() == CalendarDayModel.DayState.RESOLVED_FALSE) {
                imgIcon.setVisibility(VISIBLE);
                imgIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_highlight_off_black_24dp));
                txtDayNumber.setTextColor(getResources().getColor(R.color.red));
            } else if (calendarDayModel.getDayState() == CalendarDayModel.DayState.OPEN && !MedicationCalendarMonthView.isTheSameDay(calendarDayModel.getDayDate(), new Date())) {
                imgIcon.setVisibility(VISIBLE);
                imgIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_help_black_24dp));
                rootView.setBackground(getResources().getDrawable(R.drawable.background_calendar_open_date));
            } else if (calendarDayModel.getDayState() == CalendarDayModel.DayState.OPEN && MedicationCalendarMonthView.isTheSameDay(calendarDayModel.getDayDate(), new Date())) {
                imgIcon.setVisibility(VISIBLE);
                imgIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_help_black_24dp));
                rootView.setBackground(getResources().getDrawable(R.drawable.background_calendar_open_target_date));
            } else {
                imgIcon.setVisibility(INVISIBLE);
            }
        }
    }

}
