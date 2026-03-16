import java.util.*;

public class BookMyStayApp {

    static class Reservation {
        String reservationId;
        String guestName;
        String roomType;

        Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // Booking history storage (in insertion order)
    private static List<Reservation> bookingHistory = new ArrayList<>();


    // Add confirmed reservation to history
    public static void addReservation(String id, String guest, String room) {
        Reservation r = new Reservation(id, guest, room);
        bookingHistory.add(r);
    }

    // Generate booking report
    public static void generateReport() {

        System.out.println("==================================");
        System.out.println("        BOOKING HISTORY REPORT     ");
        System.out.println("==================================");

        for (Reservation r : bookingHistory) {
            System.out.println(
                    "Reservation ID: " + r.reservationId +
                    " | Guest: " + r.guestName +
                    " | Room Type: " + r.roomType
            );
        }

        System.out.println("----------------------------------");
        System.out.println("Total Reservations: " + bookingHistory.size());
        System.out.println("==================================");
    }

    public static void main(String[] args) {

        // Simulating confirmed bookings
        addReservation("RES101", "Alice", "Single Room");
        addReservation("RES102", "Bob", "Double Room");
        addReservation("RES103", "Charlie", "Suite Room");

        // Admin views booking report
        generateReport();
    }
}