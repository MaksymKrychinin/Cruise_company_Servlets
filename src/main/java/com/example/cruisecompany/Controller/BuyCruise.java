package com.example.cruisecompany.Controller;

import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.DAO.UsersOrderDao;
import com.example.cruisecompany.Model.Factory.CruiseDaoFactory;
import com.example.cruisecompany.Model.Factory.UserDaoFactory;
import com.example.cruisecompany.Model.Factory.UsersOrderDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceCruise;
import com.example.cruisecompany.Model.Service.ServiceUsersOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.UUID;

@WebServlet(name = "BuyCruise", value = "/BuyCruise")
@MultipartConfig(
        location = "D:\\EPAM\\CruiseCompany\\src\\main\\webapp\\passports",
        fileSizeThreshold = 1024 * 1024 * 3, // 1 MB
        maxFileSize = 1024 * 1024 * 3,      // 10 MB
        maxRequestSize = 1024 * 1024 * 6   // 100 MB
)
public class BuyCruise extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(BuyCruise.class);
    ServiceUsersOrder serviceUsersOrder;

    @Override
    public void init() {
        UsersOrderDao usersOrderDao = UsersOrderDaoFactory.getInstance();
        serviceUsersOrder = new ServiceUsersOrder(usersOrderDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceUsersOrder.createOrder(request);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        LOG.info("Forwarded yo index.jsp");
    }
}
