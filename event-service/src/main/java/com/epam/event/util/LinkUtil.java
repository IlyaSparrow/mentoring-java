package com.epam.event.util;

public final class LinkUtil {

    public static final String COLLECTION = "collection";
    public static final String NEXT = "next";
    public static final String PREV = "prev";
    public static final String FIRST = "first";
    public static final String LAST = "last";

    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
}
