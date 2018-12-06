package com.example.user_pc.project_trtpo.SupportClass;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    Date date;
    Date currentDate;
    Calendar calendar;
    public final int firstWeek=47;
    public DateHelper()
    {
        date = Calendar.getInstance().getTime();
        currentDate=Calendar.getInstance().getTime();
        calendar=Calendar.getInstance();
    }

    public int getDay()
    {
     return    date.getDay();
    }
    public int getDate()
    {
     return    date.getDate();
    }
    public int getMounth()
    {
        return    date.getMonth();
    }
    public  int getNumWeek()
    {
        calendar.setTime(date);
        int tempweek=calendar.get(Calendar.WEEK_OF_YEAR);
        return (tempweek-firstWeek)%4+1;

    }

    public Date getCurrentDate() {
        return currentDate;
    }
    public boolean isCuttentDate()
    {
        return date.getDate()==currentDate.getDate();
    }
    public void incrementDate()
    {
        date.setDate(getDate() + 1);
    }

    public void deincrementDate()
    {
        date.setDate(getDate() - 1);

    }
    public String getDayWeek()
    {
        calendar.setTime(date);
        calendar.getFirstDayOfWeek();
        switch (date.getDay()-calendar.getFirstDayOfWeek()+1)
        {
            case 0:return "Понедельник";
            case 1:return "Вторник";
            case 2:return "Среда";
            case 3:return "Четверг";
            case 4:return "Пятница";
            case 5:return "Суббота";
            case 6:return null;

            default:return null;
        }
    }



}
