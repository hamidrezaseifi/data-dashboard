package de.mediqon.generic.data_dashboard.models.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import de.mediqon.generic.data_dashboard.dataconnection.enums.EDatabaseType;
import de.mediqon.generic.data_dashboard.enums.EConnectionStatus;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties({"databaseTypeInt", "statusInt"})
@Introspected
public class ConnectionPropertiesDto extends BaseDto {

    private String name;

    private String server;

    private int port;

    private String databaseName;

    private EDatabaseType databaseType;

    private String username;

    private String password;

    protected EConnectionStatus status;


    public ConnectionPropertiesDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public EDatabaseType getDatabaseType() {
        return databaseType;
    }

    public int getDatabaseTypeInt() {
        return databaseType.getId();
    }

    @JsonSetter
    public void setDatabaseType(EDatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public void setDatabaseTypeInt(int databaseType) {
        this.databaseType = EDatabaseType.fromId(databaseType);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EConnectionStatus getStatus() {
        return status;
    }

    @JsonSetter
    public void setStatus(EConnectionStatus status) {
        this.status = status;
    }

    public void setStatusInt(Integer status) {
        this.status = EConnectionStatus.fromValue(status);
    }

    public String generateJdbcUrl() {

        String url = "jdbc:";
        url += databaseType.getJdbcPrt() + "://";
        url += server + ":" + port;
        url += "/" + databaseName;

        return url;
    }

    public Connection generateConnection() throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        //props.setProperty("ssl","true");
        Connection conn = DriverManager.getConnection(generateJdbcUrl(), props);

        return conn;
    }
}
