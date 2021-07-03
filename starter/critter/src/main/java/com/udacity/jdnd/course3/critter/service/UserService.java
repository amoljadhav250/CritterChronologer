package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.dao.CustomerRepository;
import com.udacity.jdnd.course3.critter.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

CustomerRepository customerRepository;

    @Autowired
    public UserService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
