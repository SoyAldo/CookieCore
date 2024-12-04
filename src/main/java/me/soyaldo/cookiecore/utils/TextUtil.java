package me.soyaldo.cookiecore.utils;

public class TextUtil {

    public static String replace(String text, String[][] replacements) {
        for (String[] replacement : replacements) {
            String target = replacement[0];
            String replace = replacement[1];
            text = text.replace(target, replace);
        }
        return text;
    }

}