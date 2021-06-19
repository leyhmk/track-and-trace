import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class User {

    private final String NAME;
    private final LocalDate DATE_OF_BIRTH;
    private final String EMAIL;
    private final String PHONE_NUMBER;
    private int age;
    //Display date in the format of dd/MM/yyyy
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    /**
     * Create a user record with the given name,
     * date of birth, email and phone number.
     * The age of the user is being calculated by the
     * ageCalculator()
     *
     * @param name user's name
     * @param dateOfBirth user's date of birth
     * @param email user's email address
     * @param phoneNumber user's phone number
     */
    User (String name, LocalDate dateOfBirth, String email, String phoneNumber){
        this.NAME = name;
        this.DATE_OF_BIRTH = dateOfBirth;
        this.EMAIL = email;
        this.PHONE_NUMBER = phoneNumber;
        this.age = ageCalculator();
    }


    /**
     * Calculate the age of the user using the
     * Period.between(date of birth, current year) method
     */
    private int ageCalculator(){
        return Period.between(DATE_OF_BIRTH, LocalDate.now()).getYears();
    }


    /**
     * Return a string representation of the
     * user's record.
     * The string representation is the user's full name,
     * date of birth, email, contact number and age printed
     * in five seperated lines.
     */
    public String detailPrint(){
        return String.format("""
                        Name %s
                         Date of Birth %s
                         Email %s
                         Contact Number %s
                         Age %s""",
                NAME, DATE_OF_BIRTH.format(DATE_FORMATTER),
                EMAIL, PHONE_NUMBER, age);
    }


    /**
     * Get the user's full name.
     *
     * @return full name of the user
     */
    public String getName() {
        return NAME;
    }


    /**
     * Get the user's date of birth.
     *
     * @return date of birth of the user
     */
    public LocalDate getDateOfBirth() {
        return DATE_OF_BIRTH;
    }


    /**
     * Get the user's email address.
     *
     * @return email address of the user
     */
    public String getEmail() {
        return EMAIL;
    }


    /**
     * Get the user's phone number.
     *
     * @return phone number of the user
     */
    public String getPhoneNumber() {
        return PHONE_NUMBER;
    }


    /**
     * Get the user's age.
     *
     * @return age of the user
     */
    public int getAge() {
        return age;
    }
}
