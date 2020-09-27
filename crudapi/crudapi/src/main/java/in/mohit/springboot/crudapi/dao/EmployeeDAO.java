package in.mohit.springboot.crudapi.dao;

import java.util.List;

import in.mohit.springboot.crudapi.model.Employee;

public interface EmployeeDAO {
	
	List<Employee> get();
	
	Employee get(int id);
	
	Employee save(Employee employee);
	
	void delete(int id); 
}
