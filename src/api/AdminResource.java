package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResourceSingleton = new AdminResource();
    public static AdminResource getInstance(){
        return adminResourceSingleton;
    };
    private  AdminResource(){}

    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(IRoom rooms){
        ReservationService.addRoom(rooms);
    }

    public Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public void displayAllReservation(){
        ReservationService.printAllReservations();
    }
    public Collection<Customer> getAllCustomer(){
        return CustomerService.getAllCustomer();
    }
}
