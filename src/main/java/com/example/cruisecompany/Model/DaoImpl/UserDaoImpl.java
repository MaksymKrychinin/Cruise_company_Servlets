package com.example.cruisecompany.Model.DaoImpl;

import com.example.cruisecompany.Model.DAO.UserDao;
import com.example.cruisecompany.Model.DataBaseConnectionPool.ConnectionPool;
import com.example.cruisecompany.Model.Entity.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    public User getUser(String email, String passwordMD5) {
        User user = null;
        String query = "SELECT u.idusers, u.dateOfBirthday, u.gender, u.phoneNumber, u.name, u.surname, r.name as roleName FROM cruise_company.users u\n" +
                "inner join role r ON r.idrole=u.role\n" +
                "where u.email=? and u.password=?";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordMD5);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idUsers = resultSet.getInt("idusers");
                    String role = resultSet.getString("roleName");
                    Date dateOfBirthday = resultSet.getDate("dateOfBirthday");
                    String gender = resultSet.getString("gender");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    user = new User(idUsers, email, passwordMD5, role, dateOfBirthday,
                            gender, phoneNumber, name, surname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void register(String email, String passwordMD5, Date dateOfBirthday, String gender, String phoneNumber, String name, String surname) {
        String query = "insert into users(email, password, dateOfBirthday, " +
                "gender, phoneNumber, name, surname) values(?,?,?,?,?,?,?);";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordMD5);
            preparedStatement.setDate(3, dateOfBirthday);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, name);
            preparedStatement.setString(7, surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean isUserExist(String email) {
        String query = "SELECT if((SELECT COUNT(*) FROM users where email = ? limit 1)=1, TRUE, FALSE)";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
