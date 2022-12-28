package com.example.cruisecompany.Model.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorService {
    public void sendError(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        request.setAttribute("error", message);
        response.sendRedirect("error.jsp");
    }
}
