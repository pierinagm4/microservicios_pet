package com.pet.employee.service;

import com.pet.employee.entity.Employee;
import com.pet.employee.entity.EmployeeSkill;
import com.pet.employee.repository.EmployeeRepo;
import com.pet.employee.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {

        if(skills != null && !skills.isEmpty()){
            employee.setSkills(skills);
        }
        if(daysAvailable != null && !daysAvailable.isEmpty()){
            employee.setDaysAvailable(daysAvailable);
        }
        return employeeRepo.save(employee);

    }

    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepo.findById(employeeId);
    }

    @Override
    public List<Employee> findEmployeesForService(DayOfWeek day, Set<EmployeeSkill> skills) {
        List<String> listSkills = skills.stream().map(Enum::name).toList();
        return employeeRepo.findByDayAvailability(day.name(),listSkills);
    }
}
