package com.example.cruisecompany.Model.DAO;

import com.example.cruisecompany.Model.Entity.UsersOrder;

import java.util.List;

public interface UsersOrderDao {
    int getNoOfRecords();

    List<UsersOrder> getUserOrdersByUserId(int id, int offset, int noOfRecords);

    boolean createOrder(int idUser, int idCruiseShip, String frontPassport, String backPassport);
}
