package de.mediqon.generic.data_dashboard.services.impl;

import de.mediqon.generic.data_dashboard.entities.CustomerEntity;
import de.mediqon.generic.data_dashboard.repositories.ICustomerRepository;
import de.mediqon.generic.data_dashboard.services.ICustomerService;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerEntity> getAllStatusOk() {
        return customerRepository.getAllStatusOk();
    }

    @Override
    public Optional<CustomerEntity> getById(UUID id) {
        return customerRepository.getById(id);
    }

    @Override
    public Optional<CustomerEntity> getByKey(String key) {
        return customerRepository.getByKey(key);
    }

    @Override
    public Optional<CustomerEntity> create(CustomerEntity customerEntity) {
        customerRepository.create(customerEntity);

        return customerRepository.getById(customerEntity.getId());
    }

    @Override
    public Optional<CustomerEntity> update(CustomerEntity customerEntity) {
        customerRepository.update(customerEntity);
        return customerRepository.getById(customerEntity.getId());
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        customerRepository.delete(customerEntity);
    }
}
