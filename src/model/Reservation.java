package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private  IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    }

    @Override
    public String toString(){
        return "Customer" + customer + "\n" +
                "Reservation" + room + "\n" +
                "Duration From" + checkInDate + "To" + checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public IRoom getRoom() {
        return room;
    }

}
