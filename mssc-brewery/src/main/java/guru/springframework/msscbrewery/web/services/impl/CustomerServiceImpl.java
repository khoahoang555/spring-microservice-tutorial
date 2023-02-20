package guru.springframework.msscbrewery.web.services.impl;

import guru.springframework.msscbrewery.web.model.CustomerDTO;
import guru.springframework.msscbrewery.web.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDTO getCustomerById(UUID customerId) {
        return CustomerDTO.builder().id(UUID.randomUUID())
                .name("Demo")
                .build();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId) {
        // todo impl
        log.debug("Updating...");
    }

    @Override
    public void deleteById(UUID customerId) {
        // todo impl
        log.debug("Deleting...");
    }
}
