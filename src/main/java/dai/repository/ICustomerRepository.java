package dai.repository;

import dai.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    void save(Customer customer);
    void delete(Long id);
    Customer findById(Long id);
}
