package br.coelho.agenda.Pages;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Date;

import br.coelho.agenda.Animations.CircularReveal;
import br.coelho.agenda.R;
import br.coelho.agenda.Root;

public class CreateReminder extends Fragment {

    private static View MainView;

    private static ConstraintLayout Content;

    private static LinearLayout AnimLayout;

    public static CircularReveal CircularAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        MainView = inflater.inflate(R.layout.page_create_reminder, container, false);

        Content = MainView.findViewById(R.id.Content);

        AnimLayout = MainView.findViewById(R.id.LayoutCircularAnim);

        CircularAnim = new CircularReveal(MainView, AnimLayout, Content, this.getActivity());

        CircularAnim.open();

        CircularAnim.setAnimationListener(new CircularReveal.Listener() {
            @Override
            public void onAnimationEnd() {
                Root.FabAction.setImageDrawable(
                        ContextCompat.getDrawable(CreateReminder.this.getActivity(), R.drawable.ic_pallete_balck));

            }

            @Override
            public void onAnimationStart() {


            }
        });

        onClickCalendarDate();

        Root.FabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateReminder.this.getActivity(), "teste", Toast.LENGTH_SHORT).show();
            }
        });





        return MainView;
    }

    private void onClickCalendarDate() {

        Root.Calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date d) {
                Root.MainToolbar.setTitle(Root.FormatDateTitle.format(d));

                openDialogConfirmation(Root.FormatDateTitle.format(d));
            }

            @Override
            public void onMonthScroll(Date d) {
                Root.MainToolbar.setTitle(Root.FormatDateTitle.format(d));
            }
        });

    }

    private void openDialogConfirmation(String dateClicked) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        View view = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_confirmation_create_reminder, null);

        ((TextView) view.findViewById(R.id.DateConfirmText)).setText(dateClicked);

        builder.setView(view);

        final Dialog dialog = builder.create();

        (view.findViewById(R.id.Yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Root root = (Root)getActivity();

                dialog.dismiss();

                root.onClickDelete();
            }
        });

        (view.findViewById(R.id.CloseDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}
