package com.udacity.jdnd.course3.critter.dao;

import com.udacity.jdnd.course3.critter.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
