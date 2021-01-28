package de.mediqon.generic.data_dashboard.controllers;

import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.services.ICustomerService;
import de.mediqon.generic.data_dashboard.adapters.ICustomerAdapter;
import de.mediqon.generic.data_dashboard.enums.ECustomerStatus;
import de.mediqon.generic.data_dashboard.models.dto.CustomerDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.util.*;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(value = "/customers/data")
public class CustomersController {

    private final ICustomerService customerService;
    private final ICustomerAdapter customerAdapter;

    public CustomersController(ICustomerService customerService, ICustomerAdapter customerAdapter) {
        this.customerService = customerService;
        this.customerAdapter = customerAdapter;

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/all")
    public HttpResponse<List<CustomerDto>> getAllCustomers() {
        List<CustomerEntity> entityList =  this.customerService.getAllStatusOk();
        List<CustomerDto> dtoList =  this.customerAdapter.toDtoList(entityList);

        return HttpResponse.ok(dtoList);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/new")
    public HttpResponse<Map<String, Object>> initialNewCustomerPage() {
        CustomerDto newCustomerDto = CustomerDto.generateNew();

        Map<String, Object> map = new HashMap<>();
        map.put("customer" , newCustomerDto);
        map.put("customerstatus", ECustomerStatus.values());

        return HttpResponse.ok(map);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/initedit/{id}")
    public HttpResponse<Map<String, Object>> initialEditCustomerPage(UUID id) {
        Optional<CustomerEntity> customerEntityOptional = this.customerService.getById(id);
        if(customerEntityOptional.isPresent()){
            CustomerDto customerDto = this.customerAdapter.toDto(customerEntityOptional.get());
            Map<String, Object> map = new HashMap<>();
            map.put("customer" , customerDto);
            map.put("customerstatus", ECustomerStatus.values());

            return HttpResponse.ok(map);
        }

        return  HttpResponse.notFound();

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/create")
    public HttpResponse<CustomerDto> create(@Body @Valid CustomerDto customerDto) {

        CustomerEntity entity = this.customerAdapter.fromDto(customerDto);

        Optional<CustomerEntity> savedEntityOptional = this.customerService.create(entity);
        if(savedEntityOptional.isPresent()){
            CustomerDto savedDto = this.customerAdapter.toDto(savedEntityOptional.get());

            return HttpResponse.created(savedDto);
        }
        return HttpResponse.badRequest();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/update")
    public HttpResponse<CustomerDto> update(@Body @Valid CustomerDto customerDto) {

        CustomerEntity entity = this.customerAdapter.fromDto(customerDto);

        Optional<CustomerEntity> savedEntityOptional = this.customerService.update(entity);
        if(savedEntityOptional.isPresent()){
            CustomerDto savedDto = this.customerAdapter.toDto(savedEntityOptional.get());

            return HttpResponse.ok(savedDto);
        }
        return HttpResponse.badRequest();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/delete")
    public HttpResponse<?> delete(@Body @Valid CustomerDto customerDto) {

        CustomerEntity entity = this.customerAdapter.fromDto(customerDto);

        this.customerService.delete(entity);

        return HttpResponse.ok();
    }

}
