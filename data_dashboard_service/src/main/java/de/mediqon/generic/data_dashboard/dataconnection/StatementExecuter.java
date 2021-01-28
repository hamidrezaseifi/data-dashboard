package de.mediqon.generic.data_dashboard.dataconnection;

import de.mediqon.generic.data_dashboard.dataconnection.entities.*;
import de.mediqon.generic.data_dashboard.dataconnection.helper.NamedPreparedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementExecuter {

    private StatementEntity statementEntity = null;
    private ConnectionPropertiesEntity connectionPropertiesEntity = null;


    private String sql = null;

    public StatementExecuter() {
    }

    public StatementExecuter(String sql) {
        this.sql = sql;
    }

    public StatementExecuter(ConnectionPropertiesEntity connectionPropertiesEntity,
                             String sql) {
        this.connectionPropertiesEntity = connectionPropertiesEntity;
        this.sql = sql;
    }

    public StatementExecuter(StatementEntity statementEntity) {
        this.statementEntity = statementEntity;
    }

    public String getSql() {
        if(statementEntity != null){
            sql = "select ";
            if(statementEntity.getDistinct()){
                sql += "distinct ";
            }
            for(StatementSelectFieldEntity fld: statementEntity.getSelectFields()){
                sql += fld.getLabeledFieldName() + ", ";
            }

            sql = removeLastComa(sql);
            if(sql.endsWith(", ")){
                sql = sql.substring(0, sql.length() - 2);
            }
            sql += " from " + statementEntity.getTableName();
            if(statementEntity.getGroupByFields().isEmpty() == false){
                sql += " group by  ";
                for(StatementGroupByFieldEntity fld: statementEntity.getGroupByFields()){
                    sql += fld.getFieldName() + ", ";
                }
                sql = removeLastComa(sql);
            }
            if(statementEntity.getHavingFields().isEmpty() == false){
                sql += " having  ";
                for(StatementHavingFieldEntity fld: statementEntity.getHavingFields()){
                    sql += fld.getFieldName() + "=:" + fld.getFieldName() +  ", ";
                }
                sql = removeLastComa(sql);
            }
        }
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ConnectionPropertiesEntity getConnectionPropertiesEntity() {
        return connectionPropertiesEntity;
    }

    public void setConnectionPropertiesEntity(ConnectionPropertiesEntity connectionPropertiesEntity) {
        this.connectionPropertiesEntity = connectionPropertiesEntity;
    }
    public List<Map<String, Object>> execute() throws SQLException {
        return execute(new HashMap<>());
    }

    public List<Map<String, Object>> execute(Map<String, Object> parameters) throws SQLException {
        Connection connection = getConnection();

        NamedPreparedStatement prepStmt = NamedPreparedStatement.prepareStatement(connection, getSql());

        for(String param: parameters.keySet()){
            prepStmt.setObject(param, parameters.get(param));
        }

        ResultSet rs = prepStmt.executeQuery();
        List<String> columns = getResultSetColumnNames(rs);
        List<Map<String, Object>> results = new ArrayList<>();

        while (rs.next())
        {
            Map<String, Object> rowMap = new HashMap<>();
            for(String column: columns){
                rowMap.put(column, rs.getObject(column));
            }

            results.add(rowMap);
        }

        connection.close();
        return results;
    }

    private List<String> getResultSetColumnNames(ResultSet rs) throws SQLException {
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsMetaData = rs.getMetaData();
        for(int i=1; i<=rsMetaData.getColumnCount(); i++){
            columns.add(rsMetaData.getColumnName(i));
        }
        return columns;
    }

    private Connection getConnection() throws SQLException {
        if(statementEntity != null && statementEntity.hasConnection()){
            return statementEntity.getConnections().get(0).generateConnection();
        }
        return connectionPropertiesEntity.generateConnection();
    }

    private String removeLastComa(String text){
        if(text.endsWith(", ")){
            text = text.substring(0, text.length() - 2);
        }
        return text;
    }
}
