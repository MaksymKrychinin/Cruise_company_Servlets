package com.example.cruisecompany.Model.DAO;

import com.example.cruisecompany.Model.Entity.UsersOrder;

import java.util.List;

public interface AdminDao {
    int getNoOfRecords();

    boolean isAdmin(String login, String password);

    List<UsersOrder> getAllUsersOrders(int offset, int noOfRecords);

    boolean changeStatusToPaid(int idUserOrder);
}
