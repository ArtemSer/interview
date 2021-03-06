package tk.sierikov.task4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.sierikov.task4.domain.employees.Employee;
import tk.sierikov.task4.domain.employees.EmployeeType;
import tk.sierikov.task4.domain.employees.Manager;
import tk.sierikov.task4.domain.employees.Programmer;
import tk.sierikov.task4.repository.EmployeeRepo;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeDao {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Optional<Employee> create(EmployeeType type, Double salary, Integer neededHours) {
        switch (type) {
            case MANAGER:
                return Optional.of(employeeRepo.save(new Manager(salary, neededHours)));
            case PROGRAMMER:
                return Optional.of(employeeRepo.save(new Programmer(salary, neededHours)));
            default:
                return Optional.empty();
        }
    }

    public Iterable<Employee> read() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> read(UUID id) {
        return employeeRepo.findById(id);
    }

    public Optional<Iterable<Employee>> read(EmployeeType type) {
        return Optional.of(employeeRepo.findAllByType(type));
    }

    public Optional<Employee> update(UUID id, Integer hours) {
        return this.read(id).map(
                employee -> {
                    employee.addRealHours(hours);
                    return Optional.of(employeeRepo.save(employee));
                }
        ).orElseGet(Optional::empty);
    }

    public void delete(Employee employee) {
        employeeRepo.delete(employee);
    }

    public boolean delete(UUID id) {
        return this.read(id).map(
                employee -> {
                    this.delete(employee);
                    return true;
                }
        ).orElse(false);
    }
}
