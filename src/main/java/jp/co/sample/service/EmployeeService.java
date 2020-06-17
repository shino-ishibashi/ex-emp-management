package jp.co.sample.service;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAllEmployees();
    }

    public Employee load(Integer id){
        return employeeRepository.load(id);
    }

    public void update(Integer id, String dependentsCount){
        employeeRepository.update(id,dependentsCount);
    }
}
