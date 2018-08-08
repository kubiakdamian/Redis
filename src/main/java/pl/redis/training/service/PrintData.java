package pl.redis.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.redis.training.model.Person;
import pl.redis.training.repository.EmployeeDAO;

@Service
public class PrintData {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public PrintData(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Bean
    public String getData(){
        Person emp1 = new Person(1L, "Jan", "Nowak");
        Person emp2 = new Person(2L, "Arkadiusz", "Kowalski");
        Person emp3 = new Person(3L, "Mariusz", "Kus");
        employeeDAO.addEmployee(emp1);
        employeeDAO.addEmployee(emp2);
        employeeDAO.addEmployee(emp3);
        System.out.println(employeeDAO.getAllEmployees());

        System.out.println(employeeDAO.getEmployee(2L));

        return "SUCCESS";
    }
}
