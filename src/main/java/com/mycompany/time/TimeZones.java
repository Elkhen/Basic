
package com.mycompany.time;

public class TimeZones {
    private String name;
    private String offset;
    
    public TimeZones(String name, String offset) {
        this.name = name;
        this.offset = offset;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getOffset() {
        return this.offset;
    }
    
    public String toString() {
        return "Time zone's name is " + this.name + " and it's offset is " + this.offset;
    }
    
    public void setOffset(String offset) {
        this.offset = offset;
    }
}
