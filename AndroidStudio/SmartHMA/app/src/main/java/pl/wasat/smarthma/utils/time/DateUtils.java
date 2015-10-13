package pl.wasat.smarthma.utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.wasat.smarthma.model.iso.EntryISO;

public class DateUtils {

    public static String getDateDifference(Date thenDate) {
        Calendar now = Calendar.getInstance();
        Calendar then = Calendar.getInstance();
        now.setTime(new Date());
        then.setTime(thenDate);

        // Get the represented date in milliseconds
        long nowMs = now.getTimeInMillis();
        long thenMs = then.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = nowMs - thenMs;

        // Calculate difference in seconds
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffMinutes < 60) {
            if (diffMinutes == 1)
                return diffMinutes + " minute ago";
            else
                return diffMinutes + " minutes ago";
        } else if (diffHours < 24) {
            if (diffHours == 1)
                return diffHours + " hour ago";
            else
                return diffHours + " hours ago";
        } else if (diffDays < 30) {
            if (diffDays == 1)
                return diffDays + " day ago";
            else
                return diffDays + " days ago";
        } else {
            return "a long time ago..";
        }
    }

    public static String getISOPubDate(EntryISO entry) {
        String date = "";
        try {
            if (entry.getDate() != null) {
                if (entry.getDate().getCIDate() != null) {
                    date = entry.getDate().getCIDate().getDateInCIDate().getDateGco().getText();
                } else {
                    date = entry.getDate().toString();
                }
            } else if (entry.getMDMetadata().getDateStamp() != null) {
                date = entry.getMDMetadata().getDateStamp().getDateGco().getText();
            } else if (date.isEmpty()) {
                date = "1970-01-01";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @param cal - Calendar
     * @return - String with date
     */
    public static String calendarToDateString(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        return df.format(cal.getTime());
    }

    /**
     * @param cal - Calendar
     * @return - String of time
     */
    public static String calendarToTimeString(Calendar cal) {
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm:ss", Locale.UK);
        return dfTime.format(cal.getTime());
    }

    public static String calendarToISO(Calendar cal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.UK);
        return df.format(cal.getTime());
    }

    public static String timestampToDateTimeStr(long timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault()).format(new Date(timestamp));
    }
}
