package pl.wasat.smarthma.utils.time;

/*
 * A simple class representing date and time.
 */

<<<<<<< HEAD:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/utils/time/SimpleDate.java
public class SimpleDate {
=======
public class SimpleDate
{
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/helper/SimpleDate.java
    private final int year;
    private final int month;
    private final int day;
    private final int hours;
    private final int minutes;
    private final int seconds;

<<<<<<< HEAD:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/utils/time/SimpleDate.java
    public SimpleDate(String date) {
        String[] values = date.split("-");
        this.year = Integer.parseInt(values[0]);
        this.month = Integer.parseInt(values[1]);
        if (values[2].contains("T")) {
=======
    public SimpleDate(String date)
    {
        //String str = date;
        String[] values = date.split("-");
        this.year = Integer.parseInt(values[0]);
        this.month = Integer.parseInt(values[1]);
        if (values[2].contains("T"))
        {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/helper/SimpleDate.java
            values = values[2].split("T");
            this.day = Integer.parseInt(values[0]);

            values = values[1].split(":");
            this.hours = Integer.parseInt(values[0]);
            this.minutes = Integer.parseInt(values[1]);
            this.seconds = Integer.parseInt(values[2].substring(0, 2));
<<<<<<< HEAD:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/utils/time/SimpleDate.java
        } else {
=======
        }
        else
        {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/helper/SimpleDate.java
            this.day = Integer.parseInt(values[2]);
            this.hours = this.minutes = this.seconds = 0;
        }
    }

    public String toString()
    {
        return year + "Y " + month + "M " + day + "D " + hours + ":" + minutes + ":" + seconds;
    }

    public int compareTo(SimpleDate another)
    {
        if (year != another.year)
        {
            return year - another.year;
        }
        else if (month != another.month)
        {
            return month - another.month;
        }
        else if (day != another.day)
        {
            return day - another.day;
        }
        else if (hours != another.hours)
        {
            return hours - another.hours;
        }
        else if (minutes != another.minutes)
        {
            return minutes - another.minutes;
        }
        else if (seconds != another.seconds)
        {
            return seconds - another.seconds;
        }
        return 0;
    }

<<<<<<< HEAD:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/utils/time/SimpleDate.java
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
=======
    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getHours()
    {
        return hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public int getSeconds()
    {
>>>>>>> 3cdf4b5c6a0ee0167bee856d291a553acdc6d2f4:AndroidStudio/SmartHMA/app/src/main/java/pl/wasat/smarthma/helper/SimpleDate.java
        return seconds;
    }
}
