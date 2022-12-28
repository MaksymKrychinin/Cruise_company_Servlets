package com.example.cruisecompany.Controller.AdminController;

import com.example.cruisecompany.Model.DAO.AdminDao;
import com.example.cruisecompany.Model.Factory.AdminDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeStatus", value = "/ChangeStatus")
public class ChangeStatus extends HttpServlet {
    ServiceAdmin serviceAdmin;
    private static final Logger LOG = LoggerFactory.getLogger(ChangeStatus.class);

    @Override
    public void init() {
        AdminDao adminDao = AdminDaoFactory.getInstance();
        serviceAdmin = new ServiceAdmin(adminDao);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        serviceAdmin.changeStatusToPaid(request);
        response.sendRedirect("/AllUsersOrders");
        LOG.info("Redirect to /AllUsersOrders");
    }
}
