package com.example.cruisecompany.Controller;

import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Exceptions.UserAccountException;
import com.example.cruisecompany.Model.Factory.UserDaoFactory;
import com.example.cruisecompany.Model.Service.ErrorService;
import com.example.cruisecompany.Model.Service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    ServiceUser serviceUser;
    private static final Logger LOG = LoggerFactory.getLogger(AuthServlet.class);
    ErrorService errorService;

    @Override
    public void init() {
        UserDao userDao = UserDaoFactory.getInstance();
        serviceUser = new ServiceUser(userDao);
        errorService = new ErrorService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (serviceUser.isUserAuthorized(request)) {
            LOG.info("User already authorized");
            errorService.sendError(request, response, "User already authorized");
        } else {
            User user = null;
            try {
                user = serviceUser.registrationUser(request);
            } catch (UserAccountException e) {
                LOG.info("User already exist");
                errorService.sendError(request, response, "User already exist");
            }
            if (user != null) {
                request.setAttribute("user", user);
                LOG.info("Authorization & Registred user: " + user.toString());
            }
        }
        response.sendRedirect("index.jsp");
        LOG.info("Redirected to index.jsp");
    }
}
