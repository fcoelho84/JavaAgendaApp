package br.coelho.agenda.Pages;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import br.coelho.agenda.Adapters.DataItem;
import br.coelho.agenda.Adapters.BaseListReminder;
import br.coelho.agenda.R;

public class Reminders extends Fragment {

    private ListView ReminderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.page_reminders, container, false);

        ReminderList = view.findViewById(R.id.ReminderList);

        DataItem item = new DataItem();

        item.setReminder(12, "sdffdgsdfghfsdgsdfgsdfgdfgfdgdfgfgfdgfdgfdgdfgghfdghfghfdghfgdhfdghfdghfdghfdghsdffdgsdfghfsdgsdfgsdfgdfgfdgdfgfgfdgfdgfdgdfgghfdghfghfdghfgdhfdghfdghfdghfdghsdffdgsdfghfsdgsdfgsdfgdfgfdgdfgfgfdgfdgfdgdfgghfdghfghfdghfgdhfdghfdghfdghfdgh23", "TE2323232323STE", new Date(), new Date(), "#ffffff", "#ffffff" );

        ArrayList<DataItem> ListItem = new ArrayList<>();

        ListItem.add(item);

        item = new DataItem();

        item.setReminder(12, "sdffdgsdfghfsdgsdfgsdfgdfgfdgdfgfgfdgfdgfdgdfgghfdghfghfdghfgdhfdghfdghfdghfdghsdffdgsdfghfsdgsdfgsdfgdfgfdgdfgfgfdgfdgfdgdfgghfdghfghfdghfgdhfdghfdghfdghfdghsdffdgsdfghfsdgsdfgsdfgdfgfdgdfgfgfdgfdgfdgdfgghfdghfghfdghfgdhfdghfdghfdghfdgh23", "TE2323232323STE", new Date(), new Date(), "#ffffff", "#ffffff" );


        ListItem.add(item);


        ReminderList.setAdapter(new BaseListReminder(ListItem, this.getActivity()));


        return view;
    }

}
