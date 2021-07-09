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
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Transactional
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


    public Customer saveCustomer(Customer customer) {
        Customer  savedCustomer= customerRepository.save(customer);
        /*if(savedCustomer.getPets()!=null){
            for(Pet p : savedCustomer.getPets()){
                p.setOwner(savedCustomer);
            }
        }*/
        System.out.println("Inside UserService.saveCustomer(Customer customer)");
        System.out.println("savedCustomer:="+savedCustomer);
        return savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<Pet> pets = petRepository.findAll();
        for(int i=0;i<customers.size();i++){
            Customer c = customers.get(i);
            for(int j=0;j<pets.size();j++){
                Pet p = pets.get(i);
                if(p.getOwner().equals(c)){
                    //c.getPets().add(p);
                }

            }
        }
        return customers;
    }

    public Customer getCustomerById(Long customerId) {
        System.out.println("Inside public  Customer getCustomerById(Long customerId)");
        System.out.println("customerRepository.findById(customerId)=" + customerRepository.findById(customerId));
        System.out.println("customerRepository.findAll()=" + customerRepository.findAll());
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new CustomerNotFoundException("Customer with id=" + customerId + " not found");
        }
    }


    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException("Employee with id=" + employeeId + " not found.");
        }
    }

    public Pet savePet(Pet pet) {
        /*Customer owner = pet.getOwner();
        System.out.println("owner is: " + owner);
        System.out.println("Inside UserService.savePet(Pet pet)");
        if (owner != null) {
            System.out.println("Owner returned by pet.getOwner() is not null");
            {
                owner.getPets().add(pet);
            }
            Customer customer = customerRepository.save(owner);

            System.out.println("customer:-"+customer);
        } else {
            System.out.println("Owner returned by pet.getOwner() is null");
            System.out.println("Owner is not associated with pet:=" + pet);
        }
        return petRepository.save(pet);*/
        Customer owner = pet.getOwner();
        System.out.println("owner is: " + owner);
        System.out.println("Inside UserService.savePet(Pet pet)");
        Pet savedPet = petRepository.save(pet);
        if (owner != null) {
            System.out.println("Owner returned by pet.getOwner() is not null");
            {
                owner.getPets().add(savedPet);
            }
            Customer customer = customerRepository.save(owner);
            //System.out.println("customer:-"+customer);
        }
        return savedPet;
    }

    public Pet getPetById(long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            System.out.println("optionalPet.get():=" + optionalPet.get());
            return optionalPet.get();
        } else {
            System.out.println("optionalPet.get():= throwing PetNotFoundException");
            throw new PetNotFoundException("Pet with petId=" + petId + " not found");
        }
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Long getOwnerByPetId(long petId) {
        Pet pet = getPetById(petId);
        return pet.getOwner().getId();
    }

    public Customer getOwnerByPet(long petId) {
        Customer customer = petRepository.getOne(petId).getOwner();
        if (customer.getPets() == null) {
            customer.setPets(new ArrayList<Pet>());
            List<Pet> petList = petRepository.findAll();
            for (int i = 0; i < petList.size(); i++) {
                if (petList.get(i).getOwner() == customer) {
                    customer.getPets().add(petList.get(i));
                }
            }
        }
        return customer;
    }
}
