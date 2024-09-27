package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Codec codec = new Codec();
        String shortUrl = codec.encode("https://leetcode/aoweidj123fjesjl");
        System.out.println(shortUrl);
        System.out.println(codec.decode(shortUrl));

        shortUrl = codec.encode("https://leetcode/aoweidj123fjesjf");
        System.out.println(shortUrl);
        System.out.println(codec.decode(shortUrl));
    }
}
