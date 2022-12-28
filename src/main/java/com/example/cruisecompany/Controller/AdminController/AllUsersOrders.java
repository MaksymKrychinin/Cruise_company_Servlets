package com.example.cruisecompany.Controller.AdminController;

import com.example.cruisecompany.Controller.AllCruise;
import com.example.cruisecompany.Model.DAO.AdminDao;
import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.Entity.CruiseShip;
import com.example.cruisecompany.Model.Entity.UsersOrder;
import com.example.cruisecompany.Model.Factory.AdminDaoFactory;
import com.example.cruisecompany.Model.Factory.CruiseDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceAdmin;
import com.example.cruisecompany.Model.Service.ServiceCruise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllUsersOrders", value = "/AllUsersOrders")
public class AllUsersOrders extends HttpServlet {
    ServiceAdmin serviceAdmin;
    private static final Logger LOG = LoggerFactory.getLogger(AllUsersOrders.class);

    @Override
    public void init() {
        AdminDao adminDao = AdminDaoFactory.getInstance();
        serviceAdmin = new ServiceAdmin(adminDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<UsersOrder> usersOrdersList = serviceAdmin.getAllUsersOrders(request);
        request.setAttribute("usersOrdersList", usersOrdersList);
        request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
        LOG.info("Forward adminPanel.jsp");
    }
}
