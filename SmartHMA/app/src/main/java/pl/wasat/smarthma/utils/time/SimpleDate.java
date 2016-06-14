/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wasat.smarthma.utils.time;

/*
 * A simple class representing date and time.
 */

import java.util.Calendar;

/**
 * The type Simple date.
 */
public class SimpleDate {
    private final int year;
    private final int month;
    private final int day;
    private final int hours;
    private final int minutes;
    private final int seconds;

    /**
     * Instantiates a new Simple date.
     *
     * @param date the date
     */
    public SimpleDate(String date) {
        String[] values = date.split("-");
        this.year = Integer.parseInt(values[0]);
        this.month = Integer.parseInt(values[1]);
        if (values[2].contains("T")) {
            values = values[2].split("T");
            this.day = Integer.parseInt(values[0]);

            values = values[1].split(":");
            this.hours = Integer.parseInt(values[0]);
            this.minutes = Integer.parseInt(values[1]);
            this.seconds = Integer.parseInt(values[2].substring(0, 2));
        } else {
            this.day = Integer.parseInt(values[2]);
            this.hours = this.minutes = this.seconds = 0;
        }
    }

    /**
     * Gets calendar.
     *
     * @return the calendar
     */
    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hours, minutes, seconds);
        return calendar;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets hours.
     *
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Gets minutes.
     *
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Gets seconds.
     *
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Compare to int.
     *
     * @param another the another
     * @return the int
     */
    public int compareTo(SimpleDate another) {
        if (year != another.year) {
            return year - another.year;
        } else if (month != another.month) {
            return month - another.month;
        } else if (day != another.day) {
            return day - another.day;
        } else if (hours != another.hours) {
            return hours - another.hours;
        } else if (minutes != another.minutes) {
            return minutes - another.minutes;
        } else if (seconds != another.seconds) {
            return seconds - another.seconds;
        }
        return 0;
    }

    public String toString() {
        return year + "Y " + month + "M " + day + "D " + hours + ":" + minutes + ":" + seconds;
    }
}
