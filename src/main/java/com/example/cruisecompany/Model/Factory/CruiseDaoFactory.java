package com.example.cruisecompany.Model.Factory;

import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.DaoImpl.CruiseDaoImpl;

public class CruiseDaoFactory {
    private static CruiseDao dao = null;

    private CruiseDaoFactory() {
    }

    public static synchronized CruiseDao getInstance() {
        if (dao == null) {
            dao = new CruiseDaoImpl();
        }
        return dao;
    }

}
