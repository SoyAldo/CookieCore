package com.soyaldo.cookiecore.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimestampUtil {

    public static Timestamp giveCurrent() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }

    public static long secondsElapsed(Timestamp start) {
        return secondsElapsed(start, giveCurrent());
    }

    public static long secondsElapsed(String start) {
        return secondsElapsed(Timestamp.valueOf(start), giveCurrent());
    }

    public static long secondsElapsed(Timestamp start, Timestamp end) {
        return (end.getTime() - start.getTime()) / 1000;
    }

    public static long secondsElapsed(String start, String end) {
        return secondsElapsed(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    public static Timestamp parseTimestamp(String format) {
        try {
            return Timestamp.valueOf(format);
        } catch (IllegalArgumentException e) {
            // todo mandar mensaje de que no es un formato valido.
        }
        return new Timestamp(new Date().getTime());
    }

}