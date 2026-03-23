import java.util.*;

public class BookMyStayApp {

    // ===== Custom Exception =====
    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String msg) {
            super(msg);
        }
    }

    // ===== Reservation Model =====
    static class Reservation {
        String id;
        String guest;
        String roomType;
        String roomId;
        boolean active;

        Reservation(String id, String guest, String roomType, String roomId) {
            this.id = id;
            this.guest = guest;
            this.roomType = roomType;
            this.roomId = roomId;
            this.active = true;
        }
    }

    // ===== Core Data =====
    static Map<String, Integer> inventory = new HashMap<>();
    static Map<String, Reservation> bookings = new HashMap<>();
    static Stack<String> rollbackStack = new Stack<>();

    // ===== Initialize Inventory =====
    public static void initInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    // ===== Validation =====
    public static void validate(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type");
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No availability for " + roomType);
        }
    }

    // ===== Booking =====
    public static void bookRoom(String id, String guest, String roomType) {

        try {
            validate(roomType);

            String roomId = generateRoomId(roomType);

            inventory.put(roomType, inventory.get(roomType) - 1);

            Reservation r = new Reservation(id, guest, roomType, roomId);
            bookings.put(id, r);

            System.out.println("Booking Confirmed: " + guest + " -> " + roomId);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    // ===== Cancellation =====
    public static void cancelBooking(String reservationId) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation not found");
            return;
        }

        Reservation r = bookings.get(reservationId);

        if (!r.active) {
            System.out.println("Cancellation Failed: Already cancelled");
            return;
        }

        // Rollback logic
        rollbackStack.push(r.roomId);

        inventory.put(r.roomType, inventory.get(r.roomType) + 1);

        r.active = false;

        System.out.println("Booking Cancelled: " + r.guest + " | Room Released: " + r.roomId);
    }

    // ===== Generate Room ID =====
    public static String generateRoomId(String type) {
        return type.substring(0, 3).toUpperCase() + "-" + (100 + new Random().nextInt(900));
    }

    // ===== Display Inventory =====
    public static void displayInventory() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " : " + inventory.get(key));
        }
    }

    // ===== Display Booking History =====
    public static void displayBookings() {
        System.out.println("\nBookings:");
        for (Reservation r : bookings.values()) {
            System.out.println(
                    r.id + " | " + r.guest + " | " + r.roomType +
                    " | " + r.roomId + " | Active: " + r.active
            );
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        initInventory();

        // Bookings
        bookRoom("RES1", "Alice", "Single Room");
        bookRoom("RES2", "Bob", "Double Room");

        // Cancellation
        cancelBooking("RES1");

        // Invalid cases
        cancelBooking("RES1"); // already cancelled
        cancelBooking("RES5"); // not exists

        displayInventory();
        displayBookings();

        System.out.println("\nRollback Stack:");
        System.out.println(rollbackStack);
    }
}