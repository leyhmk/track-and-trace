import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {

    private final Controller Control;
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    /**
     * Create an instance of the Controller, which is
     * private and immutable.
     */
    IO() {
        this.Control = new Controller();
    }


    /**
     * Command line program that takes in numerical
     * user input to select options in a menu.
     * The menu will not terminate unless the exit
     * program option is chosen.
     */
    public void run() {
        boolean off = false;
        Scanner s = new Scanner(System.in);

        while (!off) {
            menu();
            System.out.println("Please select an option from 1 to 6: ");
            int option = s.nextInt();

            switch (option) {

                //Record an Event
                case 1:
                    System.out.println("The programme is going to record an Event.");
                    if ((!Control.addEvent(recordEvent()))) {
                        System.out.println("This event has already been recorded.");
                    }
                    break;

                //Add an establishment
                case 2:
                    System.out.println("The programme is going to record an Establishment.");
                    if ((!Control.addEstablishment(recordEstablishment()))) {
                        System.out.println("This Establishment has already been recorded.");
                    }
                    break;

                //Filtering event submenu
                case 3:
                    submenuRun();
                    break;

                // Print all recorded events
                case 4:
                    // Check if there are any recorded event
                    if (!(Control.getEvents()).isEmpty()) {
                        System.out.println("Event records: ");
                        for (Event eve : Control.getEvents()) {
                            System.out.println(eve.eventDetail());
                        }
                    } else {
                        System.out.println("No Event record available.\n");
                    }

                    break;

                //Print all establishment record
                case 5:
                    //Check if there are any recorded establishment
                    if (!(Control.getEstablishments()).isEmpty()) {
                        System.out.println("Establishment records: ");
                        for (Establishment e : Control.getEstablishments()) {
                            System.out.println(e.addressDetail());
                        }
                    } else {
                        System.out.println("No Establishment record available.\n");
                    }
                    break;

                //Exit the program
                case 6:
                    System.out.println("Thank you for using the Track and Trace Programme.");
                    off = true;
                    break;

                default:
                    System.out.println("This is not a valid option, please select an option from 1 to 6:");

            }
        }
    }


    /**
     * Record an event.
     *
     * @return an event record
     */
    private Event recordEvent() {
        User user = recordUser();
        Establishment establishment = recordEstablishment();
        Control.addEstablishment(establishment);
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the party number: ");
        int partyNumber = s.nextInt();
        return new Event(user, partyNumber, establishment);
    }


    /**
     * Record an establishment.
     *
     * @return an establishment record
     */
    private Establishment recordEstablishment() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the name of the Establishment: ");
        String name = s.nextLine();
        System.out.println("Please enter the first line address of the Establishment: ");
        String firstLineAddress = s.nextLine();
        System.out.println("Please enter the post code of the Establishment: ");
        String postCode = s.nextLine();
        System.out.println("Please enter the maximum occupancy of the Establishment: ");
        int maxOccupancy = s.nextInt();
        return new Establishment(name, firstLineAddress, postCode, maxOccupancy);
    }


    /**
     * Record an user.
     *
     * @return an user record
     */
    private User recordUser() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the full name of the User:");
        String name = s.nextLine();
        System.out.println("Please enter the date of birth (dd/mm/yyyy) of the User: ");
        LocalDate dateOfBirth = LocalDate.parse(s.nextLine(), DATE_FORMATTER);

        //Check if the date of birth entered is after the current date
        while (dateOfBirth.isAfter(LocalDate.now())) {
            System.out.println("Please enter a valid date of birth:");
            dateOfBirth = LocalDate.parse(s.nextLine(), DATE_FORMATTER);
        }

        System.out.println("Please enter the email address of the User: ");
        String email = s.nextLine();
        //Check if the email address entered contain the "@" notation
        while (!email.contains("@")) {
            System.out.println("This is not a valid email address. Please enter a valid email address: ");
            email = s.nextLine();
        }

        System.out.println("Please enter the phone number of the User without the international calling code: ");
        String phoneNumber = s.nextLine();
        //Check if the phone number entered is 11 characters long and
        //does not include international calling code
        while ((phoneNumber.length() != 11) || ((phoneNumber.charAt(0)) != '0')) {
            System.out.println("Please enter a valid phone number without any international calling code: ");
            phoneNumber = s.nextLine();
        }
        return new User(name, dateOfBirth, email, phoneNumber);
    }


    /**
     * The printed menu of the IO
     */
    private void menu() {
        System.out.println("Welcome to the Track and Trace Programme!");
        System.out.println("""
                1: Record an Event\s
                2: Add Establishment\s
                3: Filters with sub-menu
                4: Print Event
                5: Print Establishment
                6: Exit the Program""");
    }


    /**
     * The printed submenu for filtering events by the
     * given criteria.
     */
    private void submenu() {
        System.out.println("This is the sub-menu for the filters option.");
        System.out.println("""
                1: Filter records by Establishment\s
                2: Filter records by Date\s
                3: Filters records by Name of the user
                4: Go back to the main menu
                """);

    }

    /**
     * Driver of running the filtering events'
     * submenu.
     */
    private void submenuRun() {
        Scanner s = new Scanner(System.in);

        boolean off = false;
        while (!off) {
            submenu();
            System.out.println("Please select an option from 1 to 4: ");
            int option = s.nextInt();
            Scanner input = new Scanner(System.in);

            switch (option) {

                //Filters event records by Establishment
                case 1:
                    System.out.println("Filtering Event by Establishment.");
                    System.out.println("Please enter the Establishment name: ");
                    String nameOfEstablishment = input.nextLine();
                    ArrayList<Event> resultByEstablishment = Control.filterEventByEstablishment(nameOfEstablishment);
                    //Check if there are any recorded event under the given establishment
                    if (!resultByEstablishment.isEmpty()) {
                        for (Event eve : resultByEstablishment) {
                            System.out.println(eve.eventDetail());
                        }
                    } else {
                        System.out.println("No record is being held under this Establishment.\n");
                    }

                    break;

                //Filters event records by date
                case 2:
                    System.out.println("Filtering Event by Date.");
                    System.out.println("Please enter the Event date: ");
                    String eventDate = input.nextLine();
                    ArrayList<Event> resultByDate = Control.filterEventByDate(eventDate);
                    //Check if there are any recorded event under the given date
                    if (!resultByDate.isEmpty()) {
                        for (Event eve : resultByDate) {
                            System.out.println(eve.eventDetail());
                        }
                    } else {
                        System.out.println("No record can be found for the date provided.\n");
                    }
                    break;

                //Filter event records by user name and email address
                case 3:
                    System.out.println("Filtering Event by User.");
                    System.out.println("Please enter the User name: ");
                    String name = input.nextLine();
                    System.out.println("Please enter the User email address: ");
                    String email = input.nextLine();
                    //Check if the entered email address contain the "@" notation
                    while (!email.contains("@")) {
                        System.out.println("This is not a valid email address. Please enter a valid email address: ");
                        email = s.nextLine();
                    }
                    ArrayList<Event> resultByUser = Control.filterEventByUser(name, email);
                    //Check if there are any recorded event under the given user name and email address
                    if (!resultByUser.isEmpty()) {
                        for (Event eve : resultByUser) {
                            System.out.println(eve.eventDetail());
                        }
                    } else {
                        System.out.println("No record is being held under this User.\n");
                    }
                    break;

                //Return to the main menu
                case 4:
                    System.out.println("Returning to main menu.");
                    off = true;
                    break;

                default:
                    System.out.println("Please enter a valid option from 1 to 4: ");
            }
        }
    }

    /**
     * Main method for running the Track
     * and Trace program
     */
    public static void main(String[] args) {
        new IO().run();
    }
}