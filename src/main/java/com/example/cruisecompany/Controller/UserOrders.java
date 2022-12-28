package com.example.cruisecompany.Controller;

import com.example.cruisecompany.Model.DAO.UsersOrderDao;
import com.example.cruisecompany.Model.Entity.UsersOrder;
import com.example.cruisecompany.Model.Factory.UsersOrderDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceUsersOrder;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserOrders", value = "/UserOrders")
public class UserOrders extends HttpServlet {
    ServiceUsersOrder serviceUsersOrder;

    @Override
    public void init() throws ServletException {
        UsersOrderDao usersOrderDao = UsersOrderDaoFactory.getInstance();
        serviceUsersOrder = new ServiceUsersOrder(usersOrderDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UsersOrder> usersOrderList = serviceUsersOrder.getUserOrdersByUserId(request);
        request.setAttribute("usersOrderList", usersOrderList);
        request.getRequestDispatcher("userOrdersList.jsp").forward(request, response);
    }
}
