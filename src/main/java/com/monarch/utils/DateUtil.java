/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author vishald
 */
public class DateUtil {

    public static final int DAY_SWITCH_TIME = 6;

    public static Date getWorkingDate() {
    	System.out.println("in working date function");
        Date workingDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workingDate);
        System.out.println(workingDate);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (hours < DAY_SWITCH_TIME) {
            calendar.add(Calendar.DATE, -1);
            workingDate = calendar.getTime();
        }
        System.out.println(workingDate);
        return workingDate;
    }

    public static Date addDateTime(Date date, String timeToadd) {
        try {
            timeToadd = timeToadd.trim() + ":00";
            long longTime = Time.valueOf(timeToadd).getTime();
            long totalTime = date.getTime() + longTime + TimeZone.getDefault().getRawOffset();

            Date finalDate = new Date(totalTime);
            return finalDate;
        } catch (Exception e) {
            System.out.println("com.monarch.utils.DateUtil.addDateTime()");
        }
        return null;
    }

    public static long calculateBreakTime(String breakTime) {
        try {
        	System.out.println(breakTime);
            String[] strs = breakTime.split(",");

            String outStr = "";
            String inStr = "";
            long breakDuration = 0;

            for (String str : strs) {
                if (str.trim().equals("")) {
                    continue;
                }

                String[] strs2 = str.split("-");
                if (strs2[1].trim().toLowerCase().equals("(in)")) {
                    inStr = strs2[0].trim() + ":00";
                } else if (strs2[1].trim().toLowerCase().equals("(out)")) {
                    outStr = strs2[0].trim() + ":00";
                }

                try {
                    if (!inStr.equals("") && !outStr.equals("")) {
                        long outTime = Time.valueOf(outStr).getTime();
                        long inTime = Time.valueOf(inStr).getTime();

                        if (outTime > inTime) {
                            outTime += 24 * 60 * 60 * 1000;   //taking it as next day
                        }

                        breakDuration += inTime - outTime;

                        inStr = "";
                        outStr = "";
                    }
                } catch (Exception e) {
                    System.out.println("Error calculation break Time 2: " + e.getMessage());
                }
            }

            return breakDuration;

        } catch (Exception e) {
            System.out.println("Error calculation break Time: " + e.getMessage());
        }
        return 0;
    }

    public static Date timeToDate(Time time) {
        return new Date(time.getTime());
    }

    public static Time dateToTime(Date date) {
        return new Time(date.getTime());
    }

    public static int getWorkingdays(String startDate, String endDate) {

        Calendar fromDate = stringToCalDate(startDate);
        Calendar toDate = stringToCalDate(endDate);

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        startCal.setTime(fromDate.getTime());
        endCal.setTime(toDate.getTime());
        int workDays = 0;

        //If working dates are same,then checking what is the day on that date.
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
                return workDays;
            }
        }

        /*If start date is coming after end date, Then shuffling Dates and storing dates 
         by incrementing upto end date in do-while part.*/
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(fromDate.getTime());
            endCal.setTime(toDate.getTime());
        }

        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

        return workDays;
    }

    //temp7
    public static Date stringToDate(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); // MM/dd/yyyy
            return df.parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Date stringToDateTime(String datetime) {

        try {
            //SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");  //hh:mm a 13:00:00 AM 24hours format             
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm a");  // HH 12 hours
            return df.parse(datetime);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public static long stringToTime(String timeStr) {
        long time = 0;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            String inputString = timeStr; //"01:30:00";

            Date date = sdf.parse(inputString);// .parse("1970-01-01" + inputString);
            time = date.getTime();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return time;
    }

    public static String timeToString(long time) {
        String timeStr = null;

        long totalSecs = time / 1000;
        long hours = (totalSecs / 3600);
        long mins = (totalSecs / 60) % 60;
        long secs = totalSecs % 60;

        if (mins > 0) {
            timeStr = hours + ":" + mins + ":00";
        } else {
            timeStr = hours + ":00:00";
        }

        return timeStr;
    }

    public static Calendar longToCalDateTime(long time) {
        Calendar cal = Calendar.getInstance();
        time -= TimeZone.getDefault().getRawOffset();
        Date date = new Date(time);
        cal.setTime(date);

        return cal;
    }

    public static Calendar stringToCalDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");   //MM/dd/yyyy

            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(date));

            return cal;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int getLeavedays(String startDate, String endDate) {

        Calendar fromDate = stringToCalDate(startDate);
        Calendar toDate = stringToCalDate(endDate);

        Calendar startCal = Calendar.getInstance();;
        Calendar endCal = Calendar.getInstance();;

        startCal.setTime(fromDate.getTime());
        endCal.setTime(toDate.getTime());

        int workDays = 0;

        //If working dates are same,then checking what is the day on that date.
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
                return workDays;
            }
        }

        /*If start date is coming after end date, Then shuffling Dates and storing dates 
            by incrementing upto end date in do-while part.*/
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(fromDate.getTime());
            endCal.setTime(toDate.getTime());
        }

        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

        return workDays;
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }

}
