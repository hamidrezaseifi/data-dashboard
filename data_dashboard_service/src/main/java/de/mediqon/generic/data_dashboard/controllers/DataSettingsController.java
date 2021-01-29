package de.mediqon.generic.data_dashboard.controllers;

import de.mediqon.generic.data_dashboard.adapters.IConnectionPropertiesAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.StatementExecuter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.dataconnection.enums.EDatabaseType;
import de.mediqon.generic.data_dashboard.enums.EConnectionStatus;
import de.mediqon.generic.data_dashboard.exceptions.ConnectionNotFoundException;
import de.mediqon.generic.data_dashboard.models.dto.data.ColumnDetails;
import de.mediqon.generic.data_dashboard.models.dto.data.ConnectionPropertiesDto;
import de.mediqon.generic.data_dashboard.repositories.IConnectionPropertiesRepository;
import de.mediqon.generic.data_dashboard.services.data.IConnectionPropertiesService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.*;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(value = "/datasettings/data")
public class DataSettingsController {

    private final IConnectionPropertiesService connectionPropertiesService;
    private final IConnectionPropertiesAdapter connectionPropertiesAdapter;

    public DataSettingsController(IConnectionPropertiesService connectionPropertiesService,
                                  IConnectionPropertiesAdapter connectionPropertiesAdapter) {
        this.connectionPropertiesService = connectionPropertiesService;
        this.connectionPropertiesAdapter = connectionPropertiesAdapter;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/connections/readall")
    public HttpResponse<List<ConnectionPropertiesDto>> getAllConnections() {
        List<ConnectionPropertiesEntity> entityList =  this.connectionPropertiesService.getAll();
        List<ConnectionPropertiesDto> dtoList =  this.connectionPropertiesAdapter.toDtoList(entityList);

        return HttpResponse.ok(dtoList);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/connections/new")
    public HttpResponse<Map<String, Object>> initialNewConnectionPage() {
        ConnectionPropertiesDto connectionPropertiesDto = new ConnectionPropertiesDto();
        connectionPropertiesDto.setDatabaseType(EDatabaseType.MS_SQL_SERVER);

        Map<String, Object> map = generateInitialPageData(connectionPropertiesDto);

        return HttpResponse.ok(map);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/connections/read/{id}")
    public HttpResponse<Map<String, Object>> initialReadConnectionPage(UUID id) {
        Optional<ConnectionPropertiesEntity> connectionPropertiesEntityOptional =
                this.connectionPropertiesService.getById(id);

        if(connectionPropertiesEntityOptional.isPresent()){
            ConnectionPropertiesDto connectionPropertiesDto =
                    this.connectionPropertiesAdapter.toDto(connectionPropertiesEntityOptional.get());

            Map<String, Object> map = generateInitialPageData(connectionPropertiesDto);

            return HttpResponse.ok(map);
        }

        return HttpResponse.notFound();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/connections/clone/{id}")
    public HttpResponse<Map<String, Object>> initialCloneConnectionPage(UUID id) {
        Optional<ConnectionPropertiesEntity> connectionPropertiesEntityOptional =
                this.connectionPropertiesService.getById(id);

        if(connectionPropertiesEntityOptional.isPresent()){
            ConnectionPropertiesDto connectionPropertiesDto =
                    this.connectionPropertiesAdapter.toDto(connectionPropertiesEntityOptional.get());
            connectionPropertiesDto.setName(connectionPropertiesDto.getName() + "_1");
            connectionPropertiesDto.setId(UUID.randomUUID());
            Map<String, Object> map = generateInitialPageData(connectionPropertiesDto);

            return HttpResponse.ok(map);
        }

        return HttpResponse.notFound();
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Post("/connections/create")
    public HttpResponse<ConnectionPropertiesDto> create(@Body @Valid ConnectionPropertiesDto connectionPropertiesDto) {

        ConnectionPropertiesEntity entity = this.connectionPropertiesAdapter.fromDto(connectionPropertiesDto);

        Optional<ConnectionPropertiesEntity> savedEntityOptional = this.connectionPropertiesService.create(entity);
        if(savedEntityOptional.isPresent()){
            ConnectionPropertiesDto savedDto = this.connectionPropertiesAdapter.toDto(savedEntityOptional.get());

            return HttpResponse.created(savedDto);
        }
        return HttpResponse.badRequest();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/connections/update")
    public HttpResponse<ConnectionPropertiesDto> update(@Body @Valid ConnectionPropertiesDto connectionPropertiesDto) {

        ConnectionPropertiesEntity entity = this.connectionPropertiesAdapter.fromDto(connectionPropertiesDto);

        Optional<ConnectionPropertiesEntity> savedEntityOptional = this.connectionPropertiesService.update(entity);
        if(savedEntityOptional.isPresent()){
            ConnectionPropertiesDto savedDto = this.connectionPropertiesAdapter.toDto(savedEntityOptional.get());

            return HttpResponse.ok(savedDto);
        }
        return HttpResponse.badRequest();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/connections/delete")
    public HttpResponse<?> delete(@Body @Valid ConnectionPropertiesDto connectionPropertiesDto) {

        ConnectionPropertiesEntity entity = this.connectionPropertiesAdapter.fromDto(connectionPropertiesDto);

        this.connectionPropertiesService.delete(entity);

        return HttpResponse.ok();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/connections/testconnection")
    public HttpResponse<?> testConnection(@Body @Valid ConnectionPropertiesDto connectionPropertiesDto) {

        try {
            Connection connection = connectionPropertiesDto.generateConnection();
            connection.close();
            Map<String, Object> map = new HashMap<>();
            map.put("res" , "ok");
            return HttpResponse.ok(map);
        }
        catch (Exception ex){
            JsonError sqlError = new JsonError(String.format("Fehler in der Verbindung: %s" , ex.getMessage()));
            sqlError.link("self", "/datasettings/data/connections/testconnection");
            return HttpResponse.badRequest(sqlError);
        }
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/connections/tablelist/{connectionId}")
    public HttpResponse<?> getTableList(UUID connectionId) {

        try {
            List<String> tables = connectionPropertiesService.getConnectionTableList(connectionId);
            return HttpResponse.ok(tables);
        }
        catch (ConnectionNotFoundException ex){
            JsonError sqlError = new JsonError(ex.getMessage());
            sqlError.link("self", "/datasettings/data/connections/tablelist/" + connectionId);
            return HttpResponse.badRequest(sqlError);
        }
        catch (Exception ex){
            JsonError sqlError = new JsonError(String.format("Fehler in der Verbindung: %s" , ex.getMessage()));
            sqlError.link("self", "/datasettings/data/connections/tablelist/" + connectionId);
            return HttpResponse.badRequest(sqlError);
        }

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/connections/tablecolumnlist/{connectionId}/{tableName}")
    public HttpResponse<?> getTableColumnList(UUID connectionId, String tableName) {

        try {
            List<ColumnDetails> tables =
                    connectionPropertiesService.getConnectionTableColumnList(connectionId, tableName);
            return HttpResponse.ok(tables);
        }
        catch (ConnectionNotFoundException ex){
            JsonError sqlError = new JsonError(ex.getMessage());
            sqlError.link("self",
                          "/datasettings/data/connections/tablecolumnlist/" + connectionId + "/" + tableName);
            return HttpResponse.badRequest(sqlError);
        }
        catch (Exception ex){
            JsonError sqlError = new JsonError(String.format("Fehler in der Verbindung: %s" , ex.getMessage()));
            sqlError.link("self",
                          "/datasettings/data/connections/tablecolumnlist/" + connectionId + "/" + tableName);
            return HttpResponse.badRequest(sqlError);
        }

    }

    private Map<String , Object> generateInitialPageData(ConnectionPropertiesDto connectionPropertiesDto){

        Map<String, Object> map = new HashMap<>();
        map.put("connection" , connectionPropertiesDto);
        map.put("status", EConnectionStatus.values());
        map.put("dbtype", EDatabaseType.values());

        return map;
    }
}
