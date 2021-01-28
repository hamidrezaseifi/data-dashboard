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
public class ServiceApplication implements ApplicationEventListener {

    private final IConnectionPropertiesRepository connectionPropertiesRepository;
    private final IStatementRepository statementRepository;

    public ServiceApplication(IConnectionPropertiesRepository connectionPropertiesRepository,
                              IStatementRepository statementRepository) {
        this.connectionPropertiesRepository = connectionPropertiesRepository;
        this.statementRepository = statementRepository;
    }

    public static void main(String[] args) {
        Micronaut.run(ServiceApplication.class);
    }

    @Override
    public void onApplicationEvent(Object event) {
        /*ConnectionPropertiesEntity connectionPropertiesEntity = createConnection();
        UUID statementId = createStatement(connectionPropertiesEntity);*/

    }

    private ConnectionPropertiesEntity createConnection(){
        ConnectionPropertiesEntity connectionPropertiesEntity = new ConnectionPropertiesEntity();
        //connectionPropertiesEntity.setUrl("jdbc:postgresql://mq-db02.mediqon.local:5433/mq_pg_krankenhaus_norm");
        connectionPropertiesEntity.setName("Krankenhaus Norm");
        connectionPropertiesEntity.setDatabaseName("mq_pg_krankenhaus_norm");
        connectionPropertiesEntity.setDatabaseType(3);
        connectionPropertiesEntity.setServer("mq-db02.mediqon.local");
        connectionPropertiesEntity.setPort(5433);
        connectionPropertiesEntity.setUsername("tableau_read");
        connectionPropertiesEntity.setPassword("tableau_read");
        connectionPropertiesEntity.setStatus(1);
        connectionPropertiesRepository.create(connectionPropertiesEntity);
        return connectionPropertiesRepository.getById(connectionPropertiesEntity.getId()).get();
    }

    private UUID createStatement(ConnectionPropertiesEntity connectionPropertiesEntity){
        StatementEntity statement = new StatementEntity();
        statement.setName("Krankenhause-Liste");
        statement.setTableName("qb_kh_stamm");
        statement.setDistinct(true);
        statement.setStatus(1);
        statement.setConnectionProperties(connectionPropertiesEntity);

        StatementSelectFieldEntity selectFieldEntity = new StatementSelectFieldEntity();
        selectFieldEntity.setFieldLabel("");
        selectFieldEntity.setFieldTypeEnum(EFieldType.VARCHAR);
        selectFieldEntity.setFieldName("kh_key");
        selectFieldEntity.setStatus(1);

        statement.addSelectField(selectFieldEntity);

        selectFieldEntity = new StatementSelectFieldEntity();
        selectFieldEntity.setFieldLabel("");
        selectFieldEntity.setFieldTypeEnum(EFieldType.VARCHAR);
        selectFieldEntity.setFieldName("krankenhaus_name");
        selectFieldEntity.setStatus(1);

        statement.addSelectField(selectFieldEntity);

        statementRepository.create(statement);

        return statement.getId();
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

        /*selectFieldEntity = new StatementSelectFieldEntity();
        selectFieldEntity.setFieldLabel("fallzahl");
        selectFieldEntity.setFieldTypeEnum(EFieldType.INT);
        selectFieldEntity.setFieldName("sum(fallzahl)");
        selectFieldEntity.setStatus(1);

        statement.addSelectField(selectFieldEntity);*/

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