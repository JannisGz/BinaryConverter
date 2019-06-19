import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Converter class
 * @author Jannis Gumz
 */

public class ConverterTest {

    private Converter converter;

    @Before
    @Test
    public void setUp() {
        converter = new Converter();
        assertEquals("The constructor should initialize all values to '0'.",
                "0", converter.getBinary());
        assertEquals("The constructor should initialize all values to '0'.",
                "0", converter.getOctal());
        assertEquals("The constructor should initialize all values to '0'.",
                "0", converter.getDecimal());
        assertEquals("The constructor should initialize all values to '0'.",
                "0", converter.getHexadecimal());
    }

    @Test
    public void getBinary() {
        converter.calculateRepresentations("1011", 2);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "1011", converter.getBinary());
        converter.calculateRepresentations("777", 8);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "111111111", converter.getBinary());
        converter.calculateRepresentations("90", 10);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "1011010", converter.getBinary());
        converter.calculateRepresentations("aefff", 16);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "10101110111111111111", converter.getBinary());
    }

    @Test
    public void getOctal() {
        converter.calculateRepresentations("1011", 2);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "13", converter.getOctal());
        converter.calculateRepresentations("777", 8);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "777", converter.getOctal());
        converter.calculateRepresentations("90", 10);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "132", converter.getOctal());
        converter.calculateRepresentations("aefff", 16);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "2567777", converter.getOctal());
    }

    @Test
    public void getDecimal() {
        converter.calculateRepresentations("1011", 2);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "11", converter.getDecimal());
        converter.calculateRepresentations("777", 8);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "511", converter.getDecimal());
        converter.calculateRepresentations("90", 10);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "90", converter.getDecimal());
        converter.calculateRepresentations("aefff", 16);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "716799", converter.getDecimal());
    }

    @Test
    public void getHexadecimal() {
        converter.calculateRepresentations("1011", 2);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "b", converter.getHexadecimal());
        converter.calculateRepresentations("777", 8);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "1ff", converter.getHexadecimal());
        converter.calculateRepresentations("90", 10);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "5a", converter.getHexadecimal());
        converter.calculateRepresentations("aefff", 16);
        assertEquals("Should return the value set by calculateRepresentations if valid input is provided.",
                "aefff", converter.getHexadecimal());
    }

    @Test
    public void calculateRepresentationsValidInputTest() {

        assertTrue("Valid binary input should start the calculation.",
                converter.calculateRepresentations("1010010101001111011",2));
        assertEquals("Valid binary input should be converted into a binary representation correctly.",
                "1010010101001111011", converter.getBinary());
        assertEquals("Valid binary input should be converted into an octal representation correctly.",
                "1225173", converter.getOctal());
        assertEquals("Valid binary input should be converted into a decimal representation correctly.",
                "338555", converter.getDecimal());
        assertEquals("Valid binary input should be converted into a hexadecimal representation correctly.",
                "52a7b", converter.getHexadecimal());

        assertTrue("Valid octal input should start the calculation.",
                converter.calculateRepresentations("123450",8));
        assertEquals("Valid octal input should be converted into a binary representation correctly.",
                "1010011100101000", converter.getBinary());
        assertEquals("Valid octal input should be converted into an octal representation correctly.",
                "123450", converter.getOctal());
        assertEquals("Valid octal input should be converted into a decimal representation correctly.",
                "42792", converter.getDecimal());
        assertEquals("Valid octal input should be converted into a hexadecimal representation correctly.",
                "a728", converter.getHexadecimal());

        assertTrue("Valid decimal input should start the calculation.",
                converter.calculateRepresentations("123456789",10));
        assertEquals("Valid decimal input should be converted into a binary representation correctly.",
                "111010110111100110100010101", converter.getBinary());
        assertEquals("Valid decimal input should be converted into an octal representation correctly.",
                "726746425", converter.getOctal());
        assertEquals("Valid decimal input should be converted into a decimal representation correctly.",
                "123456789", converter.getDecimal());
        assertEquals("Valid decimal input should be converted into a hexadecimal representation correctly.",
                "75bcd15", converter.getHexadecimal());

        assertTrue("Valid hexadecimal input should start the calculation.",
                converter.calculateRepresentations("eafbcd",16));
        assertEquals("Valid hexadecimal input should be converted into a binary representation correctly.",
                "111010101111101111001101", converter.getBinary());
        assertEquals("Valid hexadecimal input should be converted into an octal representation correctly.",
                "72575715", converter.getOctal());
        assertEquals("Valid hexadecimal input should be converted into a decimal representation correctly.",
                "15399885", converter.getDecimal());
        assertEquals("Valid hexadecimal input should be converted into a hexadecimal representation correctly.",
                "eafbcd", converter.getHexadecimal());
    }

    @Test
    public void calculateRepresentationsInvalidInputTest() {

        assertFalse("Invalid binary input should not start the calculation.",
                converter.calculateRepresentations("10002001",2));
        assertFalse("Invalid octal input should not start the calculation.",
                converter.calculateRepresentations("12399",8));
        assertFalse("Invalid decimal input should not start the calculation.",
                converter.calculateRepresentations("1fa",10));
        assertFalse("Invalid decimal input should not start the calculation.",
                converter.calculateRepresentations("1fas",16));

        assertFalse("Invalid input should not start the calculation.",
                converter.calculateRepresentations("-3",16));
        assertFalse("Invalid input should not start the calculation.",
                converter.calculateRepresentations("-0",2));
        assertFalse("Invalid input should not start the calculation.",
                converter.calculateRepresentations("-10010100",8));
        assertFalse("Invalid input should not start the calculation.",
                converter.calculateRepresentations("-a",10));

        assertFalse("Input greater than decimal 2147483647 should not start the calculation.",
                converter.calculateRepresentations("2147483648",10));
        assertFalse("Input greater than decimal 2147483647 should not start the calculation.",
                converter.calculateRepresentations("ffffffffffffff",16));
        assertFalse("Input greater than decimal 2147483647 should not start the calculation.",
                converter.calculateRepresentations("1111111100101010101001010101001010011001101010011",2));
        assertFalse("Input greater than decimal 2147483647 should not start the calculation.",
                converter.calculateRepresentations("123456700000",8));

        assertFalse("An invalid base (not 2,8,10 or 16) should not start the calculation.",
                converter.calculateRepresentations("1",11));
        assertFalse("An invalid base (not 2,8,10 or 16) should not start the calculation.",
                converter.calculateRepresentations("000",1));
    }

}