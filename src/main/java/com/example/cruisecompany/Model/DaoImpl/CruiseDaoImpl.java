package com.example.cruisecompany.Model.DaoImpl;

import com.example.cruisecompany.Model.DAO.CruiseDao;
import com.example.cruisecompany.Model.DataBaseConnectionPool.ConnectionPool;
import com.example.cruisecompany.Model.Entity.CruiseShip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseDaoImpl implements CruiseDao {
    int noOfRecords;

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean addCruise(int capacity, String routeFrom, String routeTo, int numberOfVisitedPorts,
                             Date startDate, Date endDate) {
        String query = "INSERT INTO `cruise_company`.`cruiseships` (`capacity`, `routeFrom`," +
                " `routeTo`, `numberOfVisitedPorts`, `startDate`, `endDate`) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, capacity);
            preparedStatement.setString(2, routeFrom);
            preparedStatement.setString(3, routeTo);
            preparedStatement.setInt(4, numberOfVisitedPorts);
            preparedStatement.setDate(5, startDate);
            preparedStatement.setDate(6, endDate);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<CruiseShip> getAllCruise(int offset, int numberOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM cruise_company.cruiseships where capacity>orderedSeats and startDate>NOW() limit ?, ?";
        List<CruiseShip> cruiseShipsList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberOfRecords);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idcruiseShip");
                    int capacity = resultSet.getInt("capacity");
                    String routeFrom = resultSet.getString("routeFrom");
                    String routeTo = resultSet.getString("routeTo");
                    int numberOfVisitedPorts = resultSet.getInt("numberOfVisitedPorts");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    int orderedSeats = resultSet.getInt("orderedSeats");
                    CruiseShip cruiseShip = new CruiseShip(id, capacity, routeFrom, routeTo,
                            numberOfVisitedPorts, startDate, endDate, orderedSeats);
                    cruiseShipsList.add(cruiseShip);
                }
                resultSet.close();
                resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
                if (resultSet.next()) {
                    this.noOfRecords = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cruiseShipsList;
    }

    @Override
    public List<CruiseShip> getCruiseByDate(Date date, int offset, int numberOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM cruise_company.cruiseships where capacity>orderedSeats and startDate>NOW() and startDate=? limit ?, ?";
        List<CruiseShip> cruiseShipsList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, numberOfRecords);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idcruiseShip");
                    int capacity = resultSet.getInt("capacity");
                    String routeFrom = resultSet.getString("routeFrom");
                    String routeTo = resultSet.getString("routeTo");
                    int numberOfVisitedPorts = resultSet.getInt("numberOfVisitedPorts");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    int orderedSeats = resultSet.getInt("orderedSeats");
                    CruiseShip cruiseShip = new CruiseShip(id, capacity, routeFrom, routeTo,
                            numberOfVisitedPorts, startDate, endDate, orderedSeats);
                    cruiseShipsList.add(cruiseShip);
                }
                resultSet.close();
                resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
                if (resultSet.next()) {
                    this.noOfRecords = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cruiseShipsList;
    }

    @Override
    public List<CruiseShip> getCruiseByRoute(String routeFrom, String routeTo, int offset, int noOfRecords) {
        return null;
    }

    @Override
    public List<CruiseShip> getCruiseByDuration(int duration, int offset, int numberOfRecords) {
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM cruise_company.cruiseships where capacity>orderedSeats and startDate>NOW() and (endDate-startDate)=? limit ?, ?";
        List<CruiseShip> cruiseShipsList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, duration);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, numberOfRecords);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("idcruiseShip");
                    int capacity = resultSet.getInt("capacity");
                    String routeFrom = resultSet.getString("routeFrom");
                    String routeTo = resultSet.getString("routeTo");
                    int numberOfVisitedPorts = resultSet.getInt("numberOfVisitedPorts");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    int orderedSeats = resultSet.getInt("orderedSeats");
                    CruiseShip cruiseShip = new CruiseShip(id, capacity, routeFrom, routeTo,
                            numberOfVisitedPorts, startDate, endDate, orderedSeats);
                    cruiseShipsList.add(cruiseShip);
                }
                resultSet.close();
                resultSet = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
                if (resultSet.next()) {
                    this.noOfRecords = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cruiseShipsList;
    }

    @Override
    public CruiseShip getCruiseShipById(int id) {
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM cruise_company.cruiseships where idcruiseShip=?";
        CruiseShip cruiseShip = null;
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int capacity = resultSet.getInt("capacity");
                    String routeFrom = resultSet.getString("routeFrom");
                    String routeTo = resultSet.getString("routeTo");
                    int numberOfVisitedPorts = resultSet.getInt("numberOfVisitedPorts");
                    Date startDate = resultSet.getDate("startDate");
                    Date endDate = resultSet.getDate("endDate");
                    int orderedSeats = resultSet.getInt("orderedSeats");
                    cruiseShip = new CruiseShip(id, capacity, routeFrom, routeTo,
                            numberOfVisitedPorts, startDate, endDate, orderedSeats);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cruiseShip;
    }


}
