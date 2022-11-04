package com.pet.customer.service.interfaces;

import com.pet.customer.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer, List<Long> pets);

    List<Customer> getAllCustomers();

    Customer getOwnerByPet (Long petId);

}
