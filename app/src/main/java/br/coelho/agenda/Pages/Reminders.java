package br.coelho.agenda.Pages;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;
import java.util.Date;

import br.coelho.agenda.Adapters.DataItem;
import br.coelho.agenda.Adapters.BaseListReminder;
import br.coelho.agenda.R;
import br.coelho.agenda.Root;

public class Reminders extends Fragment {

    private ListView ReminderList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.page_reminders, container, false);

        setFabAction(((Root)getActivity()));

        onClickCalendarDate();

        Root.FabAction.setImageDrawable(ContextCompat.getDrawable(this.getActivity(), R.drawable.ic_edit_black));

        Root.FabAction.show();

        ReminderList = view.findViewById(R.id.ReminderList);

        ReminderList.setDivider(null);

        DataItem item = new DataItem();


        item.setReminder(0, "Em linguística, a noção de texto é ampla e ainda aberta a uma definição mais precisa. Grosso modo, pode ser entendido como manifestação linguística das ideias de um autor, que serão interpretadas pelo leitor de acordo com seus conhecimentos linguísticos e culturais. Seu tamanho é variável.", "Texto", new Date(), new Date(), "#ffffff", "#ffffff" );

        ArrayList<DataItem> ListItem = new ArrayList<>();

        ListItem.add(item);

        ListItem.add(item);

        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);
        ListItem.add(item);




        item = new DataItem();

        item.setReminder(12, "Short description for testing animation", "Alpha", new Date(), new Date(), "#ffffff", "#ffffff" );


        ListItem.add(item);


        ReminderList.setAdapter(new BaseListReminder(ListItem, this.getActivity()));

        ReminderList.setNestedScrollingEnabled(true);


        return view;
    }


    private void setFabAction(final Root instance) {
        instance.FabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                instance.openNewPage(new CreateReminder());

                instance.MainToolbar.setTitle("Qual é a data do lembrete?");

                instance.toolbarMenuItemChanged();

                instance.FabAction.hide();



            }
        });
    }


    private void onClickCalendarDate() {

        Root.Calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date d) {
                Root.MainToolbar.setTitle(Root.FormatDateTitle.format(d));
            }

            @Override
            public void onMonthScroll(Date d) {
                Root.MainToolbar.setTitle(Root.FormatDateTitle.format(d));
            }
        });

    }
}
