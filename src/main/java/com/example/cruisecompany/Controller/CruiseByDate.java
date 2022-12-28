package com.example.cruisecompany.Controller;

import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.Entity.CruiseShip;
import com.example.cruisecompany.Model.Factory.CruiseDaoFactory;
import com.example.cruisecompany.Model.Service.ServiceCruise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CruiseByDate", value = "/CruiseByDate")
public class CruiseByDate extends HttpServlet {
    ServiceCruise serviceCruise;
    private static final Logger LOG = LoggerFactory.getLogger(AllCruise.class);

    @Override
    public void init() {
        CruiseDao cruiseDao = CruiseDaoFactory.getInstance();
        serviceCruise = new ServiceCruise(cruiseDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<CruiseShip> cruiseShipsList = serviceCruise.getCruiseShipsByDate(request);
        request.setAttribute("cruiseShipsList",cruiseShipsList);
        request.setAttribute("cruiseType","CruiseByDate");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        LOG.info("Forwarded to index.jsp");
    }
}
