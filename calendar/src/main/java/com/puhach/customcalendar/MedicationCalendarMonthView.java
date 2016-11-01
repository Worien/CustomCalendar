package com.puhach.customcalendar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;


import com.puhach.customcalendar.models.BaseCalendarDayModel;
import com.puhach.customcalendar.models.CalendarDayModel;
import com.puhach.customcalendar.models.MedicationItem;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 10/24/2016.
 */

public class MedicationCalendarMonthView extends AbstractCalendarMonthView {

    private List<MedicationItem> medicationItems;

    public MedicationCalendarMonthView(Context context) {
        super(context);
    }

    public MedicationCalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MedicationCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MedicationCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMedicationItems(List<MedicationItem> medicationItems) {
        this.medicationItems = medicationItems;
        notifyDataSetChanges();
    }

    @Override
    protected BaseCalendarDayModel createModelForEachDay(Date day) {
        CalendarDayModel calendarDayModel = null;
        if (medicationItems == null)
            return new CalendarDayModel(day, CalendarDayModel.DayState.UNKNOWN);
        for (MedicationItem medicationItem : medicationItems) {
            if (isTheSameDay(medicationItem.getCreatedAt(), day)) {
                switch (medicationItem.getState()) {
                    case "open":
                        calendarDayModel = new CalendarDayModel(day, CalendarDayModel.DayState.OPEN);
                        break;
                    case "resolved":
                        if (medicationItem.isCompleted() != null && medicationItem.isCompleted())
                            calendarDayModel = new CalendarDayModel(day, CalendarDayModel.DayState.RESOLVED_TRUE);
                        if (medicationItem.isCompleted() != null && !medicationItem.isCompleted())
                            calendarDayModel = new CalendarDayModel(day, CalendarDayModel.DayState.RESOLVED_FALSE);
                        break;
                }
            }
        }
        if (calendarDayModel == null)
            calendarDayModel = new CalendarDayModel(day, CalendarDayModel.DayState.UNKNOWN);
        return calendarDayModel;
    }

    @Override
    protected AbstractCalendarDayView getCalendarDayView() {
        return new MedicationCalendarDayView(getContext());
    }

    @Override
    protected Date getTargetDate() {
        if (targetDate == null)
            return new Date();
        return targetDate;
    }

    public static boolean isTheSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }
}
