import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Convert {
    private JFrame frame;
    private JPanel panel;
    private JLabel amountLabel;
    private JLabel resultLabel;
    private JTextField amountField;
    private JTextField resultField;
    private JButton convertButton;

    private static final double EXCHANGE_RATE = 4000; // 1 USD = 4000 Riels

    public Convert() {
        frame = new JFrame("Exchange Rate Converter");
        panel = new JPanel(new GridLayout(3, 2));

        amountLabel = new JLabel("Amount in USD:");
        resultLabel = new JLabel("Amount in Riels:");
        amountField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false); // Make the result field non-editable

        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amountUSD = Double.parseDouble(amountField.getText());
                    double amountRiels = amountUSD * EXCHANGE_RATE;
                    resultField.setText(String.format("%.2f", amountRiels));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(resultLabel);
        panel.add(resultField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(convertButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Convert();
            }
        });
    }
}