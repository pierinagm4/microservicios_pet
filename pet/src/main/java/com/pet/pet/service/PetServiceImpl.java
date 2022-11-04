package com.pet.pet.service;


import com.pet.pet.entity.Customer;
import com.pet.pet.entity.Pet;
import com.pet.pet.repository.CustomerRepo;
import com.pet.pet.repository.PetRepo;
import com.pet.pet.service.interfaces.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepo petRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Pet savePet(Pet pet, Long customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            pet.setCustomer(customer);
        }
        return petRepo.save(pet);
    }

    @Override
    public Optional<Pet> getPet(Long petId) {
        return petRepo.findById(petId);
    }

    @Override
    public List<Pet> getPets() {
        return petRepo.findAll();
    }

    @Override
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepo.findByOwnerId(ownerId);
    }
}
