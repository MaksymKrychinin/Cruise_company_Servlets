package com.example.cruisecompany.Model.DaoImpl;

import com.example.cruisecompany.Model.DAO.UsersOrderDao;
import com.example.cruisecompany.Model.DataBaseConnectionPool.ConnectionPool;
import com.example.cruisecompany.Model.Entity.CruiseShip;
import com.example.cruisecompany.Model.Entity.UsersOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersOrderDaoImpl implements UsersOrderDao {

    private int noOfRecords;
@Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public List<UsersOrder> getUserOrdersByUserId(int id, int offset, int noOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM cruise_company.userorders uo\n" +
                "INNER JOIN cruiseships cs on uo.idCruiseShip=cs.idcruiseShip\n" +
                "where uo.idUser=?\n" +
                "limit ?, ?";
        List<UsersOrder> usersOrderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, noOfRecords);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idCruiseShip = resultSet.getInt("idCruiseShip");
                    int capacity = resultSet.getInt("capacity");
                    String routeFrom = resultSet.getString("routeFrom");
                    String routeTo = resultSet.getString("routeTo");
                    int numberOfVisitedPorts = resultSet.getInt("numberOfVisitedPorts");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    int orderedSeats = resultSet.getInt("orderedSeats");
                    CruiseShip cruiseShip = new CruiseShip(idCruiseShip, capacity, routeFrom, routeTo,
                            numberOfVisitedPorts, startDate, endDate, orderedSeats);
                    String frontPassport = resultSet.getString("frontPassport");
                    String backPassport = resultSet.getString("backPassport");
                    int idUserOrder = resultSet.getInt("idUserOrders");
                    String status = resultSet.getString("status");
                    UsersOrder usersOrder = new UsersOrder(frontPassport, backPassport, cruiseShip, idUserOrder, status);
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
    public boolean createOrder(int idUser, int idCruiseShip, String frontPassport, String backPassport) {
        String query = "INSERT INTO `cruise_company`.`userorders` (`idUser`, `idCruiseShip`," +
                " `frontPassport`, `backPassport`) VALUES (?,?,?,?)";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idCruiseShip);
            preparedStatement.setString(3, frontPassport);
            preparedStatement.setString(4, backPassport);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
