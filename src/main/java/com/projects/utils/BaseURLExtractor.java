package com.projects.utils;

public class BaseURLExtractor {

    public static String extractBaseURL(String url) {
        int index = url.indexOf('/', url.indexOf("//") + 2);
        if (index != -1) {
            return url.substring(0, index);
        }
        // If the pattern "//" is not found, return the original URL
        return url;
    }

}
