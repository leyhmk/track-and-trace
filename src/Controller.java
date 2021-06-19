import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Controller {

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public final ArrayList<Event> EVENTS = new ArrayList<>();
    public final ArrayList<Establishment> ESTABLISHMENT = new ArrayList<>();


    /**
     * Add the establishment to the list of establishments,
     * if the establishment already exist then return false
     *
     * @param e establishment's record
     */
    public boolean addEstablishment(Establishment e) {
        for (Establishment est: ESTABLISHMENT)
        {
            //Check if the establishment already exist by comparing
            //the name and the first line address of the establishment
            if ((est.getName()).equalsIgnoreCase(e.getName()) &&
                (est.getFirstLineAddress()).equalsIgnoreCase(e.getFirstLineAddress())) {
                return false;
            }
        }
        return ESTABLISHMENT.add(e);
    }


    /**
     * Add the event record to the event lists,
     * if the event already exist then return false
     *
     * @param eve event's record
     */
    public boolean addEvent(Event eve) {
        for (Event singleEvent: EVENTS) {
            //Check if the event already exist by comparing
            //the user's name, establishment's name, and if the event time between
            //two similar event are less than 2 hours
            if (((eve.getUser().getName()).equalsIgnoreCase(singleEvent.getUser().getName())) &&
                    (eve.getEstablishment().getName()).equalsIgnoreCase(singleEvent.getEstablishment().getName())&&
                    (ChronoUnit.HOURS.between(eve.getEventTime(),LocalTime.now())) < 2){
                return false;
            }
        }
        return EVENTS.add(eve);
    }


    /**
     * Controller Constructors
     */
    Controller() {
    }


    /**
     * Filter the events hold in a given establishment.
     *
     * @param nameOfEstablishment the establishment's name
     * @return list of events hold in the given establishment
     */
    public ArrayList<Event> filterEventByEstablishment(String nameOfEstablishment) {
        ArrayList<Event> filteredEvent = new ArrayList<>();
        for (Event eve : EVENTS) {
            if ((eve.getEstablishment().getName()).equalsIgnoreCase(nameOfEstablishment)) {
                filteredEvent.add(eve);
            }
        }
        return filteredEvent;
    }


    /**
     * Filter the events hold on a given date
     *
     * @param date the event's date
     * @return list of events hold on the given date
     */
    public ArrayList<Event> filterEventByDate(String date) {
        ArrayList<Event> filteredEvent = new ArrayList<>();
        for (Event eve : EVENTS) {
            if ((eve.getEventDate().format(DATE_FORMATTER)).equals(date)) {
                filteredEvent.add(eve);
            }
        }
        return filteredEvent;

    }


    /**
     * Filter the events that are being recorded under
     * the given user's name and email address.
     *
     * @param name the user's name
     * @param email the user's address
     * @return list of events hold under the given user's name
     *         and email address
     */
    public ArrayList<Event> filterEventByUser(String name, String email) {
        ArrayList<Event> filteredEvent = new ArrayList<>();
        for (Event eve : EVENTS) {
            if (((eve.getUser().getName()).equalsIgnoreCase(name)) &&
                    (eve.getUser().getEmail().equalsIgnoreCase(email))) {
                filteredEvent.add(eve);
            }
        }
        return filteredEvent;
    }


    /**
     * Get the list of establishment.
     *
     * @return establishments list
     */
    public ArrayList<Establishment> getEstablishments() {
        return ESTABLISHMENT;
    }


    /**
     * Get the list of events.
     *
     * @return events list
     */
    public ArrayList<Event> getEvents() {
        return EVENTS;
    }
}



