package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Customer;

public interface ICustomerRepository extends IBaseRepository<Customer, Long> {
    Customer findByUsername(String username);
}
