package com.example.cruisecompany.Model.DaoImpl;

import com.example.cruisecompany.Model.DAO.AdminDao;
import com.example.cruisecompany.Model.DataBaseConnectionPool.ConnectionPool;
import com.example.cruisecompany.Model.Entity.CruiseShip;
import com.example.cruisecompany.Model.Entity.User;
import com.example.cruisecompany.Model.Entity.UsersOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    private int noOfRecords;

    @Override
    public boolean isAdmin(String login, String password) {
        return false;
    }

    @Override
    public List<UsersOrder> getAllUsersOrders(int offset, int noOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS uo.idUserOrders, uo.idCruiseShip, uo.frontPassport, uo.backPassport, \n" +
                "uo.status, cs.capacity, cs.routeFrom, cs.routeTo, cs.numberOfVisitedPorts, \n" +
                "cs.startDate, cs.endDate, cs.orderedSeats, u.idusers, r.name as roleName, u.dateOfBirthday, \n" +
                "u.gender, u.phoneNumber, u.name, u.surname, u.email\n" +
                "FROM cruise_company.userorders uo\n" +
                "INNER JOIN cruiseships cs on uo.idCruiseShip=cs.idcruiseShip\n" +
                "INNER JOIN users u on uo.idUser=u.idusers\n" +
                "INNER JOIN role r on u.role = r.idrole\n" +
                "where uo.status!='PAID'\n" +
                "LIMIT ?,?;";
        List<UsersOrder> usersOrderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idUsers = resultSet.getInt("idusers");
                    String email = resultSet.getString("email");
                    String role = resultSet.getString("roleName");
                    Date dateOfBirthday = resultSet.getDate("dateOfBirthday");
                    String gender = resultSet.getString("gender");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    User user = new User(idUsers, email, role, dateOfBirthday,
                            gender, phoneNumber, name, surname);
                    int id = resultSet.getInt("idCruiseShip");
                    int capacity = resultSet.getInt("capacity");
                    String routeFrom = resultSet.getString("routeFrom");
                    String routeTo = resultSet.getString("routeTo");
                    int numberOfVisitedPorts = resultSet.getInt("numberOfVisitedPorts");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    int orderedSeats = resultSet.getInt("orderedSeats");
                    CruiseShip cruiseShip = new CruiseShip(id, capacity, routeFrom, routeTo,
                            numberOfVisitedPorts, startDate, endDate, orderedSeats);
                    String frontPassport = resultSet.getString("frontPassport");
                    String backPassport = resultSet.getString("backPassport");
                    int idUserOrder = resultSet.getInt("idUserOrders");
                    String status = resultSet.getString("status");
                    UsersOrder usersOrder = new UsersOrder(frontPassport, backPassport, user, cruiseShip, idUserOrder, status);
                    usersOrderList.add(usersOrder);
                }
                resultSet.close();
                resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
                if (resultSet.next()) {
                    this.noOfRecords = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersOrderList;
    }

    @Override
    public boolean changeStatusToPaid(int idUserOrder) {
        String query1 = "UPDATE userorders SET status ='PAID' where idUserOrders=?";
        String query2 = "UPDATE cruiseships cs SET orderedSeats = orderedSeats+1 where idcruiseShip = (SELECT uo.idCruiseShip FROM cruise_company.userorders uo where uo.idUserOrders = ? limit 1)";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
             PreparedStatement preparedStatement2 = connection.prepareStatement(query2)) {
            preparedStatement1.setInt(1, idUserOrder);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, idUserOrder);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
