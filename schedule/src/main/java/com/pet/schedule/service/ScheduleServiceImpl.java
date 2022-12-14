package com.pet.schedule.service;

import com.pet.schedule.entity.Employee;
import com.pet.schedule.entity.EmployeeSkill;
import com.pet.schedule.entity.Pet;
import com.pet.schedule.entity.Schedule;
import com.pet.schedule.repository.EmployeeRepo;
import com.pet.schedule.repository.PetRepo;
import com.pet.schedule.repository.ScheduleRepo;
import com.pet.schedule.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PetRepo petRepo;

    @Override
    public Schedule saveSchedule(Schedule schedule, Set<EmployeeSkill> activities, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        if(activities != null && !activities.isEmpty()){
            schedule.setActivities(activities);
        }
        if(employeeIds != null && !employeeIds.isEmpty()){
            employees = employeeIds.stream().map( id -> employeeRepo.getReferenceById(id)).toList();
        }

        if(petIds != null && !petIds.isEmpty()){
            pets = petIds.stream().map( id -> petRepo.getReferenceById(id)).toList();
        }

        schedule.setEmployee(employees);
        schedule.setPet(pets);

        return scheduleRepo.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    @Override
    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepo.findByPet(petId);
    }

    @Override
    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepo.findByEmployee(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepo.findByCustomer(customerId);
    }

}
