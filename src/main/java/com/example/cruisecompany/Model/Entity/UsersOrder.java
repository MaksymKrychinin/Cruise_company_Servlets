package com.example.cruisecompany.Model.Entity;

public class UsersOrder {
    String frontPassport;
    String backPassport;
    User user;
    CruiseShip cruiseShip;
    int idUserOrder;
    String status;

    public UsersOrder(String frontPassport, String backPassport, CruiseShip cruiseShip, int idUserOrder, String status) {
        this.frontPassport = frontPassport;
        this.backPassport = backPassport;
        this.cruiseShip = cruiseShip;
        this.idUserOrder = idUserOrder;
        this.status = status;
    }

    public String getFrontPassport() {
        return frontPassport;
    }

    public String getBackPassport() {
        return backPassport;
    }

    public User getUser() {
        return user;
    }

    public CruiseShip getCruiseShip() {
        return cruiseShip;
    }

    public int getIdUserOrder() {
        return idUserOrder;
    }

    public String getStatus() {
        return status;
    }

    public UsersOrder(String frontPassport, String backPassport, User user, CruiseShip cruiseShip, int idUserOrder, String status) {
        this.frontPassport = frontPassport;
        this.backPassport = backPassport;
        this.user = user;
        this.cruiseShip = cruiseShip;
        this.idUserOrder = idUserOrder;
        this.status = status;
    }

    public String toStringUserOrder() {
        return "UsersOrder{" +
                "frontPassport='" + frontPassport + '\'' +
                ", backPassport='" + backPassport + '\'' +
                ", cruiseShip=" + cruiseShip.toString() +
                ", idUserOrder=" + idUserOrder +
                ", status='" + status + '\'' +
                '}';
    }
    @Override
    public String toString() {
        return "UsersOrder{" +
                "frontPassport='" + frontPassport + '\'' +
                ", backPassport='" + backPassport + '\'' +
                ", user=" + user.toString() +
                ", cruiseShip=" + cruiseShip.toString() +
                ", idUserOrder=" + idUserOrder +
                ", status='" + status + '\'' +
                '}';
    }
}
