import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {

    private final User USER;
    private final LocalDate EVENT_DATE;
    private final LocalTime EVENT_TIME;
    private final int PARTY_NUMBER;
    private final Establishment ESTABLISHMENT;
    private final int EVENT_ID;
    //Display time in the format of HH:mm
    private final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    //Display date in the format of dd/MM/yyyy
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    /**
     * Create a event record with the given user,
     * party number and establishment.
     *
     * @param user nominated user
     * @param partyNumber party size
     * @param establishment establishment of the event
     */
    Event(User user, int partyNumber, Establishment establishment){
        this.USER = user;
        this.EVENT_DATE = LocalDate.now(); ; //Use the current date as the event holding date
        this.EVENT_TIME = LocalTime.now(); //Use the current time as the event holding time
        this.PARTY_NUMBER = partyNumber;
        this.ESTABLISHMENT = establishment;
        this.EVENT_ID = hashCode(); //Use hash code to generate the event ID
    }


    /**
     * Return a detailed string representation of the
     * event detail.
     * The string representation is the event's ID,
     * user's name, date of birth email, contact number
     * and age, followed by the event's date and time, party
     * size, and the name and address of the establishment
     * printed in twelve seperated lines.
     */
    public String eventDetail(){
        return String.format("""
                        Event ID: %d | Recorded User
                        \tName %s
                        \t Date of Birth %s
                        \t Email %s
                        \t Contact Number %s
                        \t Age %s
                        Date %s
                        Time %s
                        Party Size %d
                        Establishment
                        \t   Name: %s
                         \t\t Address: %s %s
                        """, EVENT_ID, USER.getName(), USER.getDateOfBirth().format(DATE_FORMATTER),
                USER.getEmail(), USER.getPhoneNumber(), USER.getAge(),
                EVENT_DATE.format(DATE_FORMATTER), EVENT_TIME.format(TIME_FORMATTER), PARTY_NUMBER, ESTABLISHMENT.getName(),
                ESTABLISHMENT.getFirstLineAddress(), ESTABLISHMENT.getPostCode());
    }


    /**
     * Get the user's detail.
     *
     * @return the detail of the user
     */
    public User getUser() {
        return USER;
    }


    /**
     * Get the event's date.
     *
     * @return date of the event
     */
    public LocalDate getEventDate() {
        return EVENT_DATE;
    }


    /**
     * Get the event's time.
     *
     * @return time of the event
     */
    public LocalTime getEventTime() {
        return EVENT_TIME;
    }


    /**
     * Get the establishment detail.
     *
     * @return detail of the establishment
     */
    public Establishment getEstablishment() {
        return ESTABLISHMENT;
    }
}
