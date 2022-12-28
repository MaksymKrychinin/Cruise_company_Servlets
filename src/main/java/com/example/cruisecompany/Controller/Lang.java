package com.example.cruisecompany.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", value = "/Lang")
public class Lang extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Lang.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("lang", req.getParameter("lang"));
        resp.sendRedirect("index.jsp");
        LOG.info("Lang changed to: " + req.getParameter("lang"));
    }
}
