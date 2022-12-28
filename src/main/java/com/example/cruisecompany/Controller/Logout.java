package com.example.cruisecompany.Controller;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("user", null);
        response.sendRedirect("index.jsp");
    }
}
