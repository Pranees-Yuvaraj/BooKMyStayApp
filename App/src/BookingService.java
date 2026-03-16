import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BookingService {

    private RoomInventory inventory;
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> roomTypeAllocations;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRoomIds = new HashSet<>();
        this.roomTypeAllocations = new HashMap<>();
    }

    public void processRequests(BookingRequestQueue queue) {

        System.out.println("======================================");
        System.out.println("       PROCESSING RESERVATIONS        ");
        System.out.println("======================================");

        while (queue.hasRequests()) {

            Reservation reservation = queue.dequeueRequest();
            String roomType = reservation.getRoomType();

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                String roomId = generateRoomId(roomType);

                allocatedRoomIds.add(roomId);

                roomTypeAllocations
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Reservation Confirmed for "
                        + reservation.getGuestName()
                        + " | Room ID: " + roomId);

            } else {

                System.out.println("Reservation Failed for "
                        + reservation.getGuestName()
                        + " | No rooms available.");

            }
        }

        System.out.println("======================================");
    }

    private String generateRoomId(String roomType) {

        String id;

        do {

            id = roomType.replace(" ", "")
                    .substring(0, 3)
                    .toUpperCase()
                    + "-"
                    + (100 + new Random().nextInt(900));

        } while (allocatedRoomIds.contains(id));

        return id;
    }
}