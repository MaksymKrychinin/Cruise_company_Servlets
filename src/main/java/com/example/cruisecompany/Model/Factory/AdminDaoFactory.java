package com.example.cruisecompany.Model.Factory;

import com.example.cruisecompany.Model.DAO.AdminDao;
import com.example.cruisecompany.Model.DaoImpl.AdminDaoImpl;

public class AdminDaoFactory {
    private static AdminDao dao = null;

    private AdminDaoFactory() {
    }

    public static synchronized AdminDao getInstance() {
        if (dao == null) {
            dao = new AdminDaoImpl();
        }
        return dao;
    }

}
