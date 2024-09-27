package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Codec {

    private static final char[] MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final String BASE_URL = "https://tinyUrl/";
    private static final int SHORT_URL_LENGTH = 6;

    private final Map<String, String> shortToLongMap;
    private final Map<String, String> longToShortMap;
    private AtomicLong counter;

    public Codec() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
        counter = new AtomicLong(1L);
    }

    public String encode(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }

        long id = counter.getAndIncrement();

        String randomString = getRandomString(id);

        if (shortToLongMap.containsKey(BASE_URL + randomString)) {
            randomString = encode(longUrl);
        }

        String shortUrl = BASE_URL + randomString;
        longToShortMap.put(longUrl, shortUrl);
        shortToLongMap.put(shortUrl, longUrl);
        return shortUrl;
    }



    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLongMap.get(shortUrl);
    }

    private String getRandomString(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(MAP[(int) id %62]);
            id = id / 62;
        }

        while (sb.length() < SHORT_URL_LENGTH) {
            sb.insert(0, 'a');
        }

        return sb.toString();
    }
}
