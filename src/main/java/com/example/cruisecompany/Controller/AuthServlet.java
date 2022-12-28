package com.example.cruisecompany.Controller;

import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Factory.UserDaoFactory;
import com.example.cruisecompany.Model.Service.ErrorService;
import com.example.cruisecompany.Model.Service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
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
            User user = serviceUser.authorizationUser(request);
            if (user == null) {
                LOG.info("Not authentication user with login:" + request.getParameter("email"));
                errorService.sendError(request, response, "Not authentication user with login");
            } else {
                LOG.info("Authorization user: " + user.getEmail());
            }
            request.getSession().setAttribute("user", user);
        }
        response.sendRedirect("index.jsp");
        LOG.info("Redirected to index.jsp");
    }
}
