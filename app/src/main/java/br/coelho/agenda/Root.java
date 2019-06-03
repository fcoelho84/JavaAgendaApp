package br.coelho.agenda;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;


import java.text.SimpleDateFormat;
import java.util.Date;

import br.coelho.agenda.Adapters.AppBarStateChangedListener;
import br.coelho.agenda.Pages.Reminders;

public class Root extends AppCompatActivity {

    // Privates

    private static final String ACTION_PALLETE = "PALLETE_MODE";
    private static final String ACTION_EDIT = "EDIT_MODE";

    private Boolean isOpen = false;

    private MenuItem MenuCalendar, MenuConfig, MenuDelete;

    // Public static

    public static SimpleDateFormat FormatDateTitle = new SimpleDateFormat("dd MMM. yyyy");

    public static Toolbar MainToolbar;

    public static AppBarLayout MainAppBar;

    public static FloatingActionButton FabAction;

    public static CompactCalendarView Calendar;


    // Overrides

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.options_menu, menu);

        MenuCalendar = menu.findItem(R.id.ic_calendar);

        MenuConfig = menu.findItem(R.id.ic_config);

        MenuDelete = menu.findItem(R.id.ic_delete);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ic_calendar: handleCalendarAnim(); return true;

            case R.id.ic_config: return true;

            case R.id.ic_delete: onClickDelete(); return true;

            default: return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_root);

        MainToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(MainToolbar);

        MainAppBar = findViewById(R.id.appbar);

        FabAction = findViewById(R.id.fab);

        Calendar = findViewById(R.id.calendar);

        onStateChangedSetTitleAppBar();

        openNewPage(new Reminders());

    }

    // Methods Publics

    public void toolbarMenuItemChanged() {

        MenuCalendar.setVisible((MenuCalendar.isVisible()) ? false : true);

        MenuConfig.setVisible((MenuConfig.isVisible()) ? false : true);

        MenuDelete.setVisible((MenuDelete.isVisible()) ? false : true);
    }

    public void openNewPage(Fragment page) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame, page, "FRAME_FRAG");
        transaction.commit();


    }

    // Methods Private

    private void onStateChangedSetTitleAppBar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getSupportActionBar().setDisplayShowHomeEnabled(false);


        MainAppBar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                Log.e("TAG", getCurrentDisplayedFragment());

                if(!getCurrentDisplayedFragment().equals("CreateReminder")) {

                    if(state == State.COLLAPSED)

                        MainToolbar.setTitle("Meus lembretes");

                    else if(state == State.EXPANDED )

                        MainToolbar.setTitle(FormatDateTitle.format(new Date()));

                }
            }
        });
    }

    private void handleCalendarAnim() {

        if(isOpen)
            MainAppBar.setExpanded(true);
        else
            MainAppBar.setExpanded(false);

        isOpen = (isOpen) ? false : true;
    }

    private void onClickDelete() {

        openNewPage(new Reminders());

        toolbarMenuItemChanged();

        MainToolbar.setTitle("Meus lembretes");

        FabAction.hide();

        MainAppBar.setExpanded(false);
    }

    private String getCurrentDisplayedFragment() {

        Fragment currentFrag = getFragmentManager().findFragmentByTag("FRAME_FRAG");

        if (currentFrag != null && currentFrag.isVisible()) {

            return currentFrag.getClass().getSimpleName();
        }

        return "";

    }


}