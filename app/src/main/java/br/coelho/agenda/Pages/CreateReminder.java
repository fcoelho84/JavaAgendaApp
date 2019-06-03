package br.coelho.agenda.Pages;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Date;

import br.coelho.agenda.R;
import br.coelho.agenda.Root;

public class CreateReminder extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Root.FabAction.setImageDrawable(ContextCompat.getDrawable(this.getActivity(), R.drawable.ic_pallete_balck));

        Root.FabAction.show();

        onClickCalendarDate();


        Root.FabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateReminder.this.getActivity(), "teste", Toast.LENGTH_SHORT).show();
            }
        });





        return inflater.inflate(R.layout.page_create_reminder, container, false);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        View view = this.getActivity().getLayoutInflater().inflate(R.layout.dialog_confirmation_create_reminder, null);

        ((TextView) view.findViewById(R.id.DateConfirm)).setText(dateClicked);

        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.show();

    }

}
