package com.pet.pet.repository;

import com.pet.pet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query( "SELECT c FROM Pet p join p.customer c WHERE p.id = :petId")
    Customer findByPet(@Param("petId") Long petId);

}
