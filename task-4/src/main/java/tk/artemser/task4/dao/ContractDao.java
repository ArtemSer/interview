package tk.artemser.task4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.artemser.task4.domain.contract.Contract;
import tk.artemser.task4.repository.ContractRepo;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractDao {
    @Autowired
    private ContractRepo contractRepo;

    public Iterable<Contract> read(){
        return contractRepo.findAll();
    }

    public Optional<Contract> read(UUID id) {
        return contractRepo.findById(id);
    }

    public Contract update(Contract contract) {
        return contractRepo.save(contract);
    }

    public Optional<Contract> update(UUID id, Double salary, Integer hours) {
        return this.read(id).map(contract -> {
            if (salary != null) contract.setSalary(salary);
            if (hours != null) contract.setNeededHours(hours);
            this.update(contract);
            return Optional.of(contract);
        }).orElse(Optional.empty());
    }
}
