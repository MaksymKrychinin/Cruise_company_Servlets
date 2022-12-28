package com.example.cruisecompany.Model.Service;

import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.Entity.CruiseShip;
import com.example.cruisecompany.Model.Enum.DBPagination;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

public class ServiceCruise {
    CruiseDao cruiseDao;

    public ServiceCruise(CruiseDao cruiseDao) {
        this.cruiseDao = cruiseDao;
    }

    public void setCruiseDao(CruiseDao cruiseDao) {
        this.cruiseDao = cruiseDao;
    }

    public CruiseDao getCruiseDao() {
        return cruiseDao;
    }

    public List<CruiseShip> getAllCruiseShips(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<CruiseShip> cruiseShipsList = cruiseDao.getAllCruise(
                (page - 1) * DBPagination.NUMBER_OF_RECORDS,
                DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("page", page);
        int noOfPages = (int) Math.ceil(cruiseDao.getNoOfRecords() * 1.0 / DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("noOfPages", noOfPages);
        return cruiseShipsList;
    }

    public List<CruiseShip> getCruiseShipsByDate(HttpServletRequest request) {
        int page = 1;
        Date date = Date.valueOf(request.getParameter("date"));
        if (date == null) {
            request.getRequestDispatcher("/AllCruise");
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<CruiseShip> cruiseShipsList = cruiseDao.getCruiseByDate(date,
                (page - 1) * DBPagination.NUMBER_OF_RECORDS,
                DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("page", page);
        int noOfPages = (int) Math.ceil(cruiseDao.getNoOfRecords() * 1.0 / DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("noOfPages", noOfPages);
        return cruiseShipsList;
    }

    public List<CruiseShip> getCruiseShipsByDuration(HttpServletRequest request) {
        int page = 1;
        int duration = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("duration") != null) {
            duration = Integer.parseInt(request.getParameter("duration"));
        }
        List<CruiseShip> cruiseShipsList = cruiseDao.getCruiseByDuration(duration,
                (page - 1) * DBPagination.NUMBER_OF_RECORDS,
                DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("page", page);
        int noOfPages = (int) Math.ceil(cruiseDao.getNoOfRecords() * 1.0 / DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("noOfPages", noOfPages);
        return cruiseShipsList;
    }

    public CruiseShip getCruiseShipById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        CruiseShip cruiseShip = cruiseDao.getCruiseShipById(id);
        return cruiseShip;
    }

    public boolean addCruise(HttpServletRequest request) {
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String routeFrom = request.getParameter("routeFrom");
        String routeTo = request.getParameter("routeTo");
        int numberOfVisitedPorts = Integer.parseInt(request.getParameter("numberOfVisitedPorts"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));

        return cruiseDao.addCruise(capacity, routeFrom, routeTo, numberOfVisitedPorts, startDate, endDate);
    }
}
