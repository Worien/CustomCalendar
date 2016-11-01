package com.puhach.customcalendar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 10/25/2016.
 */

public abstract class AbstractCalendarView extends FrameLayout {

    public static int CURRENT_MONTH_NUMBER = 1000;


    private PagerAdapter pagerAdapter;
    private OnMonthSwipeListener onMonthSwipeListener;
    private ViewPager vp;

    public AbstractCalendarView(Context context) {
        super(context);
        init();
    }

    public AbstractCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AbstractCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AbstractCalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void notifyDatasetChanges(){
        vp.getAdapter().notifyDataSetChanged();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_calendar, this, true);
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setAdapter(new CalendarPagerAdapter(getContext()));
        vp.setCurrentItem(vp.getAdapter().getCount() - 1);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (onMonthSwipeListener != null){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    cal.add(Calendar.MONTH, -(CURRENT_MONTH_NUMBER - position - 1));
                    onMonthSwipeListener.monthSwiped(cal.getTime());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ((ViewGroup) findViewById(R.id.rl_header)).addView(bindHeaderView());
    }

    public void setOnMonthSwipeListener(OnMonthSwipeListener onMonthSwipeListener) {
        this.onMonthSwipeListener = onMonthSwipeListener;
    }

    protected abstract View bindHeaderView();

    protected abstract View bindCalendarMonthView();


    public class CalendarPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CalendarPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            AbstractCalendarMonthView layout = (AbstractCalendarMonthView) bindCalendarMonthView();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.MONTH, -(CURRENT_MONTH_NUMBER - position - 1));
            Log.d("CalendarPagerAdapter", "date = " + new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
            layout.setTargetDate(cal.getTime());
            collection.addView(layout);

            return layout;
        }
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return CURRENT_MONTH_NUMBER;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    public interface OnMonthSwipeListener {
        void monthSwiped(Date date);
    }
}
