public class HotelBookingApp {

    public static void main(String[] args) {

        Reservation reservation = new Reservation("RES-101", "Alice", "Single Room");

        AddOnService breakfast = new AddOnService("Breakfast", 20);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 40);
        AddOnService spa = new AddOnService("Spa Access", 60);

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservation.getReservationId(), breakfast);
        manager.addService(reservation.getReservationId(), airportPickup);
        manager.addService(reservation.getReservationId(), spa);

        manager.displayServices(reservation.getReservationId());
    }
}