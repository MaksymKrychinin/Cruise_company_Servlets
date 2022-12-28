package com.example.cruisecompany.Model.Factory;

import com.example.cruisecompany.Model.DAO.UsersOrderDao;
import com.example.cruisecompany.Model.DaoImpl.UsersOrderDaoImpl;

public class UsersOrderDaoFactory {
    private static UsersOrderDao dao = null;

    private UsersOrderDaoFactory() {
    }

    public static synchronized UsersOrderDao getInstance() {
        if (dao == null) {
            dao = new UsersOrderDaoImpl();
        }
        return dao;
    }

}
