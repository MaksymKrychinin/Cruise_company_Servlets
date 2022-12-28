package com.example.cruisecompany.Controller.FiltersAndListeners;

import com.example.cruisecompany.Model.DataBaseConnectionPool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class DBListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(DBListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connection connection = ConnectionPool.getDataSource().getConnection();
        } catch (SQLException e) {
            LOG.info("Connection FAIL, cannot get connection from db");
            contextDestroyed(sce);
            throw new RuntimeException(e);
        }
    }
}
