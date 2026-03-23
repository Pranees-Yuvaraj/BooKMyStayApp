import java.util.*;

public class BookMyStayApp {

    // Custom Exception
    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    // Reservation Model
    static class Reservation {
        String id;
        String guest;
        String roomType;

        Reservation(String id, String guest, String roomType) {
            this.id = id;
            this.guest = guest;
            this.roomType = roomType;
        }
    }

    // Inventory (Centralized)
    private static Map<String, Integer> inventory = new HashMap<>();

    // Booking History
    private static List<Reservation> history = new ArrayList<>();

    // Initialize inventory
    public static void initInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    // Validation Method
    public static void validate(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }

    // Booking Method
    public static void bookRoom(String id, String guest, String roomType) {

        try {
            validate(roomType);

            // Safe update
            int available = inventory.get(roomType);
            inventory.put(roomType, available - 1);

            // Store history
            history.add(new Reservation(id, guest, roomType));

            System.out.println("Booking Confirmed: " + guest + " -> " + roomType);

        } catch (InvalidBookingException e) {

            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    // Display Inventory
    public static void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }

    // Display Booking History
    public static void displayHistory() {
        System.out.println("\nBooking History:");
        for (Reservation r : history) {
            System.out.println(r.id + " | " + r.guest + " | " + r.roomType);
        }
    }

    public static void main(String[] args) {

        initInventory();

        // Valid bookings
        bookRoom("RES1", "Alice", "Single Room");
        bookRoom("RES2", "Bob", "Double Room");

        // Invalid cases
        bookRoom("RES3", "Charlie", "Suite Room");
        bookRoom("RES4", "David", "Suite Room"); // No availability
        bookRoom("RES5", "Eve", "King Room");    // Invalid type

        displayInventory();
        displayHistory();
    }
}