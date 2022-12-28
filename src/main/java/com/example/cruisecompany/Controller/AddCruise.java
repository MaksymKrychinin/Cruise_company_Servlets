package com.example.cruisecompany.Controller;

import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.Factory.CruiseDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceCruise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddCruise", value = "/AddCruise")
public class AddCruise extends HttpServlet {
    ServiceCruise serviceCruise;
    private static final Logger LOG = LoggerFactory.getLogger(AddCruise.class);

    @Override
    public void init() {
        CruiseDao cruiseDao = CruiseDaoFactory.getInstance();
        serviceCruise = new ServiceCruise(cruiseDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceCruise.addCruise(request);
        request.getRequestDispatcher("addCruiseForm.jsp").forward(request, response);
    }
}
