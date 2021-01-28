package de.mediqon.generic.data_dashboard;



import de.mediqon.generic.data_dashboard.dataconnection.entities.*;
import de.mediqon.generic.data_dashboard.dataconnection.enums.EFieldType;
import de.mediqon.generic.data_dashboard.repositories.IConnectionPropertiesRepository;
import de.mediqon.generic.data_dashboard.repositories.IStatementRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.Micronaut;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class MqAppServiceApplication implements ApplicationEventListener {

    private final IConnectionPropertiesRepository connectionPropertiesRepository;
    private final IStatementRepository statementRepository;

    public MqAppServiceApplication(IConnectionPropertiesRepository connectionPropertiesRepository,
                                   IStatementRepository statementRepository) {
        this.connectionPropertiesRepository = connectionPropertiesRepository;
        this.statementRepository = statementRepository;
    }

    public static void main(String[] args) {
        Micronaut.run(MqAppServiceApplication.class);
    }

    @Override
    public void onApplicationEvent(Object event) {
        /*UUID statementId = createStatement();
        addConnection(statementId);
        addSelectFields(statementId);
        addGroupByFields(statementId);
        addHavingFields(statementId);*/
    }

    private void createConnection(){
        ConnectionPropertiesEntity connectionPropertiesEntity = new ConnectionPropertiesEntity();
        connectionPropertiesEntity.setUrl("jdbc:postgresql://mq-db02.mediqon.local:5433/mq_pg_krankenhaus_norm");
        connectionPropertiesEntity.setUsername("tableau_read");
        connectionPropertiesEntity.setPassword("tableau_read");
        connectionPropertiesEntity.setStatus(1);
        connectionPropertiesRepository.create(connectionPropertiesEntity);
    }

    private UUID createStatement(){
        StatementEntity statement = new StatementEntity();
        statement.setName("Fachabteilung-Liste");
        statement.setTableName("fact_qb_icd");
        statement.setDistinct(true);
        statement.setStatus(1);
        statementRepository.create(statement);

        return statement.getId();
    }

    private void addConnection(UUID statementId){
        Optional<ConnectionPropertiesEntity> connectionPropertiesEntityOptional =
                connectionPropertiesRepository.getById(UUID.fromString("58362d83-9d3f-4d8f-a40f-360632d30f11"));
        ConnectionPropertiesEntity connectionProperties = connectionPropertiesEntityOptional.get();

        Optional<StatementEntity> StatementEntityOptional = statementRepository.getById(statementId);
        StatementEntity statement = StatementEntityOptional.get();
        statement.addConnection(connectionProperties);
        statementRepository.update(statement);
    }

    private void addSelectFields(UUID statementId){
        Optional<StatementEntity> StatementEntityOptional = statementRepository.getById(statementId);
        StatementEntity statement = StatementEntityOptional.get();

        StatementSelectFieldEntity selectFieldEntity = new StatementSelectFieldEntity();
        selectFieldEntity.setFieldLabel("");
        selectFieldEntity.setFieldTypeEnum(EFieldType.VARCHAR);
        selectFieldEntity.setFieldName("kh_key");
        selectFieldEntity.setStatus(1);

        statement.addSelectField(selectFieldEntity);

        selectFieldEntity = new StatementSelectFieldEntity();
        selectFieldEntity.setFieldLabel("");
        selectFieldEntity.setFieldTypeEnum(EFieldType.VARCHAR);
        selectFieldEntity.setFieldName("fachabteilung_name");
        selectFieldEntity.setStatus(1);

        statement.addSelectField(selectFieldEntity);

        selectFieldEntity = new StatementSelectFieldEntity();
        selectFieldEntity.setFieldLabel("fallzahl");
        selectFieldEntity.setFieldTypeEnum(EFieldType.INT);
        selectFieldEntity.setFieldName("sum(fallzahl)");
        selectFieldEntity.setStatus(1);

        statement.addSelectField(selectFieldEntity);

        statementRepository.update(statement);
    }

    private void addGroupByFields(UUID statementId){
        Optional<StatementEntity> StatementEntityOptional = statementRepository.getById(statementId);
        StatementEntity statement = StatementEntityOptional.get();

        StatementGroupByFieldEntity fieldEntity = new StatementGroupByFieldEntity();
        fieldEntity.setFieldName("kh_key");
        fieldEntity.setStatus(1);

        statement.addGroupByField(fieldEntity);

        fieldEntity = new StatementGroupByFieldEntity();
        fieldEntity.setFieldName("fachabteilung_name");
        fieldEntity.setStatus(1);

        statement.addGroupByField(fieldEntity);



        statementRepository.update(statement);
    }

    private void addHavingFields(UUID statementId){
        Optional<StatementEntity> StatementEntityOptional = statementRepository.getById(statementId);
        StatementEntity statement = StatementEntityOptional.get();

        StatementHavingFieldEntity fieldEntity = new StatementHavingFieldEntity();
        fieldEntity.setFieldName("kh_key");
        fieldEntity.setFieldValue("");
        fieldEntity.setStatus(1);

        statement.addHavingField(fieldEntity);

        statementRepository.update(statement);
    }
}