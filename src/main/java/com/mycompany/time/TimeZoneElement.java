
package com.mycompany.time;

public class TimeZoneElement {
    private String timezone;
    private String offset;
    private String sign;
    
    public TimeZoneElement(String name, String offset) {
        this(name, offset, "");
    }
    
    public TimeZoneElement(String name, String offset, String sign) {
        this.timezone = name;
        this.offset = offset;
        this.sign = sign;
    }
    
    public String getTimezone() {
        return this.timezone;
    }
    
    public String getOffset() {
        return this.offset;
    }
    
    public String getSign() {
        return this.sign;
    }
    
    
    public String toString() {
        return "Time zone's name is " + this.timezone + " and it's offset is " + this.offset;
    }
    
    public void setOffset(String offset) {
        this.offset = offset;
    }
}
