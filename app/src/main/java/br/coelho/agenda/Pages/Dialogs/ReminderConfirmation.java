package br.coelho.agenda.Pages.Dialogs;


import android.os.Bundle;
import android.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.coelho.agenda.R;
import br.coelho.agenda.Root;

public class ReminderConfirmation extends DialogFragment {


    public static TextView DateConfirmationText, Confirm, SetTime;

    public static ReminderConfirmation newInstance(String dateClicked) {
        ReminderConfirmation frag = new ReminderConfirmation();
        Bundle args = new Bundle();
        args.putString("DateClicked", dateClicked);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View dialog = inflater.inflate(R.layout.dialog_reminder_confirmation, container, false);

        DateConfirmationText = dialog.findViewById(R.id.DateConfirmText);

        Confirm = dialog.findViewById(R.id.Yes);

        SetTime = dialog.findViewById(R.id.SetTime);

        setOnClickConfirmation();

        setOnClickSetTime();

        return dialog;
    }


    private void setOnClickConfirmation() {
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Root root = (Root)getActivity();

                dismiss();

                root.onClickCloseWithAnim();
            }
        });
    }

    private void setOnClickSetTime() {
        SetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
