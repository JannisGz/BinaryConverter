/**
 * A class for converting an input number to its binary, octal, decimal and hexdecimal representations.
 *
 * @author Jannis Gumz
 */

public class Converter {

    private String binaryValue;
    private String octalValue;
    private String decimalValue;
    private String hexdecimalValue;

    /**
     * Creates an instance of the Converter class and initializes all representations.
     */

    public Converter() {
        binaryValue = "0";
        octalValue = "0";
        decimalValue = "0";
        hexdecimalValue = "0";
    }

    /**
     * Fetches the calculated binary representation of the last given number
     *
     * @return the currently stored binary value
     */

    public String getBinary() {
        return binaryValue;
    }

    /**
     * Fetches the calculated octal representation of the last given number
     *
     * @return the currently stored octal value
     */

    public String getOctal() {
        return octalValue;
    }

    /**
     * Fetches the calculated decimal representation of the last given number
     *
     * @return the currently stored decimal value
     */

    public String getDecimal() {
        return decimalValue;
    }

    /**
     * Fetches the calculated hexdecimal representation of the last given number
     *
     * @return the currently stored hexdecimal value
     */

    public String getHexadecimal() {
        return hexdecimalValue;
    }

    /**
     * Calculates the binary, octal, decimal and hexdecimal values of a given number.
     *
     * @param input the given number as a String
     * @param base  the base (2 for binary, 8 for octal, 10 for decimal, 16 for hexdecimal) of the given number
     * @return true if the calculation was successful and false if an error occurred.
     */

    public boolean calculateRepresentations(String input, int base) {

        input = input.toLowerCase();

        if (!isValidInput(input, base)) {
            return false;
        }

        try {
            decimalValue = String.valueOf(Integer.parseInt(input, base));
            binaryValue = Integer.toString(Integer.valueOf(decimalValue), 2);
            octalValue = Integer.toString(Integer.valueOf(decimalValue), 8);
            hexdecimalValue = Integer.toString(Integer.valueOf(decimalValue), 16);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the given values are valid input parameters. The given base has to be either 2,8,10 or 16 and the
     * provided input has to be a positive number representing a decimal integer smaller than 2,147,483,647
     *
     * @param input the provided number
     * @param base  the base (2 for binary, 8 for octal, 10 for decimal, 16 for hexdecimal) of the given number
     * @return true if all parameters are valid, else false
     */

    private boolean isValidInput(String input, int base) {

        if (!(base == 2 || base == 8 || base == 10 || base == 16)) {
            return false;
        }

        boolean isValid = false;

        switch (base) {
            case 2:
                isValid = input.matches("^[01]*$");
                break;
            case 8:
                isValid = input.matches("^[0-7]*$");
                break;
            case 10:
                isValid = input.matches("^[0-9]*$");
                break;
            case 16:
                isValid = input.matches("^[0-9a-f]*$");
                break;
            default:
                break;
        }

        return isValid;

    }

}
