package com.soyaldo.cookiecore.utils;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

    /**
     * Return a time in seconds from a time format.
     * Example 1: "30m" return 1800
     * Example 2: "1h30m" return 5400
     * Example 3: "30m 10s" return 1810
     *
     * @param time time format.
     * @return amount of seconds.
     */
    public static long parseFromFormat(String time) {
        String patron = "(?:(\\d+)y\\s*)?(?:(\\d+)mo\\s*)?(?:(\\d+)w\\s*)?(?:(\\d+)d\\s*)?(?:(\\d+)h\\s*)?(?:(\\d+)m\\s*)?(?:(\\d+)s)?";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(time);

        if (matcher.matches()) {
            long years = Long.parseLong(matcher.group(1) != null ? matcher.group(1) : "0");
            long months = Long.parseLong(matcher.group(2) != null ? matcher.group(2) : "0");
            long weeks = Long.parseLong(matcher.group(3) != null ? matcher.group(3) : "0");
            long days = Long.parseLong(matcher.group(4) != null ? matcher.group(4) : "0");
            long hours = Long.parseLong(matcher.group(5) != null ? matcher.group(5) : "0");
            long minutes = Long.parseLong(matcher.group(6) != null ? matcher.group(6) : "0");
            long seconds = Long.parseLong(matcher.group(7) != null ? matcher.group(7) : "0");
            return (((years * 365 + months * 30 + weeks * 7 + days) * 24 + hours) * 60 + minutes) * 60 + seconds;
        } else {
            throw new IllegalArgumentException("The time format is invalid or not found any match.");
        }
    }

    public static String parseFromSeconds(long segundos) {
        Duration duration = Duration.ofSeconds(segundos);

        long years = duration.toDays() / 365;
        long months = (duration.toDays() % 365) / 30;
        long weeks = ((duration.toDays() % 365) % 30) / 7;
        long days = ((duration.toDays() % 365) % 30) % 7;
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        StringBuilder result = new StringBuilder();

        if (years > 0) result.append(years).append("y ");
        if (months > 0) result.append(months).append("mo ");
        if (weeks > 0) result.append(weeks).append("w ");
        if (days > 0) result.append(days).append("d ");
        if (hours > 0) result.append(hours).append("h ");
        if (minutes > 0) result.append(minutes).append("m ");
        result.append(seconds).append("s");

        return result.toString().trim();
    }

}