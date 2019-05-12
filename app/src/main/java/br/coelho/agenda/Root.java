package br.coelho.agenda;

import android.app.FragmentTransaction;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;


import br.coelho.agenda.Animations.CalendarCollapse;
import br.coelho.agenda.Pages.Reminders;

public class Root extends AppCompatActivity {


    private CalendarCollapse CalendarCollapseAnim = new CalendarCollapse();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ic_calendar:

                CalendarCollapseAnim.openCalendar();


                return true;

            case R.id.ic_config:


                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_root);

        CalendarCollapseAnim.setAnimation(
                this,
                ((FloatingActionButton) findViewById(R.id.btnCalendar)),
                ((AppBarLayout) findViewById(R.id.appbar)),
                ((Toolbar) findViewById(R.id.toolbar)),
                ((CompactCalendarView) findViewById(R.id.calendar)),
                ((ConstraintLayout) findViewById(R.id.Frame)));


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getSupportActionBar().setDisplayShowHomeEnabled(false);

        openNewPage(new Reminders());

    }

    private void openNewPage(Fragment page) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame, page);
        transaction.commit();


    }


}