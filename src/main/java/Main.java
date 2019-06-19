/**
 * The executing class for the binary converter.
 * The program will convert any given positive number up to 2147483647 into binary, octal, decimal and hexadecimal
 * representations. The input itself can be provided as a binary, octal, decimal or hexadecimal number. However the
 * input and chosen base must be consistent (i.e. if '8' is chosen as a base (octal) the input can only contain the
 * characters 0-7).
 */

public class Main {

    static Converter converter;

    public static void main(String[] args) {

        converter = new Converter();

        new MainFrame();

    }
}
