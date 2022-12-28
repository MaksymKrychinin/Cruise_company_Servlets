package com.example.cruisecompany.Controller.FiltersAndListeners;

import com.example.cruisecompany.Model.DAO.AdminDao;
import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Factory.AdminDaoFactory;
import com.example.cruisecompany.Model.Factory.UserDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceAdmin;
import com.example.cruisecompany.Model.Service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/AllUsersOrders", "/ChangeStatus"})
public class AdminFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AdminFilter.class);
    ServiceAdmin serviceAdmin;
    HttpSession session;
    User user;

    public void init(HttpServletRequest req) {
        AdminDao adminDao = AdminDaoFactory.getInstance();
        serviceAdmin = new ServiceAdmin(adminDao);
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
        if (user != null && !user.getRole().equals("admin") && serviceAdmin.isAdmin(request)) {
            ((HttpServletResponse) resp).sendRedirect("index.jsp");
            LOG.info("NOT ADMIN try to check adminPage");
        } else {
            LOG.info("Admin panel checked by: " + user.toString());
            filterChain.doFilter(req, resp);
        }
    }
}
