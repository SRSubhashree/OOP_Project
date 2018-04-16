package com.subbu.sample.constants;

public class FlightData {
    private int number;
    private String start;
    private String end;
    private String firstFare;
    private String businessFare;
    private String economyFare;

    public int getNumber() {
        return number;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getFirstFare() {
        return firstFare;
    }

    public String getBusinessFare() {
        return businessFare;
    }

    public String getEconomyFare() {
        return economyFare;
    }

    public FlightData(int number, String start, String end, String firstFare, String businessFare, String economyFare) {
        this.number = number;
        this.start = start;
        this.end = end;
        this.firstFare = firstFare;
        this.businessFare = businessFare;
        this.economyFare = economyFare;
    }
}
