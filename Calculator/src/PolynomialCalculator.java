import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialCalculator extends JFrame {
    private JTextField polynomialField1, polynomialField2, resultField;
    private JButton addButton, subtractButton;

    public PolynomialCalculator() {
        // Set up the JFrame
        setTitle("Polynomial Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        polynomialField1 = new JTextField(20);
        polynomialField2 = new JTextField(20);
        resultField = new JTextField(20);
        resultField.setEditable(false);

        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");

        // Set layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the frame
        add(new JLabel("Polynomial 1:"));
        add(polynomialField1);
        add(new JLabel("Polynomial 2:"));
        add(polynomialField2);
        add(new JLabel("Result:"));
        add(resultField);
        add(addButton);
        add(subtractButton);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("add");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("subtract");
            }
        });
    }

    private void performOperation(String operation) {
        try {
            String poly1Str = polynomialField1.getText();
            String poly2Str = polynomialField2.getText();

            // Parse the polynomials
            Polynomial poly1 = new Polynomial(poly1Str);
            Polynomial poly2 = new Polynomial(poly2Str);

            // Perform the operation
            Polynomial result = null;
            if (operation.equals("add")) {
                result = poly1.add(poly2);
            } else if (operation.equals("subtract")) {
                result = poly1.subtract(poly2);
            }

            // Display the result
            resultField.setText(result.toString());
        } catch (IllegalArgumentException ex) {
            // Handle invalid input
            resultField.setText("Invalid input. Please enter valid polynomials.");
        }
    }
}


