package com.example.secureApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.secureApp.model.Employee;
import com.example.secureApp.model.Employee.gender;

@RestController
class HomeController {

	@RequestMapping(method = RequestMethod.GET, value = { "/employees", "/" })
	public ResponseEntity<List<Employee>> getHomeUrl() {
		List<Employee> employees = new ArrayList<Employee>();
		for (int i = 0; i < 10; i++)
			employees.add(new Employee(i, "firstName" + i, "lastName" + i, 30 + i,
					(i / 2 == 0) ? gender.FEMALE : gender.MALE));

		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

}
