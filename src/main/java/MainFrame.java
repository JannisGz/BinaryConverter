import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Gui for the converter
 * @author Jannis Gumz
 */

public class MainFrame extends JFrame {

    private static int WINDOW_HEIGHT = 300;
    private static int WINDOW_WIDTH = 400;
    private static int LINE_HEIGHT = (int) (0.2 * WINDOW_HEIGHT);
    private static int OUTPUT_LINE_LENGTH_TITLE = (int) (0.3*WINDOW_WIDTH);
    private static int OUTPUT_LINE_LENGTH_VALUE = WINDOW_WIDTH - OUTPUT_LINE_LENGTH_TITLE;

    private JPanel inputPanel;

    private JTextField inputField;
    private JButton toggleBase;
    private JButton submitButton;

    private JPanel outputPanel;

    private OutputTextField binaryField1;
    private OutputTextField binaryField2;
    private OutputTextField octalField1;
    private OutputTextField octalField2;
    private OutputTextField decimalField1;
    private OutputTextField decimalField2;
    private OutputTextField hexadecimalField1;
    private OutputTextField hexadecimalField2;

    /**
     * Constructor for the gui.
     * Creates a window consisting of two panels. The Input panel at the top contains a textfield for input, a toggle
     * button to choose a base and button to submit.
     * The output panel below contains several uneditable textfields containing the name of the base and the
     * corresponding value.
     */
    public MainFrame() {

        super("BinaryConverter");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBounds(0, 0, WINDOW_WIDTH, (int) (WINDOW_HEIGHT * 0.2));

        inputField = new JTextField("");
        inputField.setToolTipText("Enter your number here");
        inputField.setPreferredSize(new Dimension((int) (WINDOW_WIDTH * 0.7), inputField.getSize().height));

        toggleBase = new JButton("2");
        toggleBase.setToolTipText("Click here to change the base of the input number");
        toggleBase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String toggleBaseText = toggleBase.getText();

                if ("2".equals(toggleBaseText)) {
                    toggleBaseText = "8";
                } else if ("8".equals(toggleBaseText)) {
                    toggleBaseText = "10";
                } else if ("10".equals(toggleBaseText)) {
                    toggleBaseText = "16";
                } else if ("16".equals(toggleBaseText)) {
                    toggleBaseText = "2";
                } else {
                    toggleBaseText = "2";
                }

                toggleBase.setText(toggleBaseText);
            }
        });

        submitButton = new JButton("Convert");
        submitButton.setToolTipText("Click here to submit your input");
        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String base = toggleBase.getText();
                String input = inputField.getText();

                if (Main.converter.calculateRepresentations(input, Integer.valueOf(base))) {
                    binaryField2.setText(Main.converter.getBinary());
                    octalField2.setText(Main.converter.getOctal());
                    decimalField2.setText(Main.converter.getDecimal());
                    hexadecimalField2.setText(Main.converter.getHexadecimal());
                } else {
                    inputField.setText("Error: Please provide valid input.");
                }
            }
        });

        inputPanel.add(inputField, BorderLayout.LINE_START);
        inputPanel.add(toggleBase, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.LINE_END);

        add(inputPanel, BorderLayout.PAGE_START);

        outputPanel = new JPanel(new FlowLayout(0, 0, 0));
        outputPanel.setBounds(0, inputPanel.getHeight(), WINDOW_WIDTH, (int) (WINDOW_HEIGHT * 0.8));

        binaryField1 = new OutputTextField("Binary: ", OUTPUT_LINE_LENGTH_TITLE);
        binaryField2 = new OutputTextField("0", OUTPUT_LINE_LENGTH_VALUE);
        octalField1 = new OutputTextField("Octal: ", OUTPUT_LINE_LENGTH_TITLE);
        octalField2 = new OutputTextField("0", OUTPUT_LINE_LENGTH_VALUE);
        decimalField1 = new OutputTextField("Decimal: ", OUTPUT_LINE_LENGTH_TITLE);
        decimalField2 = new OutputTextField("0", OUTPUT_LINE_LENGTH_VALUE);
        hexadecimalField1 = new OutputTextField("Hexadecimal: ", OUTPUT_LINE_LENGTH_TITLE);
        hexadecimalField2 = new OutputTextField("0", OUTPUT_LINE_LENGTH_VALUE);

        add(outputPanel);


        setVisible(true);
    }

    /**
     * A specialised version of a JTextField that will
     */
    private class OutputTextField extends JTextField {

        /**
         * Creates an uneditable JTextField with the given values and adds it to the output panel.
         * @param text the displayed String
         * @param length the total length of the textfield.
         */
        public OutputTextField(String text, int length) {
            super(text);
            setEditable(false);
            setPreferredSize(new Dimension(length, LINE_HEIGHT));
            outputPanel.add(this);
        }

    }

}
