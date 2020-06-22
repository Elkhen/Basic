package com.mycompany.time;

import java.util.regex.*;

public class TimeZoneSeparator {

    private String timezoneString;
    private TimeZoneElement timezoneElement;
    private String timezone;
    private String offset;
    private String sign;

    public TimeZoneSeparator(String timezoneString) {
        this.timezoneString = timezoneString;
        Pattern p1 = Pattern.compile("(\\+|\\u2212|\\u00B1|-)\\s*(\\d*)\\D?");
        Matcher m1 = p1.matcher(this.timezoneString);
        Pattern p2 = Pattern.compile("(\\S+)\\s*(\\+|\\u2212|\\u00B1|-)");
        Matcher m2 = p2.matcher(this.timezoneString);
        if (m2.find()) {
            this.timezone = m2.group(1);
        }
        if (m1.find()) {
            this.offset = m1.group(2);
            this.sign = m1.group(1);
            this.timezoneElement = new TimeZoneElement(this.timezone, Integer.parseInt(this.offset), this.sign);
        } else {
            this.timezoneElement = new TimeZoneElement(this.timezone);
        }
        
    }

    public TimeZoneElement getTimeZoneElement() {
        return this.timezoneElement;
    }
}
