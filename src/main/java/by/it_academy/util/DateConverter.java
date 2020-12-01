package by.it_academy.util;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {

    public static Date getDateIncreasedBySeveralDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
