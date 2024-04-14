package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.IEmployeeDao;
import com.example.dao.ILoginDao;
import com.example.entity.Employee;
import com.example.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	IEmployeeDao empDao;

	@Autowired
	ILoginDao loginDao;

	@Override
	public List<Employee> getAllEmployees() {

		// Get all employee from db
		List<Employee> lst = empDao.findAll();

		return lst;
	}

	@Override
	public Employee addEmployee(Employee emp) {

		// Call dao/repository method to add employee in db
		Employee e = empDao.save(emp);

		// return response
		return e;
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {

		// call dao/repository method to get employee by id
		Optional<Employee> opt = empDao.findById(id);

		// return emp obj if present else return null
		if (opt.isPresent()) {
			return opt.get();
		}

		throw new EmployeeNotFoundException("Employee not found with id: " + id);
	}

	@Override
	public Employee updateEmployee(Employee emp) {

		// call dao/repository method to get employee by id
		Optional<Employee> opt = empDao.findById(emp.getEmpId());

		// return emp obj if present else return null
		if (opt.isPresent()) {
			Employee e = empDao.save(emp);

			return e;
		}

		return null;
	}

	@Override
	public void deleteEmployeeById(int id) {
		// call dao delete method to remove employee from db
		Optional<Employee> opt = empDao.findById(id);

		// delete emp from db, if present else return null
		if (opt.isPresent()) {
			empDao.deleteById(id);

		}
	}

	@Override
	public Employee updateContactNo(int id, String newContactNo) {

		// call dao delete method to remove employee from db
		Optional<Employee> opt = empDao.findById(id);

		// update emp contactNo if present else return null
		if (opt.isPresent()) {
			// get employee from optional using get()
			Employee emp = opt.get();

			// update contact no.
			emp.setContactNo(newContactNo);

			// save updated emp in the db.
			Employee e = empDao.save(emp);

			// return updated employee
			return e;

		}

		return null;
	}

}
