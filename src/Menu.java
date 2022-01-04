import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Menu {
 private static final  int CREATE_USER =1;
    private static final  int LOG_IN =2;
    private static final  int END_PROGRAM =3;
    private static final  int ADVERTISE =1;
    private static final  int REMOVE =2;
    private static final  int SHOW_ALL_PROPERTIES =3;
    private static final  int SHOW_USER_PROPERTIES =4;
    private static final  int SEARCH =5;
    private static final  int MAIN_MENU =6;


    public static void startUp() {
        Scanner scanner = new Scanner(System.in);
        int firstMenuChoose = 0;
        RealEstate realEstate = new RealEstate();
        while (true) {
            System.out.println("what would you like to do? " + "\n"
                    + "press accordingly :" + "\n" +
                    "1- for creating an account" + "\n" +
                    "2- for login to existing account" + "\n" +
                    "3- for ending the program");
            firstMenuChoose = scanner.nextInt();

            switch (firstMenuChoose) {
                case CREATE_USER:
                    realEstate.createUser();
                    break;

                case LOG_IN:
                    User foundUser = realEstate.userLogin();
                    if (foundUser != null) {
                        userChooseOption(foundUser, realEstate);
                    }
                    break;
                case END_PROGRAM:
                    return;
                default:

            }
        }
    }

    public static void userChooseOption(User user, RealEstate realEstate) {
        Scanner scanner = new Scanner(System.in);

        int secondMenuChoose;

        do {
            System.out.println("what would you like to do? " + "\n"
                    + "press accordingly :" + "\n" +
                    "1- advertise new property" + "\n" +
                    "2- to remove property from advertisement" + "\n" +
                    "3- display all properties in the system" + "\n" +
                    "4- display all properties this user is advertising" + "\n" +
                    "5- search property by parameters" + "\n" +
                    "6- exit to main menu");
            secondMenuChoose = scanner.nextInt();
        } while (secondMenuChoose > 6 || secondMenuChoose < 1);
        switch (secondMenuChoose) {
            case ADVERTISE:
                realEstate.postNewProperty(user);
                break;
            case REMOVE:
                realEstate.removeProperty(user);
                break;
            case SHOW_ALL_PROPERTIES:
                realEstate.printAllProperties();
                System.out.println("---------------------");
                break;
            case SHOW_USER_PROPERTIES:
                realEstate.printAllProperties(user);
                System.out.println("---------------------");
                break;
            case SEARCH: realEstate.search();
                break;
            case MAIN_MENU:
                System.out.println("---------------------");
                return;
            default:
                break;

        }

    }


    public static void main(String[] args) {
        startUp();
    }
}

