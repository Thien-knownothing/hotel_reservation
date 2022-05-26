package application;

import api.AdminResource;
import model.*;

import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdminMenu {
    private static AdminResource adminResource = AdminResource.getInstance();
    public static void adminMenu(){
        printAdminMenu();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        switch (userInput){
            case"1":
                seeAllCustomer();
                break;
            case"2":
                seeAllRoom();
                break;
            case "3":
                seeAllReservations();
                break;
            case"4":
                addARoom();
                break;
            case "5":
                System.out.println("Return to Main menu");
                MainMenu.printMainMenu();
            default:
                System.out.println("Please enter a valid input");
        }

    }
    public static void printAdminMenu()
    {
        System.out.print("Welcome to the Hotel Reservation Application\n" +
                "--------------------------------------------\n" +
                "1. See all customers\n" +
                "2. See all rooms\n" +
                "3. See all reservations\n" +
                "4. Ads a room\n" +
                "5. Back to Main menu\n" );
    }

    private static void seeAllCustomer(){
        Collection<Customer> customers = adminResource.getAllCustomer();
        for(Customer customer: customers){
            System.out.println(customer);
        }
    }

    private static void seeAllRoom(){
        Collection<IRoom> rooms = adminResource.getAllRooms();
        for (IRoom room: rooms){
            System.out.println(room);
        }
    }

    private static void seeAllReservations(){
        adminResource.displayAllReservation();
    }


    private static void addARoom(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a room number:");
        String roomNumber = scanner.nextLine();

        System.out.println("Chose room price");
        Double roomPrice = scanner.nextDouble();

        System.out.println("Enter room type. 1 for Single, 2 for Double:");
        RoomType roomType = setRoomType(scanner);

        Room room = new Room(roomNumber, roomPrice, roomType);
        AdminResource.addRoom(room);
        System.out.println("Room added");
        /*        String roomTypeInput = scanner.nextLine();
            switch (roomTypeInput){
                case"1":
                    RoomType roomType = RoomType.SINGLE;
                    break;
                case"2":
                    RoomType roomType = RoomType.DOUBLE;
                    break;
                    }
                    */
    }

    private static RoomType setRoomType(Scanner scanner){
        Scanner roomType = new Scanner(System.in);
        String inputRoom = roomType.nextLine();
        try{
            return RoomType.retrieveLabel(inputRoom);
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getLocalizedMessage());
            return setRoomType(scanner);
        }
    }






}
