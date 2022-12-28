package com.example.cruisecompany.Model.DataBaseConnectionPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);

    private ConnectionPool() {
    }

    private static DataSource dataSource;

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ConnectionPool");
            } catch (NamingException e) {
                e.printStackTrace();
                LOG.error("Connection pool .getDataSource error," +
                        " Context lookup environment doesn't exist" +
                        " java:comp/env/jdbc/ConnectionPool", e);
            }
        }
        return dataSource;
    }

}
