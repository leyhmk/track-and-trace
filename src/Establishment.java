public class Establishment {

    private final String NAME;
    private final String FIRST_LINE_ADDRESS;
    private final String POST_CODE;
    private final int MAX_OCCUPANCY;

    /**
     * Create an establishment record with the given
     * establishment's name, first line address,
     * post code and the max occupancy.
     *
     * @param name establishment's name
     * @param firstLineAddress establishment's first line address
     * @param postCode establishment's post code
     * @param maxOccupancy establishment's maximum occupancy
     */
    Establishment(String name, String firstLineAddress, String postCode, int maxOccupancy){
        this.NAME = name;
        this.FIRST_LINE_ADDRESS = firstLineAddress;
        this.POST_CODE = postCode;
        this.MAX_OCCUPANCY = maxOccupancy;
    }


    /**
     * Return a string representation of the
     * establishment's record.
     * The string representation is the name and
     * address (first line address, post code) of
     * the establishment printed in two
     * seperated lines.
     */
    public String addressDetail(){
        return String.format("""
                Name: %s
                Address: %s %s
                """, NAME, FIRST_LINE_ADDRESS, POST_CODE);
    }


    /**
     * Get the establishment's name.
     *
     * @return name of the establishment
     */
    public String getName() {
        return NAME;
    }


    /**
     * Get the establishment's first line address.
     *
     * @return first line address of the establishment
     */
    public String getFirstLineAddress() {
        return FIRST_LINE_ADDRESS;
    }


    /**
     * Get the establishment's post code.
     *
     * @return post code of the establishment
     */
    public String getPostCode() {
        return POST_CODE;
    }
}
