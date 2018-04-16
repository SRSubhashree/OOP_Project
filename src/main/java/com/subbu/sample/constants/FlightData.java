package com.subbu.sample.constants;

public class FlightData {
    public int getNumber() {
        return number;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    private int number;
    private String start;
    private String end;

    public FlightData(int number, String start, String end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }
}
