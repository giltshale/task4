public class Property {
    private int kindOfApartment;
    private boolean isRental;
    private int amountOfRooms;
    private int floor;
    private int price;
    private User advertisers;
    private Address propertyAddress;
    private int numberOfApartment;


    public Property(int kindOfApartment, boolean isRental,
                    int amountOfRooms, int floor, int price, int numberOfApartment,Address propertyAddress) {
        this.kindOfApartment = kindOfApartment;
        this.isRental = isRental;
        this.amountOfRooms = amountOfRooms;
        this.floor = floor;
        this.price = price;
        this.numberOfApartment = numberOfApartment;
        this.propertyAddress=propertyAddress;
    }

    @Override
    public String toString() {
        String rental = "";
        String broker = "";
        if (!advertisers.isRealEstateAgent()) {
            broker = "(real estate broker)";
        } else {
            broker = " (private user)";
        }
        if (isRental) {
            rental = "for rent: ";
        } else {
            rental = "for sale: ";
        }

        return kindOfApartment + "- " +
                rental +
                +amountOfRooms + " rooms, " +
                "floor " + floor + ".\n" +
                "Price: " + price + "$" + "\n" +
                "Contact info: " + advertisers +" " + broker;
    }

    public Address getPropertyAddress() {
        return propertyAddress;
    }

    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public int getKindOfApartment() {

        return kindOfApartment;
    }

    public boolean isRental() {
        return isRental;
    }

    public int getNumberOfApartment() {
        return numberOfApartment;
    }

    public int getFloor() {
        return floor;
    }

    public void setPropertyAddress(Address propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public void setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setKindOfApartment(int kindOfApartment) {
        this.kindOfApartment = kindOfApartment;
    }
    public void setRental(boolean rental) {
        isRental = rental;
    }

    public void setNumberOfApartment(int numberOfApartment) {
        this.numberOfApartment = numberOfApartment;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setAdvertisers(User advertisers) {
        this.advertisers = advertisers;
    }

    public User getAdvertisers() {
        return advertisers;
    }
}
