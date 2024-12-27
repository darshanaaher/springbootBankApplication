package com.fullstack.repository;

import com.fullstack.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustAccountId(int custAccountId);
    Customer findByCustEmailIdAndCustPassword(String custEmailId, String custPassword);


}
