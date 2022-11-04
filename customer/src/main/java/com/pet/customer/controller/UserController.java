package com.pet.customer.controller;

import com.pet.customer.entity.Customer;
import com.pet.customer.entity.Pet;
import com.pet.customer.entity.request.CustomerDTO;
import com.pet.customer.service.CustomerServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        Customer customer = new Customer(customerDTO.getName(),customerDTO.getPhoneNumber(),customerDTO.getNotes());
        List<Long> petsIds = customerDTO.getPetIds();
        CustomerDTO convertedCustomer;
        try {
            convertedCustomer = convertCustomerToCustomerDTO(customerService.saveCustomer(customer, petsIds));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User could not be saved", exception);
        }
        return convertedCustomer;

    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers =  customerService.getAllCustomers();
        return convertListCustomerToListCustomerDTO(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        return convertCustomerToCustomerDTO(customer);
    }


    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        List<Long> petsIds = customer.getPets().stream().map(Pet::getId).toList();
        BeanUtils.copyProperties(customer, customerDTO);
        customerDTO.setPetIds(petsIds);
        return customerDTO;
    }

    private List<CustomerDTO> convertListCustomerToListCustomerDTO(List<Customer> customer) {
        return customer.stream()
                .map(this::convertCustomerToCustomerDTO).toList();
    }

}
