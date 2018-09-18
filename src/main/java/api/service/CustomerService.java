package api.service;

import java.util.Objects;

import api.model.Customer;
import api.repository.CustomersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import rx.Observable;

@Service
@Lazy
public class CustomerService {

    private final CustomersRepository customersRepository;

    @Autowired
    public CustomerService(CustomersRepository customersRepository) {
        this.customersRepository = Objects.requireNonNull(customersRepository);
    }

    public Observable<Customer> findAll() {
        return customersRepository.findAll().map(this::customer);
    }

    private Customer customer(api.repository.Customer row) {

        Customer customer = Customer.builder()
                .id(row.getId())
                .name(row.getName())
                .email(row.getEmail())
                .build();

        return customer;
    }

}
