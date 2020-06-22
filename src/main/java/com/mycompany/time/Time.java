package com.mycompany.time;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import com.google.gson.*;
import kong.unirest.*;
import org.joda.time.DateTime;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.io.IOException;

public class Time {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        while (true) {
            System.out.println("Enter time zone+hours(empty to exit): ");
            String zonePlus = read.nextLine();

            if (zonePlus.isEmpty()) {
                break;
            }
            if (zonePlus.matches(".*\\d.*")) {
                Pattern p1 = Pattern.compile("(\\+|-|\\u00B1)\\s*(\\d*)\\D?");
                Matcher m1 = p1.matcher(zonePlus);
                Pattern p2 = Pattern.compile("(\\S+)\\s*(\\+|-|\\u00B1)");
                Matcher m2 = p2.matcher(zonePlus);
                int amount = 0;
                String zone = "";
                int changer = 0;
                if (m1.find()) {
                    amount = Integer.valueOf(m1.group(2));
                    changer = 1;
                }
                if (m2.find()) {
                    zone = m2.group(1).toUpperCase();
                }
                if (changer == 1) {
                    DateTime time = DateTime.parse(JsonParser.parseString(
                            Unirest.get("http://worldtimeapi.org/api/timezone/" + zone).asJson().getBody().toString())
                            .getAsJsonObject().get("datetime").getAsString());
                    System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime().plusHours(amount));
                    System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().plusHours(amount));
                } else if (changer == 2) {
                    DateTime time = DateTime.parse(JsonParser.parseString(
                            Unirest.get("http://worldtimeapi.org/api/timezone/" + zone).asJson().getBody().toString())
                            .getAsJsonObject().get("datetime").getAsString());
                    System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime().minusHours(amount));
                    System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().minusHours(amount));
                }

            } else {
                DateTime time = DateTime.parse(JsonParser.parseString(
                        Unirest.get("http://worldtimeapi.org/api/timezone/" + zonePlus).asJson().getBody().toString())
                        .getAsJsonObject().get("datetime").getAsString());
                System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime());
                System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime());

            }
        }
    }

}
