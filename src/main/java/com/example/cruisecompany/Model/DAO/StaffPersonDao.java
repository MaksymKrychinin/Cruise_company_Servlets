package com.example.cruisecompany.Model.DAO;

import com.example.cruisecompany.Model.Entity.StaffPerson;

import java.util.List;

public interface StaffPersonDao {
    List<StaffPerson> getStaffPersonByCruiseId(int id, int offset, int noOfRecords);
}
