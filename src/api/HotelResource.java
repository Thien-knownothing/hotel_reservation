package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import javax.xml.namespace.QName;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResourceSingleton = new HotelResource();
    public static HotelResource getInstance(){
        return hotelResourceSingleton;
    };
    private  HotelResource(){}

    public static Customer getCustomer (String email){
        return CustomerService.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName){
        CustomerService.getInstance().addCustomer(email, firstName, lastName);

    }

    public IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);

    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
       return ReservationService.reserveARoom(getCustomer(customerEmail),room, checkInDate,checkOutDate);
    }

    public static Collection<Reservation> getCustomerReservations(String customerEmail){
        return ReservationService.getCustomerReservation(getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return ReservationService.findARoom(checkInDate, checkOutDate);
    }
}
