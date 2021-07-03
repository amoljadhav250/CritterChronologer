package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.dao.CustomerRepository;
import com.udacity.jdnd.course3.critter.dao.EmployeeRepository;
import com.udacity.jdnd.course3.critter.dao.PetRepository;
import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private PetRepository petRepository;

    @Autowired
    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public  Customer getCustomerById(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }else{
            throw new CustomerNotFoundException("Customer with id="+customerId+" not found");
        }
    }


    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long employeeId) {
        Optional<Employee> employee= employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new EmployeeNotFoundException("Employee with id="+employeeId+" not found.");
        }
    }

    public Pet savePet(Pet convertDTOtoPet) {
        return petRepository.save(convertDTOtoPet);
    }

    public Pet getPetById(long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if(optionalPet.isPresent()){
            return optionalPet.get();
        }else{
            throw new PetNotFoundException("Pet with petId="+petId+" not found");
        }
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Long getOwnerByPetId(long petId) {
        Pet pet = getPetById(petId);
        return pet.getOwner().getId();
    }
}
