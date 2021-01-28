package de.mediqon.generic.data_dashboard.dataconnection;

import de.mediqon.generic.data_dashboard.dataconnection.entities.*;
import de.mediqon.generic.data_dashboard.dataconnection.helper.NamedPreparedStatement;
import de.mediqon.generic.data_dashboard.models.dto.data.*;

import java.sql.*;
import java.util.*;


public class StatementExecuter {

    private StatementDto statementDto = null;
    private ConnectionPropertiesDto connectionProperties = null;


    private String sql = null;

    public StatementExecuter() {
    }

    public StatementExecuter(String sql) {
        this.sql = sql;
    }

    public StatementExecuter(ConnectionPropertiesDto connectionProperties,
                             String sql) {
        this.connectionProperties = connectionProperties;
        this.sql = sql;
    }

    public StatementExecuter(StatementDto statementDto) {
        this.statementDto = statementDto;
    }

    public String getSql() {
        if(statementDto != null){
            sql = "select ";
            if(statementDto.getDistinct()){
                sql += "distinct ";
            }
            for(StatementSelectFieldDto fld: statementDto.getSelectFields()){
                sql += fld.getLabeledFieldName() + ", ";
            }

            sql = removeLastComa(sql);
            if(sql.endsWith(", ")){
                sql = sql.substring(0, sql.length() - 2);
            }
            sql += " from " + statementDto.getTableName();
            if(statementDto.getGroupByFields().isEmpty() == false){
                sql += " group by  ";
                for(StatementGroupByFieldDto fld: statementDto.getGroupByFields()){
                    sql += fld.getFieldName() + ", ";
                }
                sql = removeLastComa(sql);
            }
            if(statementDto.getHavingFields().isEmpty() == false){
                sql += " having  ";
                for(StatementHavingFieldDto fld: statementDto.getHavingFields()){
                    sql += fld.getFieldName() + "=:" + fld.getFieldName() +  ", ";
                }
                sql = removeLastComa(sql);
            }
        }

        if(statementDto.getResultLimit() > 0){
            sql += "limit " + statementDto.getResultLimit();
        }

        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ConnectionPropertiesDto getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(ConnectionPropertiesDto connectionProperties) {
        this.connectionProperties = connectionProperties;
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
        ConnectionPropertiesDto currentConnectionProperties = connectionProperties;
        if(statementDto != null && statementDto.hasConnection()){
            currentConnectionProperties = statementDto.getConnectionProperties();
        }
        return currentConnectionProperties.generateConnection();
    }


    private String removeLastComa(String text){
        if(text.endsWith(", ")){
            text = text.substring(0, text.length() - 2);
        }
        return text;
    }
}
