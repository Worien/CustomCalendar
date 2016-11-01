package com.puhach.customcalendar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;


import com.puhach.customcalendar.models.MedicationItem;

import java.util.List;

/**
 * Created by user on 10/25/2016.
 */

public class MedicationCalendarView extends AbstractCalendarView {


    private List<MedicationItem> items;

    public MedicationCalendarView(Context context) {
        super(context);
    }

    public MedicationCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MedicationCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setItems(List<MedicationItem> items) {
        this.items = items;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MedicationCalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected View bindHeaderView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.view_calendar_header, this, false);
    }

    @Override
    protected View bindCalendarMonthView() {
        MedicationCalendarMonthView medicationCalendarMonthView = new MedicationCalendarMonthView(getContext());
        medicationCalendarMonthView.setMedicationItems(items);
        return medicationCalendarMonthView;
    }
}
