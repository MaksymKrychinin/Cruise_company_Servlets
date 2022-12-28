package com.example.cruisecompany.Model.Factory;

import com.example.cruisecompany.Model.DAO.StaffPersonDao;
import com.example.cruisecompany.Model.DaoImpl.StaffPersonDaoImpl;

public class StaffPersonDaoFactory {
    private static StaffPersonDao dao = null;

    private StaffPersonDaoFactory() {
    }

    public static synchronized StaffPersonDao getInstance() {
        if (dao == null) {
            dao = new StaffPersonDaoImpl();
        }
        return dao;
    }

}
