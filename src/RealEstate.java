import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Character.isDigit;
public class RealEstate {
    final int LIMIT_OF_MEDIATOR = 10;
    final int LIMIT_OF_UN_MEDIATOR = 3;
    final int MAX_LENGTH_NUMBER = 10;
    final int REGULAR_BUILDING_APARTMENT = 1;
    final int PENTHOUSE = 2;
    final int PRIVATE_GROUND_HOUSE = 3;
    final int NO_VALUE = -999;
    final int TRUE = 1;
    final int FALSE = 0;

    private User[] users;
    private Property[] properties;
    private Address[] addresses;

    public RealEstate() {
        properties = new Property[0];
        users = new User[0];
        addresses = new Address[10];
        addresses[0] = new Address("Tel-aviv", "Sokolov");
        addresses[1] = new Address("Ashkelon", "Nathan-hanavie");
        addresses[2] = new Address("Bat-yam", "Ben-yehuda");
        addresses[3] = new Address("Afula", "Hamacabim");
        addresses[4] = new Address("Jerusalem", "Eliezer-ve-hagezer");
        addresses[5] = new Address("Kiryat-gat", "Ardina");
        addresses[6] = new Address("Jerusalem", "Menashe-hevron");
        addresses[7] = new Address("Tel-aviv", "Amos-oz");
        addresses[8] = new Address("Tel-aviv", "David-hai");
        addresses[9] = new Address("Tel-aviv", "Derech hashalom");
    }

    public String[] getCitiesName() {
        String[] cityAddresses = new String[10];
        int count = 0;
        for (Address address : addresses) {
            for (int j = 0; j < addresses.length; j++) {
                if (address.getCityName().equals(cityAddresses[j])) {
                    break;
                }
                if (j == 9) {
                    cityAddresses[count] = address.getCityName();
                    count++;
                }
            }
        }

        return cityAddresses;
    }

