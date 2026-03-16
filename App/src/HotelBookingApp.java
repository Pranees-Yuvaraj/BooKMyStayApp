public class HotelBookingApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
            new SingleRoom(),
            new DoubleRoom(),
            new SuiteRoom()
        };

        SearchService search = new SearchService(inventory);

        search.searchAvailableRooms(rooms);
    }
}