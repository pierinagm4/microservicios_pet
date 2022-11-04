package com.pet.employee.service.interfaces;


import com.pet.employee.entity.Employee;
import com.pet.employee.entity.EmployeeSkill;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeService {

    Employee saveEmployee(Employee employee, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable);

    Optional<Employee> getEmployee (Long employeeId);

    List<Employee> findEmployeesForService(DayOfWeek day, Set<EmployeeSkill> skills);

}
