import java.util.*;

public class AddOnServiceManager {

    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.getName() + " added to Reservation " + reservationId);
    }

    public double calculateTotalCost(String reservationId) {

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (AddOnService s : services) {
            total += s.getPrice();
        }

        return total;
    }

    public void displayServices(String reservationId) {

        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("======================================");
        System.out.println("      ADD-ON SERVICES SELECTED        ");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("======================================");

        if (services == null) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println(s.getName() + " : $" + s.getPrice());
        }

        System.out.println("--------------------------------------");
        System.out.println("Total Add-On Cost: $" + calculateTotalCost(reservationId));
        System.out.println("======================================");
    }
}