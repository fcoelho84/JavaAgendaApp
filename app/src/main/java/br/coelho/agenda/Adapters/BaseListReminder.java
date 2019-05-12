package br.coelho.agenda.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.coelho.agenda.Animations.ReminderExpand;
import br.coelho.agenda.R;

public class BaseListReminder extends BaseAdapter {

    private ArrayList<DataItem> ListItem;

    private Context ListContext;

    public BaseListReminder(ArrayList<DataItem> ListItem, Context ListContext) {

        this.ListItem = ListItem;

        this.ListContext = ListContext;
    }


    @Override
    public int getCount() {
        return ListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return ListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListItem.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ListContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.template_reminder_item, null);

        final TextView descrition, tag, reminderDate, notificationDate;

        final FrameLayout ReminderColor;

        final DataItem item = ListItem.get(position);


        descrition = v.findViewById(R.id.Description);

        tag = v.findViewById(R.id.Tag);

        reminderDate = v.findViewById(R.id.Date);

        notificationDate = v.findViewById(R.id.NotificationDate);

        ReminderColor = v.findViewById(R.id.ReminderColor);

        descrition.setText(item.getShortDescrition());

        tag.setText(item.getTag());

        reminderDate.setText(item.getDate());

        notificationDate.setText(item.getNotificationDate());

        new ReminderExpand().setExpandable(v, item, ListContext);


        return v;
    }


}
