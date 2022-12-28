package com.example.cruisecompany.Model.Entity;

import java.sql.Date;

public class CruiseShip {
    private int id;
    private int capacity;
    private String routeFrom;
    private String routeTo;
    private int numberOfVisitedPorts;
    private Date startDate;
    private Date endDate;
    private int orderedSeats;

    @Override
    public String toString() {
        return "CruiseShip{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", routeFrom='" + routeFrom + '\'' +
                ", routeTo='" + routeTo + '\'' +
                ", numberOfVisitedPorts=" + numberOfVisitedPorts +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", orderedSeats=" + orderedSeats +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRouteFrom() {
        return routeFrom;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public int getNumberOfVisitedPorts() {
        return numberOfVisitedPorts;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    public CruiseShip(int id, int capacity, String routeFrom, String routeTo, int numberOfVisitedPorts, Date startDate, Date endDate, int orderedSeats) {
        this.id = id;
        this.capacity = capacity;
        this.routeFrom = routeFrom;
        this.routeTo = routeTo;
        this.numberOfVisitedPorts = numberOfVisitedPorts;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderedSeats = orderedSeats;
    }


    public int getOrderedSeats() {
        return orderedSeats;
    }
}
