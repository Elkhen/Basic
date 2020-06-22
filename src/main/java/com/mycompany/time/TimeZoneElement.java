package com.mycompany.time;

public class TimeZoneElement {

    private String timezone;
    private String utcOffset;
    private String sign;
    private int offsetValue;

    public TimeZoneElement(String timezone, String utcOffset) {
        this.timezone = timezone;
        this.utcOffset = utcOffset;
    }

    public TimeZoneElement(String timezone, int offsetValue, String sign) {
        this.timezone = timezone;
        this.offsetValue = offsetValue;
        if (sign.equals("\u00B1")) {
            this.sign = "";
        } else if (sign.equals("\u2212")){
            this.sign = "-";
        } else {
            this.sign = sign;
        }
    }

    public TimeZoneElement(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public String getUtcOffset() {
        return this.utcOffset;
    }

    public String getSign() {
        return this.sign;
    }

    public String toString() {
        return "Time zone's name is " + this.timezone + " and it's UTC offset is " + this.utcOffset;
    }

    public void setUtcOffset(String offset) {
        this.utcOffset = offset;
    }
    
    public int getOffset() {
        return Integer.parseInt(this.sign + this.offsetValue);
    }
}
