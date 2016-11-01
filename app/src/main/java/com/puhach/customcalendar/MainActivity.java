package com.puhach.customcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.puhach.customcalendar.models.MedicationItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MedicationCalendarView medicationCalendarView = (MedicationCalendarView) findViewById(R.id.calendarView);
        List<MedicationItem> medicationItems = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            MedicationItem medicationItem = new MedicationItem();
            medicationItem.setCompleted(i%2 == 0);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -i);
            medicationItem.setCreatedAt(cal.getTime());
            medicationItem.setState(i%2 == 0?"open":"resolved");
            medicationItems.add(medicationItem);
        }
        medicationCalendarView.setItems(medicationItems);
    }
}
