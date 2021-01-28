package de.mediqon.generic.data_dashboard;

import de.mediqon.generic.data_dashboard.dataconnection.StatementExecuter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestApp {

    public static void main(String[] args) throws SQLException {

        try {
            Properties props = new Properties();
            props.setProperty("user", "mq_app_user");
            props.setProperty("password", "mq_app_user");
            //props.setProperty("ssl","true");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost1:5432/mq_app", props);
            boolean b = conn.isClosed();
            conn.close();
        }
        catch (Exception ex){
            String s = ex.getLocalizedMessage();
            String s1 = ex.getMessage();
            s = s.trim();
        }


    }
}
