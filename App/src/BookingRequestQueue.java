import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added to queue for " + reservation.getGuestName());
    }

    public void displayQueue() {

        System.out.println("======================================");
        System.out.println("        BOOKING REQUEST QUEUE         ");
        System.out.println("======================================");

        for (Reservation r : queue) {
            r.displayRequest();
        }

        System.out.println("======================================");
    }
}