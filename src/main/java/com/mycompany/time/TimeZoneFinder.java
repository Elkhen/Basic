package com.mycompany.time;

import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TimeZoneFinder {

    private ArrayList<TimeZoneElement> listofTimeZones;

    public TimeZoneFinder() throws IOException {
        this.listofTimeZones = new ArrayList<>();
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_time_zone_abbreviations").get();
        Elements table = doc.getElementsByTag("td");

        for (Element e : table) {
            if (e.text().length() >= 3 && e.text().length() <= 5) {
                TimeZoneElement t = new TimeZoneElement(e.text(), e.nextElementSibling().nextElementSibling().text());
                if (t.getUtcOffset().length() > 9) {
                    t.setUtcOffset(t.getUtcOffset().substring(0, 9));
                }
                if (!sameName(this.listofTimeZones, t)) {
                    this.listofTimeZones.add(t);
                }
            }
        }
    }
    
    public static boolean sameName(ArrayList<TimeZoneElement> list, TimeZoneElement elt) {
        for (TimeZoneElement t : list) {
            if (t.getTimezone().equals(elt.getTimezone())) {
                return true;
            }
        }
        return false;
    }
    
    public TimeZoneElement find(String timezone) {
        for (TimeZoneElement t: this.listofTimeZones) {
            if (t.getTimezone().equals(timezone.toUpperCase())){
                return t;
            }
        }
        System.out.println("Couldn't find it. Returning UTC");
        return new TimeZoneElement("UTC", "0");
    }

}
