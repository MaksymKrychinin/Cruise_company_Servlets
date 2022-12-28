package com.example.cruisecompany.Model.Service;

import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Exceptions.UserAccountException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class ServiceUser {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ServiceUser(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isUserAuthorized(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null;
    }


    public User authorizationUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordMD5 = md5(password);
        User user = userDao.getUser(email, passwordMD5);
        request.getSession().setAttribute("user", user);
        return user;
    }

    public User registrationUser(HttpServletRequest request) throws UserAccountException {
        String password = request.getParameter("password");
        String passwordMD5 = md5(password);
        String email = request.getParameter("email");
        Date dateOfBirthday = Date.valueOf(request.getParameter("date"));
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        if (userDao.isUserExist(email)) {
            throw new UserAccountException("Account with email" + email + " already exist");
        }
        userDao.register(email, passwordMD5, dateOfBirthday, gender, phoneNumber, name, surname);
        User user = authorizationUser(request);
        request.getSession().setAttribute("user", user);
        return user;
    }


    String md5(String password) {
        return DigestUtils.md5Hex(password);
    }
}