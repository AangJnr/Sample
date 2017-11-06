package io.nothing.sample.pojo;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightSchedule {


    String departure;
    String departureTime;
    String departureDays;
    String departureNameCode;


    String arrival;
    String arrivalTime;
    String arrivalDays;
    String arrivalNameCode;


    public FlightSchedule(
            String departure,
            String departureTime,
            String departureDays,
            String departureNameCode,
            String arrival,
            String arrivalTime,
            String arrivalDays,
            String arrivalNameCode) {


        this.departure = departure;
        this.departureTime = departureTime;
        this.departureDays = departureDays;
        this.departureNameCode = departureNameCode;

        this.arrival = arrival;
        this.arrivalTime = arrivalTime;
        this.arrivalDays = arrivalDays;
        this.arrivalNameCode = arrivalNameCode;

    }


    public String getArrival() {
        return arrival;
    }

    public String getArrivalDays() {
        return arrivalDays;
    }

    public String getArrivalNameCode() {
        return arrivalNameCode;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDepartureDays() {
        return departureDays;
    }

    public String getDepartureNameCode() {
        return departureNameCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }


}
