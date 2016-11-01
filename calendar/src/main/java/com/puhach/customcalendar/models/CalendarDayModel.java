package com.puhach.customcalendar.models;

import java.util.Date;

/**
 * Created by user on 10/24/2016.
 */

public class CalendarDayModel extends BaseCalendarDayModel {

    private  Date dayDate;
    private  DayState dayState;

    public CalendarDayModel(Date dayDate, DayState dayState) {
        this.dayDate = dayDate;
        this.dayState = dayState;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public DayState getDayState() {
        return dayState;
    }

    public enum DayState {
        RESOLVED_FALSE, RESOLVED_TRUE, OPEN, UNKNOWN
    }
}
