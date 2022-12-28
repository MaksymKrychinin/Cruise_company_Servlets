package com.example.cruisecompany.Controller.FiltersAndListeners;

import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Factory.UserDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/AllCruise", "/CruiseByDate", "/CruiseByDate",
        "/CruiseByDuration","/CruiseById"})
public class UserAuthCheck implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(UserAuthCheck.class);
    ServiceUser serviceUser;
    HttpSession session;
    User user;

    public void init(HttpServletRequest req) {
        UserDao userDao = UserDaoFactory.getInstance();
        serviceUser = new ServiceUser(userDao);
        session = req.getSession();
        user = (User) session.getAttribute("user");
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        init(request);
        if (user != null && serviceUser.isUserAuthorized(request)) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect("index.jsp");
            LOG.info("Not authorised user try to check data");
        }
    }
}

