package com.puhach.customcalendar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.puhach.customcalendar.models.BaseCalendarDayModel;


/**
 * Created by user on 10/24/2016.
 */

public abstract class AbstractCalendarDayView extends FrameLayout {
    public AbstractCalendarDayView(Context context) {
        super(context);
        init();
    }

    public AbstractCalendarDayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AbstractCalendarDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AbstractCalendarDayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(getLayoutResource(), this, true);
        configDayView(this);
    }

    abstract void setData(BaseCalendarDayModel baseCalendarModel);
    abstract int getLayoutResource();
    abstract void configDayView(View viewOfTheDay);
}
