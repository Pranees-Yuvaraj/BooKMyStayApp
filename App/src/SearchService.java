import java.util.Map;

public class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("======================================");
        System.out.println("        AVAILABLE ROOMS SEARCH        ");
        System.out.println("======================================");

        Map<String, Integer> data = inventory.getInventorySnapshot();

        for (Room room : rooms) {
            int available = data.getOrDefault(room.getType(), 0);

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println("--------------------------------------");
            }
        }
    }
}