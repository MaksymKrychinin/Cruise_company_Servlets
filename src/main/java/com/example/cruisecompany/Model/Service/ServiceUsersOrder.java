package com.example.cruisecompany.Model.Service;


import com.example.cruisecompany.Controller.BuyCruise;
import com.example.cruisecompany.Model.DAO.UsersOrderDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Entity.UsersOrder;
import com.example.cruisecompany.Model.Enum.DBPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ServiceUsersOrder {
    UsersOrderDao usersOrderDao;

    public List<UsersOrder> getUserOrdersByUserId(HttpServletRequest request) {
        int idUser = ((User) request.getSession().getAttribute("user")).getIdUsers();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<UsersOrder> usersOrderList = usersOrderDao.getUserOrdersByUserId(idUser,
                (page - 1) * DBPagination.NUMBER_OF_RECORDS, DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("page", page);
        int noOfPages = (int) Math.ceil(usersOrderDao.getNoOfRecords()*1.0 / DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("noOfPages", noOfPages);
        return usersOrderList;
    }

    public void createOrder(HttpServletRequest request) throws ServletException, IOException {
        int idUser = ((User) request.getSession().getAttribute("user")).getIdUsers();
        int idCruiseShip = Integer.parseInt(request.getParameter("id"));
        Part filePart1 = request.getPart("frontPassport");
        String fileName1 = UUID.randomUUID().toString() + filePart1.getSubmittedFileName();
        Part filePart2 = request.getPart("backPassport");
        String fileName2 = UUID.randomUUID().toString() + filePart2.getSubmittedFileName();

        for (Part part : request.getParts()) {
            if (part.getName().equals("frontPassport")) {
                part.write(fileName1);
            }
            if (part.getName().equals("backPassport")) {
                part.write(fileName2);
            }
        }
        usersOrderDao.createOrder(idUser, idCruiseShip, fileName1, fileName2);
    }

    public UsersOrderDao getUsersOrderDao() {
        return usersOrderDao;
    }

    public void setUsersOrderDao(UsersOrderDao usersOrderDao) {
        this.usersOrderDao = usersOrderDao;
    }

    public ServiceUsersOrder(UsersOrderDao usersOrderDao) {
        this.usersOrderDao = usersOrderDao;
    }
}
