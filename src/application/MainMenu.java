package application;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.CollationElementIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class MainMenu {
    private static  HotelResource hotelResource = HotelResource.getInstance();
    public static void startMain(){
        Scanner scanner = new Scanner(System.in);
        printMainMenu();
        String userInput = scanner.nextLine();
        switch (userInput){
            case "1":
            findAndReserveARoom();
                break;
            case "2":
                seeMyReservation();
                break;
            case "3":
                createAccount();
                break;
            case "4":
                AdminMenu.adminMenu();
                break;
            case"5":
                System.out.print("Exit");
                break;
        }
        }

        private static void findAndReserveARoom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your check in date yyyy-mm-dd:");
        Date checkInDate = parseDate(scanner);

        System.out.println("Enter your checkout date yyyy-mm-dd:");
        Date checkOutDate = parseDate(scanner);

        Collection<IRoom> availableRoom = hotelResource.findARoom(checkInDate, checkOutDate);
        if (availableRoom.isEmpty()){
            System.out.println("This room is not available");
        }else{
            reserveARoom(scanner,availableRoom,checkInDate,checkOutDate);
        }

        }

        private static void reserveARoom(Scanner scanner, Collection<IRoom> rooms, Date checkInDate, Date checkOutDate){
//            Collection<IRoom> mainMenuIRoom;
            System.out.println("Would you like to reserve this room ? Y/N");
//        boolean flagCheckroom = false;
        String decision = scanner.nextLine();
        if ("y".equals(decision)){
            System.out.println("Do you have an account? Y/N");
            String accountExist = scanner.nextLine();
            if("y".equals(accountExist)){
                System.out.println("Enter your email:");
                String inputEmail = scanner.nextLine();
                if(HotelResource.getCustomer(inputEmail) != null){
                    System.out.println("Choose your room");
                    String roomID = scanner.nextLine();

                    IRoom bookRoom = hotelResource.getRoom(roomID);
                    Reservation reservation = hotelResource.bookARoom(inputEmail, bookRoom, checkInDate,checkOutDate);
                    System.out.println("Your room is reserved");

                }else{
                    System.out.println("Your account does not exist");
                    printMainMenu();
                }

            }else{
                System.out.println("Please create your account");
                printMainMenu();
            }
        }else{
            System.out.println("You choose not to reserve a room!");
            printMainMenu();
        }
        }

    private static void  seeMyReservation(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        printReservation(hotelResource.getCustomerReservations(email));

    }
    private static void printReservation(Collection<Reservation> reservations){
        for (Reservation reservation: reservations){
            System.out.println(reservation);
        }
    }
    private static void createAccount(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter you email:");
        String accountEmail = scanner.nextLine();

        System.out.println("Enter your first name:");
        String  accountFirstName = scanner.nextLine();

        System.out.println("Enter your last name:");
        String  accountLastName = scanner.nextLine();

        try{
            hotelResource.createCustomer(accountEmail,accountFirstName,accountLastName);
            System.out.println("Account created");
            printMainMenu();
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getLocalizedMessage());
            createAccount();
        }
    }


    public static void printMainMenu() {
        System.out.print("Welcome to the Hotel Reservation Application\n" +
                "--------------------------------------------\n"+
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an Account\n" +
                "4. Admin\n" +
                "5. Exit\n");
    }

    private static Date parseDate(Scanner scanner){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return format.parse(scanner.nextLine());
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }
    }

