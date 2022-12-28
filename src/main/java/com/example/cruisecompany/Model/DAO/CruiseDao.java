package com.example.cruisecompany.Model.DAO;

import com.example.cruisecompany.Model.Entity.CruiseShip;

import java.sql.Date;
import java.util.List;

public interface CruiseDao {

    int getNoOfRecords();

    boolean addCruise(int capacity, String routeFrom, String routeTo, int numberOfVisitedPorts,
                      Date startDate, Date endDate);

    List<CruiseShip> getAllCruise(int offset, int noOfRecords);

    List<CruiseShip> getCruiseByDate(Date date, int offset, int noOfRecords);

    List<CruiseShip> getCruiseByRoute(String routeFrom, String routeTo, int offset, int noOfRecords);

    List<CruiseShip> getCruiseByDuration(int duration, int offset, int numberOfRecords);

    CruiseShip getCruiseShipById(int id);
}
