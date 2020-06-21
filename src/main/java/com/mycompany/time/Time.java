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

    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);

        while (true) {
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
                Pattern p3 = Pattern.compile("-\\s*(\\d*)\\D?");
                Matcher m3 = p1.matcher(zonePlus);
                Pattern p4 = Pattern.compile("(\\S+)\\s*-");
                Matcher m4 = p2.matcher(zonePlus);
                int amount = 0;
                String zone = "";
                if (m1.find()) {
                    amount = Integer.valueOf(m1.group(1));
                }
                if (m2.find()) {
                    zone = m2.group(1).toUpperCase();
                }
                if (m3.find()) {
                    amount = Integer.valueOf(m1.group(1));
                }
                if (m4.find()) {
                    zone = m4.group(1).toUpperCase();
                }
                if (amount > 0) {
                    DateTime time = DateTime.parse(JsonParser.parseString(
                            Unirest.get("http://worldtimeapi.org/api/timezone/" + zone).asJson().getBody().toString())
                            .getAsJsonObject().get("datetime").getAsString());
                    System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime().plusHours(amount));
                    System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().plusHours(amount));
                } else {
                    DateTime time = DateTime.parse(JsonParser.parseString(
                            Unirest.get("http://worldtimeapi.org/api/timezone/" + zone).asJson().getBody().toString())
                            .getAsJsonObject().get("datetime").getAsString());
                    System.out.println("Your current time according to my computer is : " + new DateTime(System.currentTimeMillis()).toLocalTime().plusHours(amount));
                    System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().plusHours(amount));
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

    public static boolean sameName(ArrayList<TimeZones> list, TimeZones elt) {
        for (TimeZones t : list) {
            if (t.getName().equals(elt.getName())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<TimeZones> getTimeZones() {
        ArrayList<TimeZones> list = new ArrayList<>();
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_time_zone_abbreviations").get();
        Elements table = doc.getElementsByTag("td");

        for (Element e : table) {
            if (e.text().length() >= 3 && e.text().length() <= 5) {
                TimeZones t = new TimeZones(e.text(), e.nextElementSibling().nextElementSibling().text());
                if (t.getOffset().length() > 9) {
                    t.setOffset(t.getOffset().substring(0, 9));
                }
                if (!sameName(list, t)) {
                    list.add(t);
                }
            }
        }
        return list;
    }

}
