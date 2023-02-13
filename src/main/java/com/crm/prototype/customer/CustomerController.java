package com.crm.prototype.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getCustomers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return customerService
                .getAllCustomers(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createCustomer(@RequestBody CustomerCreateDTO customerDetails) {
        customerService.createCustomer(customerDetails);
        return "Saved";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerDetails) {
        customerService.updateCustomer(customerDetails);
        return "Updated";
    }
}