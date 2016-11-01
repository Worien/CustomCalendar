package com.puhach.customcalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.puhach.customcalendar.models.BaseCalendarDayModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 10/24/2016.
 */

public abstract class AbstractCalendarMonthView extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener {

    public static final int COUNT_OF_DAYS_IN_WEEK = 7;

    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private int contentWidth;
    private int contentHeight;
    private LinearLayout lnrWeekContainer;
    protected Date targetDate;


    public AbstractCalendarMonthView(Context context) {
        super(context);
        init();
    }

    public AbstractCalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AbstractCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AbstractCalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        lnrWeekContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.view_calendar_month, this, true).findViewById(R.id.lnr_week_container);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        setupCalendarView();
    }

    private void setupCalendarView() {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(getTargetDate());
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, -dayOfMonth);
        int firstDayInWeek = cal.get(Calendar.DAY_OF_WEEK);
        initMonthView(firstDayInWeek, daysInMonth, cal);
    }


    private void initMonthView(int startDayOffset, int countOfDayInMonth, Calendar calFirstDayInMonth) {
        int countOfRows = (startDayOffset + countOfDayInMonth) / COUNT_OF_DAYS_IN_WEEK;
        if (!((startDayOffset + countOfDayInMonth) % COUNT_OF_DAYS_IN_WEEK == 0))
            countOfRows++;
        for (int week = 0; week < countOfRows; week++) {
            LinearLayout lnrDayContainer = new LinearLayout(getContext());
            lnrDayContainer.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getWidth() / COUNT_OF_DAYS_IN_WEEK, ViewGroup.LayoutParams.MATCH_PARENT, 1);
            //params.setMargins(3, 0, 3, 0);
            for (int day = 0; day < COUNT_OF_DAYS_IN_WEEK; day++) {
                int numberOfTheDay = COUNT_OF_DAYS_IN_WEEK*week+day;
                if ((week == 0 && day < startDayOffset) ||  numberOfTheDay >= countOfDayInMonth+startDayOffset) {
                    createDayView(lnrDayContainer, params, null);
                } else {
                    calFirstDayInMonth.add(Calendar.DAY_OF_MONTH, 1);
                    Date date = calFirstDayInMonth.getTime();
                    createDayView(lnrDayContainer, params, date);
                }
            }

            lnrWeekContainer.addView(lnrDayContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getHeight()/countOfRows));
        }
    }

    private void createDayView(LinearLayout lnrDayContainer, LinearLayout.LayoutParams params, Date date) {
        BaseCalendarDayModel baseCalendarModel = createModelForEachDay(date);
        AbstractCalendarDayView abstractCalendarDayView = getCalendarDayView();
        abstractCalendarDayView.setData(baseCalendarModel);
        lnrDayContainer.addView(abstractCalendarDayView);
        abstractCalendarDayView.setLayoutParams(params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calcContentSize();
    }

    private void calcContentSize() {
        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();

        contentWidth = getWidth() - paddingLeft - paddingRight;
        contentHeight = getHeight() - paddingTop - paddingBottom;
    }

    public void notifyDataSetChanges() {
        lnrWeekContainer.removeAllViews();
        setupCalendarView();
        invalidate();
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    protected abstract BaseCalendarDayModel createModelForEachDay(Date day);

    protected abstract AbstractCalendarDayView getCalendarDayView();

    protected abstract Date getTargetDate();

}
