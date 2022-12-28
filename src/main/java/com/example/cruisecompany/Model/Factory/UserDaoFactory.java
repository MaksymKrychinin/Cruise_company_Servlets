package com.example.cruisecompany.Model.Factory;

import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.DaoImpl.UserDaoImpl;

public class UserDaoFactory {
    private static UserDao dao = null;

    private UserDaoFactory() {
    }

    public static synchronized UserDao getInstance() {
        if (dao == null) {
            dao = new UserDaoImpl();
        }
        return dao;
    }

}
