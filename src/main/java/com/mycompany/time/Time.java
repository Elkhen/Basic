package com.mycompany.time;

import java.util.*;
import java.util.regex.*;
import com.google.gson.*;
import kong.unirest.*;
import org.joda.time.DateTime;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.IOException;

public class Time {

    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_time_zone_abbreviations").get();
        Element table = doc.getElementsByTag("tbody").eq(1).first();
        System.out.print(table);

        
        
        /*while (true) {
            System.out.println("Enter time zone+hours(empty to exit): ");
            String zonePlus = read.nextLine();

            if (zonePlus.isEmpty()) {
                break;
            }
            if (zonePlus.matches(".*\\d.*")) {
                Pattern p1 = Pattern.compile("\\+\\s*(\\d*)\\D?");
                Matcher m1 = p1.matcher(zonePlus);
                Pattern p2 = Pattern.compile("(\\S+)\\s*\\+");
                Matcher m2 = p2.matcher(zonePlus);
                int plus = 0;
                String zone = "";
                if (m1.find()) {
                    plus = Integer.valueOf(m1.group(1));
                }
                if (m2.find()) {
                    zone = m2.group(1).toUpperCase();
                    System.out.print(zone);
                }
                DateTime time = DateTime.parse(JsonParser.parseString(
                        Unirest.get("http://worldtimeapi.org/api/timezone/" + zone).asJson().getBody().toString())
                        .getAsJsonObject().get("datetime").getAsString());
                System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime().plusHours(plus));
                System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().plusHours(plus));
            } else {
                DateTime time = DateTime.parse(JsonParser.parseString(
                        Unirest.get("http://worldtimeapi.org/api/timezone/" + zonePlus).asJson().getBody().toString())
                        .getAsJsonObject().get("datetime").getAsString());
                System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime());
                System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime());

            }
        }*/

    }

}
