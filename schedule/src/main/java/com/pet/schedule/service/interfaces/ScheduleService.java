package com.pet.schedule.service.interfaces;

import com.pet.schedule.entity.EmployeeSkill;
import com.pet.schedule.entity.Schedule;

import java.util.List;
import java.util.Set;

public interface ScheduleService {

    Schedule saveSchedule(Schedule schedule, Set<EmployeeSkill> activities, List<Long> employeeIds , List<Long> petIds);

    List<Schedule> getAllSchedules();

    List<Schedule> getScheduleForPet(long petId);

    List<Schedule> getScheduleForEmployee(long employeeId);

    List<Schedule> getScheduleForCustomer(long customerId);

}
