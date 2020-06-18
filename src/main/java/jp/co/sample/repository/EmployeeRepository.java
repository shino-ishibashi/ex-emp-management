package jp.co.sample.repository;


import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_Mapper = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date").toLocalDate());
        employee.setMailAddress(rs.getString( "mail_address"));
        employee.setZipCode(rs.getString( "zip_code"));
        employee.setAddress(rs.getString( "address"));
        employee.setTelephone(rs.getString( "telephone"));
        employee.setSalary(rs.getInt( "salary"));
        employee.setCharacteristics(rs.getString( "characteristics"));
        employee.setDependentsCount(rs.getInt( "dependents_count"));

        return employee;
    };



    public List<Employee> findAllEmployees(){
        String sql = "SELECT * FROM employees  ORDER BY hire_date ASC";
        List<Employee> employeeList = template.query(sql,EMPLOYEE_ROW_Mapper);
        return employeeList;
    }

    public Employee load(Integer id){
        String sql = "SELECT * FROM employees WHERE id = :id";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        Employee employee = template.queryForObject(sql, param,EMPLOYEE_ROW_Mapper);



        return employee;
    }

    public void update(Employee employee){
        String sql = "UPDATE employees SET name = :name,image = :image, gender = :gender, hire_date = :hire_date,mail_address = :mail_address , zip_code = :zip_code , address = :address , telephone = :telephone, salary = :salary,characteristics = :characteristics,dependents_count = :dependents_count where id = :id";


        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", employee.getId())
                .addValue("name", employee.getName())
                .addValue("image",employee.getImage())
                .addValue("gender",employee.getGender())
                .addValue("hire_date",employee.getHireDate())
                .addValue("mail_address",employee.getMailAddress())
                .addValue("zip_code",employee.getZipCode())
                .addValue("address",employee.getAddress())
                .addValue("telephone",employee.getTelephone())
                .addValue("salary",employee.getSalary())
                .addValue("characteristics",employee.getCharacteristics())
                .addValue("dependents_count",employee.getDependentsCount());

        template.update(sql, param);
    }

}
