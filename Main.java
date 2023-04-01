/*
* Name: Isaac Francisco Marte
* Class : CMSC 350
* Lab 1
*
* Purpose of Application:
* The main class extends JFrame methods and implements the action listener methods
* which are used by the class to provide a responsive graphical user interface
* where the user interacts with the application.
*
* */

package cmsc350projects.project1;

// Method imports modules used for GUI application, handle exceptions and action listeners for user responses
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

// Main class extends JFrame and implements action listeners to visualize and respond to user
public class Main extends JFrame implements ActionListener {

    // Declares variables as constants and are used for the gui fields such as label, buttons, and text fields.
    private static final JLabel INPUT_EXPRESSION_LABEL = new JLabel("Enter Expression");
    private static final JLabel RESULT_EXPRESSION_LABEL = new JLabel("Result");
    private static final JButton POSTFIX_TO_PREFIX_BUTTON = new JButton("Postfix to Prefix");
    private static final JButton PREFIX_TO_POST_BUTTON = new JButton("Prefix to Postfix");
    private static final JTextField EXPRESSION_FIELD = new JTextField(25);
    private static final JTextField RESULT_FIELD = new JTextField(25);

    // constructor sets title for window, panel properties, and location of panels within using border layout
    public Main() {
        super("Expression Converter");
        JPanel mainPanel = new JPanel(); // main panel object where other panels will be nested within
        setContentPane(mainPanel);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // sets empty border space
        mainPanel.setLayout(new BorderLayout());  // Border layout is used to position panels within each section
        mainPanel.add(northPanel(), BorderLayout.NORTH); // sets north panel to north of border layout
        mainPanel.add(centerPanel(), BorderLayout.CENTER);  // sets center panel to center of border layout
        mainPanel.add(southPanel(), BorderLayout.SOUTH);  // sets south panel to south of border layout
    }

    // North panel sets input label, and text field in north section of border layout,
    // and is location in window where user enters value to convert.
    private JPanel northPanel() {
        JPanel northPanel = new JPanel();
        northPanel.add(INPUT_EXPRESSION_LABEL);
        northPanel.add(EXPRESSION_FIELD);

        return northPanel;  // returns north panel with added fields
    }

    // Center panel sets both the prefix and postfix conversion buttons. As well as adds action listener
    // to the buttons and positions the panel in center of the border layout.
    private JPanel centerPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.add(PREFIX_TO_POST_BUTTON);  // adds button for prefix
        centerPanel.add(POSTFIX_TO_PREFIX_BUTTON); // adds button for post
        PREFIX_TO_POST_BUTTON.addActionListener(this);  // action listener for pressing prefix button
        POSTFIX_TO_PREFIX_BUTTON.addActionListener(this); // action listener for pressing postfix button
        return centerPanel;
    }

    // south panel sets result label, and text field for the result in the south section of the border layout
    private JPanel southPanel() {
        JPanel southPanel = new JPanel();
        southPanel.add(RESULT_EXPRESSION_LABEL);  // add label
        southPanel.add(RESULT_FIELD);  // adds text field
        return southPanel;
    }

    // Method reads action performed by user and based on condition calls upon prefix to postfix
    // or vice versa conversion method in converter class.
    public void actionPerformed(ActionEvent e) {

            // try and catch block catch exception such as empty stack, syntax, or runtime exception.
            try {
                // conditional checks which button was pressed and calls on the selected Converter method.
                if (e.getSource() == PREFIX_TO_POST_BUTTON) {
                    RESULT_FIELD.setText(String.valueOf(Converter.prefixToPostfix(EXPRESSION_FIELD.getText())));

                } else if (e.getSource() == POSTFIX_TO_PREFIX_BUTTON) {
                    RESULT_FIELD.setText(Converter.postfixToPrefix(EXPRESSION_FIELD.getText()));
                }

            } catch (EmptyStackException ex){
                JFrame error1 = new JFrame();
                JOptionPane.showMessageDialog(error1, "Pop on an empty stack!",
                        "Alert", JOptionPane.WARNING_MESSAGE);

            } catch (SyntaxError ex) {
                JFrame error2 = new JFrame();
                JOptionPane.showMessageDialog(error2, "Incorrect Format: Items left in Stack!",
                        "ALERT", JOptionPane.WARNING_MESSAGE);

            } catch (RuntimeException ex){
                JFrame error3 = new JFrame();
                JOptionPane.showMessageDialog(error3, "Unknown Error occurred ",
                        "Alert", JOptionPane.WARNING_MESSAGE);
            }

    }

    // The main method runs program window, and sets parameters for main window once application is initiated
    public static void main(String[] args) {
        Main frame = new Main();  // instantiates main class which in turn extends the JFrame capabilities

        frame.setVisible(true);  // Makes frame visible
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // stops app when window is closed
        frame.setBounds(150, 150, 400, 200);  // Sets permanent size of window
        frame.setResizable(false);  //Prevents user from changing the size of the main window
    }
}

