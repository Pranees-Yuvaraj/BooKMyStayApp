public class HotelBookingApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nChecking availability for Single Room...");
        int available = inventory.getAvailability("Single Room");
        System.out.println("Single Room Available: " + available);

        System.out.println("\nUpdating availability...");
        inventory.updateAvailability("Single Room", available - 1);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}