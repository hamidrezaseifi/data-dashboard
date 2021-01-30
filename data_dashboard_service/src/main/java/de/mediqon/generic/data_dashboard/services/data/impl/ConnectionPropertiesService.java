package de.mediqon.generic.data_dashboard.services.data.impl;

import de.mediqon.generic.data_dashboard.adapters.IConnectionPropertiesAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.exceptions.ConnectionNotFoundException;
import de.mediqon.generic.data_dashboard.models.dto.data.ColumnDetails;
import de.mediqon.generic.data_dashboard.models.dto.data.ConnectionPropertiesDto;
import de.mediqon.generic.data_dashboard.repositories.IConnectionPropertiesRepository;
import de.mediqon.generic.data_dashboard.services.data.IConnectionPropertiesService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.hateoas.JsonError;

import javax.inject.Singleton;
import java.sql.*;
import java.util.*;

@Singleton
public class ConnectionPropertiesService implements IConnectionPropertiesService {

    private final IConnectionPropertiesRepository connectionPropertiesRepository;
    private final IConnectionPropertiesAdapter connectionPropertiesAdapter;

    public ConnectionPropertiesService(IConnectionPropertiesRepository connectionPropertiesRepository,
                                       IConnectionPropertiesAdapter connectionPropertiesAdapter) {
        this.connectionPropertiesRepository = connectionPropertiesRepository;
        this.connectionPropertiesAdapter = connectionPropertiesAdapter;
    }

    @Override
    public List<ConnectionPropertiesEntity> getAll() {
        return connectionPropertiesRepository.getAll();
    }

    @Override
    public Optional<ConnectionPropertiesEntity> getById(UUID id) {
        return connectionPropertiesRepository.getById(id);
    }

    @Override
    public Optional<ConnectionPropertiesEntity> create(ConnectionPropertiesEntity entity) {
        connectionPropertiesRepository.create(entity);
        return connectionPropertiesRepository.getById(entity.getId());
    }

    @Override
    public Optional<ConnectionPropertiesEntity> update(ConnectionPropertiesEntity entity) {
        connectionPropertiesRepository.update(entity);
        return connectionPropertiesRepository.getById(entity.getId());
    }

    @Override
    public void delete(ConnectionPropertiesEntity entity) {
        connectionPropertiesRepository.delete(entity);
    }

    @Override
    public List<String> getConnectionTableList(UUID id) throws SQLException, ConnectionNotFoundException {
        Optional<ConnectionPropertiesEntity> connectionPropertiesEntityOptional = getById(id);

        if(connectionPropertiesEntityOptional.isPresent()){
            ConnectionPropertiesDto connectionPropertiesDto =
                    this.connectionPropertiesAdapter.toDto(connectionPropertiesEntityOptional.get());


            Connection connection = connectionPropertiesDto.generateConnection();
            DatabaseMetaData dbMeta = connection.getMetaData();

            ResultSet rs = dbMeta.getTables(connection.getCatalog(), "", null, new String[]{"TABLE"});
            List<String> tables = new ArrayList<String>();
            while(rs.next()){
                String tableName = rs.getString("TABLE_NAME");
                tables.add(tableName);
            }
            connection.close();
            return tables;
        }
        else
            throw new ConnectionNotFoundException(id);
    }

    @Override
    public List<ColumnDetails> getConnectionTableColumnList(UUID id,
                                                            String tableName) throws SQLException {
        Optional<ConnectionPropertiesEntity> connectionPropertiesEntityOptional = getById(id);

        if(connectionPropertiesEntityOptional.isPresent()){
            ConnectionPropertiesDto connectionPropertiesDto =
                    this.connectionPropertiesAdapter.toDto(connectionPropertiesEntityOptional.get());


            Connection connection = connectionPropertiesDto.generateConnection();
            DatabaseMetaData dbMeta = connection.getMetaData();

            ResultSet rs = dbMeta.getColumns(null, null, tableName, null);
            List<ColumnDetails> columns = new ArrayList<>();
            while(rs.next()){
                String name = rs.getString("COLUMN_NAME");
                String type = rs.getString("TYPE_NAME");
                int size = rs.getInt("COLUMN_SIZE");
                columns.add(new ColumnDetails(name, type, size));
            }
            connection.close();
            return columns;
        }
        else
            throw new ConnectionNotFoundException(id);

    }


    @Override
    public List<ColumnDetails> getConnectionQueryColumnList(UUID id,
                                                            String query) throws SQLException {
        Optional<ConnectionPropertiesEntity> connectionPropertiesEntityOptional = getById(id);

        if(connectionPropertiesEntityOptional.isPresent()){
            ConnectionPropertiesDto connectionPropertiesDto =
                    this.connectionPropertiesAdapter.toDto(connectionPropertiesEntityOptional.get());


            Connection connection = connectionPropertiesDto.generateConnection();

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            List<ColumnDetails> columns = new ArrayList<>();
            for(int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                String name = resultSetMetaData.getColumnName(i);
                String type = resultSetMetaData.getColumnTypeName(i);
                int size = resultSetMetaData.getColumnDisplaySize(i);
                columns.add(new ColumnDetails(name, type, size));
            }

            stmt.close();
            connection.close();
            return columns;
        }
        else
            throw new ConnectionNotFoundException(id);

    }
}
