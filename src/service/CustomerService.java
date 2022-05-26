package service;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    /*singleton implement*/
    private static CustomerService singletonInstance = new CustomerService();
    public static CustomerService getInstance(){
        return singletonInstance;
    }
    /*provide static reference to
    restrict instantiation of the class from other classes.*/
    private CustomerService(){}
    private static Map<String, Customer> customer = new HashMap<>();


    /*Use Jun-Wei L question as reference to implement HashMap collection
    to store the Customer, that addCustomer adn getCustomer can be simply implemented
     */

    public void addCustomer(String email, String firstName, String lastName){
        customer.put(email, new Customer(firstName, lastName, email));
    }
    public static Customer getCustomer(String customerEmail){
        return customer.get(customerEmail);
    }
    public static Collection<Customer> getAllCustomer(){
        return customer.values();
    }


}
