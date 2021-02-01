package de.mediqon.generic.data_dashboard.controllers;

import de.mediqon.generic.data_dashboard.adapters.IConnectionPropertiesAdapter;
import de.mediqon.generic.data_dashboard.adapters.ICustomerAdapter;
import de.mediqon.generic.data_dashboard.adapters.IWorkbookAdapter;
import de.mediqon.generic.data_dashboard.dataconnection.entities.ConnectionPropertiesEntity;
import de.mediqon.generic.data_dashboard.dataconnection.entities.WorkbookEntity;
import de.mediqon.generic.data_dashboard.dataconnection.enums.EDatabaseType;
import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.enums.EConnectionStatus;
import de.mediqon.generic.data_dashboard.exceptions.ConnectionNotFoundException;
import de.mediqon.generic.data_dashboard.models.dto.CustomerDto;
import de.mediqon.generic.data_dashboard.models.dto.data.ColumnDetails;
import de.mediqon.generic.data_dashboard.models.dto.data.ConnectionPropertiesDto;
import de.mediqon.generic.data_dashboard.models.dto.data.QueryColumnRequest;
import de.mediqon.generic.data_dashboard.models.dto.data.workbook.WorkbookDto;
import de.mediqon.generic.data_dashboard.services.ICustomerService;
import de.mediqon.generic.data_dashboard.services.data.IConnectionPropertiesService;
import de.mediqon.generic.data_dashboard.services.data.IWorkbookService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.sql.Connection;
import java.util.*;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(value = "/workbook/data")
public class WorkbookController {

    private final IWorkbookService workbookService;
    private final IWorkbookAdapter workbookAdapter;
    private final IConnectionPropertiesService connectionPropertiesService;
    private final IConnectionPropertiesAdapter connectionPropertiesAdapter;
    private final ICustomerService customerService;
    private final ICustomerAdapter customerAdapter;

    public WorkbookController(IWorkbookService workbookService,
                              IWorkbookAdapter workbookAdapter,
                              IConnectionPropertiesService connectionPropertiesService,
                              IConnectionPropertiesAdapter connectionPropertiesAdapter,
                              ICustomerService customerService,
                              ICustomerAdapter customerAdapter) {
        this.workbookService = workbookService;
        this.workbookAdapter = workbookAdapter;
        this.connectionPropertiesService = connectionPropertiesService;
        this.connectionPropertiesAdapter = connectionPropertiesAdapter;
        this.customerService = customerService;
        this.customerAdapter = customerAdapter;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/readall/{customerId}")
    public HttpResponse<Map<String, Object>> getAllConnections(Authentication authentication, UUID customerId) {
        List<WorkbookEntity> entityList =  this.workbookService.getAllCustomerWorkbooks(customerId);
        List<WorkbookDto> dtoList =  this.workbookAdapter.toDtoList(entityList);

        Map<String, Object> map = new HashMap<>();
        map.put("workbooks", dtoList);
        map.put("customers", getAllCustomers());

        return HttpResponse.ok(map);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/new/{customerId}")
    public HttpResponse<Map<String, Object>> initialNewConnectionPage(Authentication authentication,
                                                                      UUID customerId) {
        WorkbookDto newDto = new WorkbookDto();

        Map<String, Object> map = getPageInitialData(newDto);

        return HttpResponse.ok(map);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/read/{customerId}/{id}")
    public HttpResponse<Map<String, Object>> initialReadConnectionPage(UUID id,
                                                                       Authentication authentication,
                                                                       UUID customerId) {
        Optional<WorkbookEntity> workbookEntityOptional = this.workbookService.getById(id);

        if(workbookEntityOptional.isPresent()){
            WorkbookDto workbookDto = this.workbookAdapter.toDto(workbookEntityOptional.get());

            Map<String, Object> map = getPageInitialData(workbookDto);

            return HttpResponse.ok(map);
        }

        return HttpResponse.notFound();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/clone/{customerId}/{id}")
    public HttpResponse<Map<String, Object>> initialCloneConnectionPage(UUID id,
                                                                        Authentication authentication,
                                                                        UUID customerId) {
        Optional<WorkbookEntity> workbookEntityOptional = this.workbookService.getById(id);

        if(workbookEntityOptional.isPresent()){
            WorkbookDto workbookDto = this.workbookAdapter.toDto(workbookEntityOptional.get());
            workbookDto.setName(workbookDto.getName() + "_1");

            Map<String, Object> map = getPageInitialData(workbookDto);

            return HttpResponse.ok(map);
        }

        return HttpResponse.notFound();
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Post("/create/{customerId}")
    public HttpResponse<WorkbookDto>
        create(@Body @Valid  WorkbookDto workbookDto,
               Authentication authentication,
               UUID customerId) {

        WorkbookEntity entity = this.workbookAdapter.fromDto(workbookDto);

        Optional<WorkbookEntity> savedEntityOptional = this.workbookService.create(entity);
        if(savedEntityOptional.isPresent()){
            WorkbookDto savedDto = this.workbookAdapter.toDto(savedEntityOptional.get());

            return HttpResponse.created(savedDto);
        }
        return HttpResponse.badRequest();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/update/{customerId}")
    public HttpResponse<WorkbookDto>
        update(@Body @Valid WorkbookDto workbookDto,
               Authentication authentication,
               UUID customerId) {

        WorkbookEntity entity = this.workbookAdapter.fromDto(workbookDto);

        Optional<WorkbookEntity> savedEntityOptional = this.workbookService.update(entity);
        if(savedEntityOptional.isPresent()){
            WorkbookDto savedDto = this.workbookAdapter.toDto(savedEntityOptional.get());

            return HttpResponse.ok(savedDto);
        }
        return HttpResponse.badRequest();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/delete/{customerId}")
    public HttpResponse<?> delete(@Body @Valid WorkbookDto workbookDto,
                                  Authentication authentication,
                                  UUID customerId) {

        WorkbookEntity entity = this.workbookAdapter.fromDto(workbookDto);

        this.workbookService.delete(entity);

        return HttpResponse.ok();
    }

    private List<ConnectionPropertiesDto> getConnectionDtoList(){
        List<ConnectionPropertiesEntity> connectionEntityList =
                this.connectionPropertiesService.getAll();
        List<ConnectionPropertiesDto> connectionDtoList =
                this.connectionPropertiesAdapter.toDtoList(connectionEntityList);

        return connectionDtoList;
    }

    private List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> entityList =  this.customerService.getAllStatusOk();
        List<CustomerDto> dtoList =  this.customerAdapter.toDtoList(entityList);

        return dtoList;
    }

    private Map<String, Object> getPageInitialData(WorkbookDto workbookDto){
        Map<String, Object> map = new HashMap<>();
        map.put("workbook", workbookDto);
        map.put("connections", getConnectionDtoList());
        map.put("customers", getAllCustomers());

        return map;

    }

}
