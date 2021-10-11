package com.mytech.taskmanagement.util;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class DateUtil {
    public static DateTime zoneDateToDateTime(String date) {
        Calendar calendar = javax.xml.bind.DatatypeConverter.parseDateTime(date);
        DateTimeZone dateTimeZone = DateTimeZone.forTimeZone(calendar.getTimeZone());
        return new DateTime(date, dateTimeZone);
    }

   public static String getZonedTime(Timestamp timestamp, String zone) {
        if(timestamp != null) {
            if (zone != null) {
                LocalDateTime localDateTimeNoTimeZone = timestamp.toLocalDateTime();
                ZonedDateTime zonedDateTime = localDateTimeNoTimeZone.atZone(ZoneId.of(zone));
                return zonedDateTime.toString();
            } else {
                return timestamp.toString();
            }
        }
       return null;
    }

}

