package com.company.restaurant.controllers;

import com.company.restaurant.dao.EmployeeDao;
import com.company.restaurant.dao.JobPositionDao;
import com.company.restaurant.model.*;
import com.company.util.DataIntegrityException;

import java.util.List;
import java.util.Set;

public class EmployeeControllerImpl implements EmployeeController {
    private static final String OPERATION_IS_NOT_SUPPORTED_PATTERN = "<%s>: operation is not supported for <employee> with id <%d>";

    private JobPositionDao jobPositionDao;
    private EmployeeDao employeeDao;

    private void errorMessage(String message) {
        throw new DataIntegrityException(message);
    }

    private void operationIsNotSupportedMessage(String message, int employeeId) {
        errorMessage(String.format(OPERATION_IS_NOT_SUPPORTED_PATTERN, message, employeeId));
    }

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public JobPosition addJobPosition(String name) {
        return jobPositionDao.addJobPosition(name);
    }

    @Override
    public void delJobPosition(String name) {
        jobPositionDao.delJobPosition(name);
    }

    @Override
    public JobPosition findJobPositionByName(String name) {
        return jobPositionDao.findJobPositionByName(name);
    }

    @Override
    public JobPosition findJobPositionById(int jobPositionId) {
        return jobPositionDao.findJobPositionById(jobPositionId);
    }

    @Override
    public List<JobPosition> findAllJobPositions() {
        return jobPositionDao.findAllJobPositions();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public void delEmployee(Employee employee) {
        employeeDao.delEmployee(employee);
    }

    @Override
    public void delEmployee(int employeeId) {
        employeeDao.delEmployee(employeeId);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public List<Employee> findEmployeeByFirstName(String firstName) {
        return employeeDao.findEmployeeByFirstName(firstName);
    }

    @Override
    public List<Employee> findEmployeeBySecondName(String lastName) {
        return employeeDao.findEmployeeBySecondName(lastName);
    }

    @Override
    public List<Employee> findEmployeeByFirstAndSecondName(String firstName, String secondName) {
        return employeeDao.findEmployeeByFirstAndSecondName(firstName, secondName);
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }

    @Override
    public Set<Order> getEmployeeOrders(Employee employee) {
        Set<Order> result = null;

        if (employee instanceof Waiter) {
            result = ((Waiter) employee).getOrders();

        } else if (employee instanceof CookAndWaiter) {
            result = ((CookAndWaiter) employee).getOrders();

        } else {
            operationIsNotSupportedMessage("EmployeeControllerImpl.getEmployeeOrders", employee.getEmployeeId());
        }

        return result;
    }

    @Override
    public Set<CookedCourse> getEmployeeCookedCourses(Employee employee) {
        Set<CookedCourse> result = null;

        if (employee instanceof Cook) {
            result = ((Cook) employee).getCookedCourses();

        } else if (employee instanceof CookAndWaiter) {
            result = ((CookAndWaiter) employee).getCookedCourses();

        } else {
            operationIsNotSupportedMessage("EmployeeControllerImpl.getEmployeeCookedCourses", employee.getEmployeeId());
        }

        return result;
    }
}