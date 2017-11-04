package io.nothing.sample.pojo;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightStatus {


    String flightNo;
    String date;
    String source;
    String destination;
    String departureTime;
    String arrivalTime;
    String flightTime;
    String expectedIn;


    public FlightStatus(
            String flightNo,
            String date,
            String source,
            String departureTime,
            String destination,
            String arrivalTime,
            String flightTime,
            String expectedIn) {


        this.flightNo = flightNo;
        this.date = date;
        this.source = source;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.flightTime = flightTime;
        this.expectedIn = expectedIn;

    }


    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDate() {
        return date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getExpectedIn() {
        return expectedIn;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public String getSource() {
        return source;
    }

}
