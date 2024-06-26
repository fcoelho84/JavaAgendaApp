package br.coelho.agenda;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import com.github.clans.fab.FloatingActionButton;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;


import java.text.SimpleDateFormat;
import java.util.Date;

import br.coelho.agenda.Adapters.AppBarStateChangedListener;
import br.coelho.agenda.Animations.CircularReveal;
import br.coelho.agenda.Pages.CreateReminder;
import br.coelho.agenda.Pages.Reminders;

public class Root extends AppCompatActivity {

    // Privates

    private static final String ACTION_PALLETE = "PALLETE_MODE";
    private static final String ACTION_EDIT = "EDIT_MODE";

    private MenuItem MenuCalendar, MenuConfig, MenuDelete;

    // Public static

    public static SimpleDateFormat FormatDateTitle = new SimpleDateFormat("dd MMM. yyyy");

    public static Boolean ToolbarIsOpen = false, ClosedAnim = false;

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

            case R.id.ic_delete: onClickCloseWithAnim(); return true;

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

    // Open the fragment inside a frame layout
    public void openNewPage(Fragment fragemntPage) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.replace(R.id.Frame, fragemntPage, "FRAME_FRAG");
        transaction.addToBackStack( getCurrentDisplayedFragment() );
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

                if(state == State.COLLAPSED) {

                    if(!getCurrentDisplayedFragment().equals("CreateReminder")) {

                        MainToolbar.setTitle("Meus lembretes");

                    }

                    FabAction.hide(true);

                    ToolbarIsOpen = false;


                }else if(state == State.EXPANDED ) {

                    if(!getCurrentDisplayedFragment().equals("CreateReminder")) {

                        MainToolbar.setTitle(FormatDateTitle.format(new Date()));

                    }

                    FabAction.show(true);

                    ToolbarIsOpen = true;

                }


            }
        });
    }

    private void handleCalendarAnim() {

        ToolbarIsOpen = (ToolbarIsOpen) ? false : true;

        MainAppBar.setExpanded(ToolbarIsOpen);
    }

    public void onClickCloseWithAnim() {

        CreateReminder.CircularAnim.close();

        CreateReminder.CircularAnim.setAnimationListener(new CircularReveal.Listener() {
            @Override
            public void onAnimationEnd() {

                toolbarMenuItemChanged();

                MainToolbar.setTitle("Meus lembretes");

                MainAppBar.setExpanded(false);

                ClosedAnim = true;

                onBackPressed();
            }

            @Override
            public void onAnimationStart() {

            }
        });

    }

    private String getCurrentDisplayedFragment() {

        Fragment currentFrag = getFragmentManager().findFragmentByTag("FRAME_FRAG");

        if (currentFrag != null && currentFrag.isVisible()) {

            return currentFrag.getClass().getSimpleName();
        }

        return "";

    }

    @Override
    public void onBackPressed() {

        if (getCurrentDisplayedFragment().equals("CreateReminder") && !ClosedAnim) {

            onClickCloseWithAnim();

        } else {

            ClosedAnim = false;

            super.onBackPressed();

        }

    }


}

