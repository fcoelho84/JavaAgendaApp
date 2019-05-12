package br.coelho.agenda.Animations;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarCollapse {


    private float density;

    private SimpleDateFormat format = new SimpleDateFormat("dd MMM. yyyy");

    private AppBarLayout bar;

    private Toolbar toolbar;

    private ConstraintLayout content;

    private CompactCalendarView calendar;

    public void setAnimation(Context context, FloatingActionButton btn, AppBarLayout bar, Toolbar toolbar,
                             CompactCalendarView calendar, ConstraintLayout content) {

        this.density =  context.getResources().getDisplayMetrics().density;

        this.bar = bar;
        this.toolbar = toolbar;
        this.content = content;
        this.calendar = calendar;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCalendar();
            }
        });

        bar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(final AppBarLayout bar, final int i) {
                bar.post(new Runnable() {
                    @Override
                    public void run() {

                        if (Math.abs(i) - bar.getTotalScrollRange() == 0) {

                            openAnimCalendar();

                        }
                        else {

                            closeAnimCalendar();

                        }

                    }
                });
            }
        });



    }

    private void openAnimCalendar() {
        content.setPadding(0, (int) (55 * density), 0, 0);

        toolbar.setTitle("Calendário");

    }

    private void closeAnimCalendar() {
        if (toolbar.getTitle().toString().equals("Calendário")) {

            toolbar.setTitle(format.format(new Date()));

        }

        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date d) {

                toolbar.setTitle(format.format(d));
            }

            @Override
            public void onMonthScroll(Date d) {

                toolbar.setTitle(format.format(d));

            }
        });

        content.setPadding(0, (int) (290 * density), 0,0);

    }

    public void openCalendar() {

        this.bar.setExpanded(true);

    }

    public void closeCalendar() {

        this.bar.setExpanded(false);

    }







}
