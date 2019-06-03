package br.coelho.agenda.Adapters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataItem {

    private int ID;
    private Date ReminderDate, NotificationDate;
    private String Description, Tag ,TagColor, ReminderColor;


    private SimpleDateFormat DateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public void setReminder(int ID, String Description, String Tag, Date ReminderDate,
                            Date NotificationDate, String TagColor, String ReminderColor ) {

        this.ID = ID;
        this.Description = Description;
        this.Tag = Tag;
        this.ReminderDate = ReminderDate;
        this.NotificationDate = NotificationDate;
        this.TagColor = TagColor;
        this.ReminderColor = ReminderColor;


    }


    public int getID() {
        return ID;
    }


    public String getTag() {
        return Tag;
    }

    public String getShortDescrition() {

        if(Description.length() > 52) {

            return Description.substring(0, 52);
        }

        return Description;
    }



    public String getNotificationDate() {
        return DateFormatter.format(NotificationDate);
    }



    public String getDate() {
        return DateFormatter.format(ReminderDate);
    }



    public String getDescrition() {
        return Description;
    }



    public String getColor() {
        return ReminderColor;
    }



    public String getTagColor() {
        return TagColor;
    }
}
