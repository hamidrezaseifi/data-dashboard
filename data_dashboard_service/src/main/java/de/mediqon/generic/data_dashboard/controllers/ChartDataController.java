package de.mediqon.generic.data_dashboard.controllers;

import de.mediqon.generic.data_dashboard.dataconnection.StatementExecuter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.StatementEntity;
import de.mediqon.generic.data_dashboard.repositories.IStatementRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.sql.SQLException;
import java.util.*;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(value = "/charts/data")
public class ChartDataController {


    private List<Map<String, Object>> krankenhaus = null;

    private ConnectionPropertiesEntity krankenhausNormConnectionProperties = null;

    private final IStatementRepository statementRepository;

    public ChartDataController(IStatementRepository statementRepository) {
        this.statementRepository = statementRepository;

        krankenhausNormConnectionProperties = new ConnectionPropertiesEntity();
        krankenhausNormConnectionProperties.setUrl("jdbc:postgresql://mq-db02.mediqon.local:5433/mq_pg_krankenhaus_norm");
        krankenhausNormConnectionProperties.setUsername("tableau_read");
        krankenhausNormConnectionProperties.setPassword("tableau_read");
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/krankenhaus")
    public HttpResponse<List<Map<String, Object>>> listKankenhaus() throws SQLException {

        if(krankenhaus == null){

            Optional<StatementEntity> statementEntityOptional = statementRepository.getByName("Krankenhause-Liste");
            if(statementEntityOptional.isPresent()){
                StatementExecuter statementExecuter =
                        new StatementExecuter(statementEntityOptional.get());

                krankenhaus = statementExecuter.execute();
            }

        }


        return HttpResponse.ok(krankenhaus);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/krankenhaus/fachabteilung/{khKey}")
    public HttpResponse<List<Map<String, Object>>> listFachabteilung(String khKey) throws SQLException {

        String sql = String.format("select distinct kh_key, fachabteilung_name, " +
                                   "sum(fallzahl) as fallzahl " +
                                   "from fact_qb_icd " +
                                   "group by kh_key, fachabteilung_name " +
                                   "having kh_key='%s'", khKey);

        List<Map<String, Object>> results = null;
        Optional<StatementEntity> statementEntityOptional = statementRepository.getByName("Fachabteilung-Liste");
        if(statementEntityOptional.isPresent()){
            StatementExecuter statementExecuter = new StatementExecuter(statementEntityOptional.get());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("kh_key", khKey);
            results = statementExecuter.execute(parameters);
        }

       // StatementExecuter statementExecuter = new StatementExecuter(krankenhausNormConnectionProperties, sql);

        //results = statementExecuter.execute();

        return HttpResponse.ok(results);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/krankenhaus/erkrankung/{khKey}")
    public HttpResponse<List<Map<String, Object>>> listErkrankung(String khKey) throws SQLException {

        String sql = String.format("select distinct kh_key, fachabteilung_name, erkrankung_kapitel, erkrankung_obergruppe, " +
                                   "sum(fallzahl) as fallzahl " +
                                   "from fact_qb_icd " +
                                   "group by kh_key, fachabteilung_name, erkrankung_kapitel, erkrankung_obergruppe " +
                                   "having kh_key='%s'", khKey);

        StatementExecuter statementExecuter = new StatementExecuter(krankenhausNormConnectionProperties, sql);

        List<Map<String, Object>> results = statementExecuter.execute();

        return HttpResponse.ok(results);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/krankenhaus/behandlung/{khKey}")
    public HttpResponse<List<Map<String, Object>>> listBehandlung(String khKey) throws SQLException {

        String sql = String.format("select distinct kh_key, fachabteilung_name, behandlung_kapitel, behandlung_obergruppe, " +
                                   "sum(anzahl) as fallzahl " +
                                   "from fact_qb_ops " +
                                   "group by kh_key, fachabteilung_name, behandlung_kapitel, behandlung_obergruppe " +
                                   "having kh_key='%s'", khKey);

        StatementExecuter statementExecuter = new StatementExecuter(krankenhausNormConnectionProperties, sql);

        List<Map<String, Object>> results = statementExecuter.execute();

        return HttpResponse.ok(results);
    }


}
