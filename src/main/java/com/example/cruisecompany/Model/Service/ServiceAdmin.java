package com.example.cruisecompany.Model.Service;

import com.example.cruisecompany.Model.DAO.AdminDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Entity.UsersOrder;
import com.example.cruisecompany.Model.Enum.DBPagination;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ServiceAdmin {
    AdminDao adminDao;

    public ServiceAdmin(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public boolean isAdmin(HttpServletRequest request) {
        String login = ((User)request.getSession().getAttribute("user")).getEmail();
        String passwordMD5 = ((User)request.getSession().getAttribute("user")).getPassword();
        return adminDao.isAdmin(login, passwordMD5);
    }

    public List<UsersOrder> getAllUsersOrders(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<UsersOrder> usersOrdersList = adminDao.getAllUsersOrders((page - 1) * DBPagination.NUMBER_OF_RECORDS,
                DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("page", page);
        int noOfPages = (int) Math.ceil(adminDao.getNoOfRecords()*1.0 / DBPagination.NUMBER_OF_RECORDS);
        request.setAttribute("noOfPages", noOfPages);
        return usersOrdersList;
    }

    public boolean changeStatusToPaid(HttpServletRequest request) {
        int userOrderId = Integer.parseInt(request.getParameter("userOrderId"));
        boolean status = adminDao.changeStatusToPaid(userOrderId);
        return status;
    }

    String md5(String password) {
        return DigestUtils.md5Hex(password);
    }
}
