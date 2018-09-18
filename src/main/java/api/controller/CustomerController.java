package api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.model.CustomerResponse;
import api.model.Customer;
import api.model.Error;
import api.model.ErrorResponse;
import api.service.CustomerService;
import rx.Single;

import java.util.Objects;

@RestController
@Lazy
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = Objects.requireNonNull(service);
    }

    @RequestMapping(value = "/clientes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Single<CustomerResponse> findAll() {
        return service.findAll()
                .doOnNext(this::trace)
                .toList()
                .map(CustomerResponse::new)
                .toSingle()
                .doOnSuccess(this::success);
    }

    private void trace(Customer customer) {
        LOGGER.debug("{}", customer);
    }

    private void success(CustomerResponse response) {
        LOGGER.info("{} cliente(s) retornados com sucesso", response.getCustomers().size());
    }

    @ExceptionHandler
    @ResponseBody
    protected ResponseEntity<Object> handle(Exception e) {
        LOGGER.error("Erro ao obter lista de clientes: {}", e);
        ErrorResponse errorResponse = new ErrorResponse(new Error("500", "Problemas no processamento"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
