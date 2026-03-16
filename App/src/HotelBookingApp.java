public class HotelBookingApp {
    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("======================================");
        System.out.println("        HOTEL ROOM AVAILABILITY       ");
        System.out.println("======================================");

        single.displayDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println("--------------------------------------");

        doubleRoom.displayDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println("--------------------------------------");

        suite.displayDetails();
        System.out.println("Available : " + suiteAvailable);
        System.out.println("======================================");
    }
}