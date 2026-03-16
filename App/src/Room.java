public abstract class Room {
    private String type;
    private int beds;
    private double size;
    private double price;

    public Room(String type, int beds, double size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() { return type; }
    public int getBeds() { return beds; }
    public double getSize() { return size; }
    public double getPrice() { return price; }

    public void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sqm");
        System.out.println("Price     : $" + price);
    }
}