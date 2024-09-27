package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        Codec codec = new Codec();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executorService.submit(() -> {
                String longUrl = "https://example.com/page" + finalI;
                String shortUrl = codec.encode(longUrl);
                System.out.println("Thread " + finalI + " encoded URL: " + shortUrl);
            });
        }

        // Shutdown the executor
        executorService.shutdown();

    }
}
