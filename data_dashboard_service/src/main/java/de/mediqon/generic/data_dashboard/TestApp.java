package de.mediqon.generic.data_dashboard;

import de.mediqon.generic.data_dashboard.dataconnection.StatementExecuter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestApp {

    public static void main(String[] args) {

        ConnectionPropertiesEntity connectionProperties = new ConnectionPropertiesEntity();
        connectionProperties.setUrl("jdbc:postgresql://mq-db02.mediqon.local:5433/mq_pg_krankenhaus_norm");
        connectionProperties.setUsername("tableau_read");
        connectionProperties.setPassword("tableau_read");

        StatementExecuter statementExecuter =
                new StatementExecuter(connectionProperties,
                                      "select distinct kh_key, krankenhaus_name from qb_kh_stamm");

        try {
            List<Map<String, Object>> results = statementExecuter.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
