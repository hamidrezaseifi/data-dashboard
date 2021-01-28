package de.mediqon.generic.data_dashboard.dataconnection.entities;

import de.mediqon.generic.data_dashboard.entities.base.BaseEntity;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


@Entity
@Table(name = "connection_properties")
public class ConnectionPropertiesEntity extends BaseEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    protected Integer status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @ManyToMany(mappedBy = "connections")
    private Set<StatementEntity> statements = new HashSet<>();

    public ConnectionPropertiesEntity() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Set<StatementEntity> getStatements() {
        return statements;
    }

    public void setStatements(Set<StatementEntity> statements) {
        this.statements = statements;
    }

    public Connection generateConnection() throws SQLException {

        Properties props = new Properties();
        props.setProperty("user",username);
        props.setProperty("password", password);
        //props.setProperty("ssl","true");
        Connection conn = DriverManager.getConnection(url, props);

        return conn;
    }
}