    public void setProperties(Property[] propertiesArray) {
        Property[] properties = new Property[propertiesArray.length + 1];

        this.properties = properties;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public void createUser() {
        int oldSize = users.length;
        User[] temp = new User[oldSize + 1];
        for (int i = 0; i < oldSize; i++) {
            temp[i] = new User(users[i]);
        }

        String newUserName = "";
        Scanner scanner = new Scanner(System.in);
        boolean exists = false;

        do {
            System.out.println("Enter wanted user name ");
            newUserName = scanner.nextLine();
            for (User user : users) {
                exists = false;
                if (user.getUserName().equals(newUserName)) {
                    System.out.println("This name is occupied, try again please ");
                    exists = true;
                }

            }
        } while (exists);

        String password;
        boolean strong;
        do {
            System.out.println("Enter password ");
            password = scanner.nextLine();
            strong = isStrongPassword(password);
            if (!strong) {
                System.out.println("password must contain one of : '$' or '%' or '_' ");
            }

        } while (!strong);


        String phone;
        boolean validPhone;
        do {
            System.out.println("Enter wanted phone number");
            phone = scanner.nextLine();
            validPhone = isValidPhone(phone);
            if (!validPhone) {
                System.out.println("phone not valid");
            }

        } while (!validPhone);
        int mediator;
        boolean isMediator = false;
        do {
            System.out.println("Are you mediator? (1 = yes , 0 = no)");
            mediator = scanner.nextInt();

        } while (mediator != TRUE && mediator != FALSE);
        if (mediator == TRUE) {
            isMediator = true;
        }

        User u = new User(newUserName, password, phone, isMediator);
        temp[temp.length - 1] = u;
        users = temp;
    }

    private boolean isValidPhone(String phone) {
        if (phone.length() != MAX_LENGTH_NUMBER) {
            return false;
        }
        if (phone.charAt(0) != '0' || phone.charAt(1) != '5') {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isStrongPassword(String passwordToCheck) {
        boolean strong = false;

        boolean hasLetter = false;
        boolean hasDigit = false;

        for (int i = 0; i < passwordToCheck.length(); i++) {
            char currentChar = passwordToCheck.charAt(i);
            if (isDigit(currentChar)) {
                hasDigit = true;
            } else if (currentChar == '$' || currentChar == '%' || currentChar == '_') {
                hasLetter = true;
            }
            if (hasDigit && hasLetter) {
                break;
            }
        }

        if (hasLetter && hasDigit) {
            strong = true;
        }
        return strong;
    }

    public User userLogin() {
        String userName = "";
        String password = "";
        Scanner scanner = new Scanner(System.in);
        boolean userFound = false;
        User user = null;
        do {
            System.out.println("please enter user name for login: ");
            userName = scanner.nextLine();
            System.out.println("please enter your password");
            password = scanner.nextLine();
            for (int i = 0; i < users.length; i++) {
                if (users[i].getUserName().equals(userName) &&
                        users[i].getPassword().equals(password)) {
                    user = users[i];
                    userFound = true;
                    break;
                }

            }
        } while (!userFound);
        return user;

    }

    private Property apartmentDetails(int index, User user) {
        Scanner scanner = new Scanner(System.in);
        int apartmentChoice;
        int floor;
        int numberOfrooms = 0;
        int numOfApartment = 0;
        int rental;
        int price = 0;
        Property advertisersProperty;

        do {
            System.out.println("what kind  is the Apartment ? " + "\n" +
                    "press accordingly:" + "\n" +
                    "1- for regular apartment" + "\n" +
                    "2- for a penthouse in a building " + "\n" +
                    "3- for private ground house");
            apartmentChoice = scanner.nextInt();

        } while (apartmentChoice != REGULAR_BUILDING_APARTMENT &&
                apartmentChoice != PENTHOUSE && apartmentChoice != PRIVATE_GROUND_HOUSE);

        boolean isRental = false;
        String whichApartment;
        if (apartmentChoice == PRIVATE_GROUND_HOUSE) {
            whichApartment = "private ground house ";
            floor = 0;
        } else if (apartmentChoice == REGULAR_BUILDING_APARTMENT) {
            whichApartment = "Regular building apartment ";
            System.out.println(" what floor is the apartment ? : ");
            floor = scanner.nextInt();
        } else {
            whichApartment = "Penthouse ";
            System.out.println(" what floor is the apartment ? : ");
            floor = scanner.nextInt();
        }

        while (numberOfrooms <= 0) {

            System.out.println("how many rooms ? :");
            numberOfrooms = scanner.nextInt();
        }
        while (numOfApartment <= 0) {
            System.out.println("what is the number of apartment ? :");
            numOfApartment = scanner.nextInt();
        }

        System.out.println("the property is for rent ? ( 1 = yes , other number = no ) ");
        rental = scanner.nextInt();
        if (rental == TRUE) {
            isRental = true;
        }
        while (price <= 0) {
            System.out.println("what is the price for the property ? : ");
            Scanner scanner1 = new Scanner(System.in);
            price = scanner1.nextInt();
        }


        advertisersProperty = new Property(apartmentChoice, isRental, numberOfrooms, floor, price, numOfApartment, addresses[index]);

        return advertisersProperty;
    }

    public boolean postNewProperty(User user) {
        String cityChoose;
        String streetName;
        boolean correctCityFlag = true;
        boolean correctStreetFlag = true;
        Scanner scanner = new Scanner(System.in);

        int numOfProperties = numberOfUserProperties(user);

        if (((!user.isRealEstateAgent()) && (numOfProperties >= LIMIT_OF_UN_MEDIATOR)) || (user.isRealEstateAgent()
                && (numOfProperties >= LIMIT_OF_MEDIATOR))) {
            System.out.println("you reached limit of  adds to advertise");
            return false;
        }
        int size = getCitiesName().length;
        int count = 0;
        String[] cities = getCitiesName();

        System.out.println("city names are:");
        for (int i = 0; i < size; i++) {
            if (cities[i] != null) {
                System.out.println(cities[i]);
                count++;

            }

        }
        //  do {
        System.out.println("------" + "\n" +
                "please choose one of the the city names that appeared in list: ");
        cityChoose = scanner.nextLine();

        //   }while (!cities[citiesIndex].equals(cityChoose));

        for (int i = 0; i < count; i++) {
            if (cities[i].equals(cityChoose)) {
                correctCityFlag = false;
            }
            if (correctCityFlag && i == count - 1) {
                System.out.println("the city you typed does not  exist ! ! !");
                return !correctCityFlag;

            }
        }
        System.out.println("please choose one of the the street names that appeared in list:");
        for (Address address : addresses) {
            if (cityChoose.equals(address.getCityName())) {
                System.out.println(address.getStreetName());
                correctStreetFlag = false;
                continue;
            }
        }
        boolean match = false;
        streetName = scanner.nextLine();
        for (Address value : addresses) {
            if (streetName.equals(value.getStreetName())) {
                match = true;
                break;
            }
        }
        if (!match) {
            System.out.println("street name does not exists ! ! !");
            return correctStreetFlag;
        }
        int addressFromArray = 0;
        for (int j = 0; j < addresses.length; j++) {
            if (this.addresses[j].getCityName().equals(cityChoose) && addresses[j].getStreetName().equals(streetName)) {
                addressFromArray = j;
            }
        }

        Property[] temp = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            temp[i] = this.properties[i];
        }

        Property createNewProperty = apartmentDetails(addressFromArray, user);//
        temp[properties.length] = createNewProperty;
        properties = temp;
        createNewProperty.setAdvertisers(user);//
        createNewProperty.getAdvertisers().setUserAddAmount(createNewProperty.getAdvertisers().getUserAddAmount() + 1);

        return true;
    }

    private int numberOfUserProperties(User user) {
        int userProperties = 0;
        for (Property property : this.properties) {
            if (property.getAdvertisers().getUserName().equals(user.getUserName())
                    && property.getAdvertisers().getPassword().equals(user.getPassword())) {
                userProperties++;
            }
        }
        return userProperties;
    }

    public void printAllProperties() {
        if (properties != null) {
            for (Property property : properties) {
                System.out.println(property);
            }
        }
    }

    void removeProperty(User user) {
        Property[] userProperties = new Property[properties.length];
        Property[] fixedPropertyArray = new Property[userProperties.length - 1];
        if (user.getUserAddAmount() == 0) {
            System.out.println("You don't have any posts to show");
        } else {

            for (int i = 0, j = 0; i < properties.length; i++) {
                if (this.properties[i].getAdvertisers().equals(user)) {
                    userProperties[j] = properties[i];
                    j++;
                }

            }
            int num = 0;
            int listNumber = 1;
            Scanner scanner = new Scanner(System.in);

            for (Property userProperty : userProperties) {
                System.out.print("" + listNumber + ": " + userProperty.toString() + "\n");
                listNumber++;

            }

            while (num <= 0 || num > listNumber) {
                System.out.println("Please select the number of property to remove : ");
                num = scanner.nextInt();
            }

            userProperties[num - 1] = userProperties[userProperties.length - 1];
            user.setUserAddAmount(user.getUserAddAmount() - 1);
            for (int i = 0; i < userProperties.length - 1; i++) {
                fixedPropertyArray[i] = userProperties[i];
            }
            this.properties = fixedPropertyArray;
            System.out.println("Delete completed ! ");
        }

    }

    public void printAllProperties(User user) {
        if (properties != null) {
            for (Property property : properties) {
                if (property.getAdvertisers().getUserName().equals(user.getUserName()) &&
                        property.getAdvertisers().getPhoneNumber().equals(user.getPhoneNumber()))
                    System.out.println(property + "\n");
            }
        }
    }

    public Property[] search() {
        Scanner scanner = new Scanner(System.in);
        Property[] addedProperties = new Property[0];
        int rent;
        int whatKindOfApartment = 0;
        int amountOfRooms;
        int maximumToPay;
        int minimumToPay;
        boolean rentConvert;
        boolean isKindOfApartmentCorrect = true;
        boolean isAmountOfRoosCorrect = true;
        boolean isMaxPriceCorrect = true;
        boolean isMinPriceCorrect = true;

        do {
            System.out.println("looking property for rent  or sale ? please type your answer: " +
                    "" + "\n" + "( 1 = for rent, 0 = for sale ");
            rent = scanner.nextInt();
            rentConvert = rent != NO_VALUE;
        } while (rent != FALSE && rent != TRUE && rent != NO_VALUE);

        do {
            System.out.println("1- for regular apartment" + "\n" + "2- for penthouse" + "\n" + "3- for private house");
            whatKindOfApartment = scanner.nextInt();
            isKindOfApartmentCorrect = whatKindOfApartment != NO_VALUE;

        } while (whatKindOfApartment != REGULAR_BUILDING_APARTMENT && whatKindOfApartment !=
                PENTHOUSE && whatKindOfApartment != PRIVATE_GROUND_HOUSE && whatKindOfApartment != NO_VALUE);
        do {
            System.out.println("How many rooms do you want?");
            amountOfRooms = scanner.nextInt();
            isAmountOfRoosCorrect = amountOfRooms != NO_VALUE;

        } while (amountOfRooms < 0 && amountOfRooms != NO_VALUE);
        do {
            System.out.println("what is the maximum price you want to spend ? ");
            maximumToPay = scanner.nextInt();
            isMaxPriceCorrect = maximumToPay != NO_VALUE;
        } while ((maximumToPay < 0 && maximumToPay != NO_VALUE) && maximumToPay != NO_VALUE);
        do {
            System.out.println("Notice: minimum price can't be mor the maximum price");
            System.out.println("what is the minimum price you want to spend ? ");
            minimumToPay = scanner.nextInt();
            isMinPriceCorrect = minimumToPay != NO_VALUE;

        } while ((minimumToPay < 0 && minimumToPay != NO_VALUE) && minimumToPay > maximumToPay && minimumToPay != NO_VALUE);

        if (this.properties.length > 0) {
            for (int i = 0; i < properties.length; i++) {
                if (properties[i] == null) {
                    System.out.println("null found " + i);
                    continue;
                }
                boolean checker = false;
                if (rentConvert) {
                    if (properties[i].isRental() && rent == TRUE) {
                        checker = true;
                    } else if (!properties[i].isRental() && rent == FALSE) {
                        checker = true;
                    }
                }
                if (isKindOfApartmentCorrect) {
                    if (properties[i].getKindOfApartment() == whatKindOfApartment)
                        checker = true;
                }
                if (isAmountOfRoosCorrect) {
                    if (properties[i].getAmountOfRooms() == amountOfRooms)
                        checker = true;
                }
                if (isMaxPriceCorrect) {
                    if (properties[i].getPrice() > maximumToPay)
                        checker = true;
                }
                if (isMinPriceCorrect) {
                    if (properties[i].getPrice() < minimumToPay)
                        checker = true;
                }
                if (checker) {

                    addedProperties = addProperty(properties[i], addedProperties);
                }

            }
        }

        return addedProperties;

    }
    private Property[] addProperty(Property property, Property[] addedProperties) {
        Property[] propertiesToFind;
        if (addedProperties != null) {
            propertiesToFind = new Property[addedProperties.length + 1];
            for (int i = 0; i < addedProperties.length; i++) {
                propertiesToFind[i] = addedProperties[i];
            }
        } else {
            propertiesToFind = new Property[1];
        }
        propertiesToFind[propertiesToFind.length - 1] = property;

        return propertiesToFind;
    }

}
