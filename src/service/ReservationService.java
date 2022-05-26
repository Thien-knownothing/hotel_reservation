package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Date;
import java.util.HashSet;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationSingleton = new ReservationService();
    public ReservationService getReservationSingleton(){
        return reservationSingleton;
    }
    /*singleton implement*/
    public ReservationService(){};
    /*provide static reference to
    restrict instantiation of the class from other classes using Lazy Instantiate.*/
/*    private Map<String, IRoom> rooms = new HashMap<>();
    private Map<String, Collection<Reservation>> reservations = new HashMap<>();*/
    public static Collection<Reservation> reservations = new HashSet<>();
    public static Map<String, IRoom> roomsList = new HashMap<String, IRoom>();


    /* Implement HashSet and followed method instead of HashMap base on answer to Cory S question
     */

    public static void addRoom(IRoom room){
        roomsList.put(room.getRoomNumber(), room);
    }

    public static IRoom getARoom(String roomId){
            return roomsList.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservedRoom = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservedRoom);
        return reservedRoom;
    }

    static boolean isDateValid(Date checkInDate, Date checkOutDate, Reservation reservation){
        return checkInDate.before(reservation.getCheckOutDate())&&checkOutDate.after(reservation.getCheckInDate());
    }
    public static Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {

        Collection<IRoom> rooms = getAllRooms();
        for (Reservation reservation : reservations) {
            if (isDateValid(checkInDate, checkOutDate, reservation)){
                rooms.remove(reservation.getRoom());
            }else{
                System.out.println("Sorry! there is no available room");
            }
        }
        Collection<IRoom> validRoom = rooms.stream().toList();
        return validRoom;
    }
    /*reference to answer to Bambang's question

     */
    public static Collection<Reservation> getCustomerReservation(Customer customer){
     CustomerService.getInstance().getCustomer(customer.getEmail());
     return reservations;
    }

    public static void printAllReservations(){
        for (Reservation reservation: reservations){
            System.out.println(reservation);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        System.out.println("All room:");
        System.out.println(roomsList.values());
        return roomsList.values();
    }
}
