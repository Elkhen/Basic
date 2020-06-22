package com.mycompany.time;

import java.util.*;
import com.google.gson.*;
import kong.unirest.*;
import org.joda.time.DateTime;
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

            TimeZoneFinder find = new TimeZoneFinder();

            if (zonePlus.matches(".*(\\+|-).*")) {
                TimeZoneSeparator sep = new TimeZoneSeparator(zonePlus);
                TimeZoneSeparator sep2 = new TimeZoneSeparator(find.find(sep.getTimeZoneElement().getTimezone()).getUtcOffset());
                DateTime time = DateTime.parse(JsonParser.parseString(
                        Unirest.get("http://worldtimeapi.org/api/timezone/" + "etc/UTC").asJson().getBody().toString())
                        .getAsJsonObject().get("datetime").getAsString());
                System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().plusHours(sep.getTimeZoneElement().getOffset()+sep2.getTimeZoneElement().getOffset()));
            } else {
                TimeZoneSeparator sep = new TimeZoneSeparator(find.find(zonePlus).getUtcOffset());
                DateTime time = DateTime.parse(JsonParser.parseString(
                        Unirest.get("http://worldtimeapi.org/api/timezone/" + "etc/UTC").asJson().getBody().toString())
                        .getAsJsonObject().get("datetime").getAsString());
                System.out.println("Your current time according to supposedly atomic clock based api is: " + time.toLocalTime().plusHours(sep.getTimeZoneElement().getOffset()));

            }
        }
    }

}
