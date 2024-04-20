import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExpenseTracker {

  public static void main(String[] args) {
    // Create the main window
    JFrame frame = new JFrame("Expense Tracker");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);

    // Create a panel to hold UI elements
    JPanel panel = new JPanel(new FlowLayout());

    // Labels and Text Fields for user input
    JLabel dateLabel = new JLabel("Date (YYYY-MM-DD): ");
    JTextField dateField = new JTextField(10);
    JLabel descriptionLabel = new JLabel("Description: ");
    JTextField descriptionField = new JTextField(10);
    JLabel amountLabel = new JLabel("Amount: ");
    JTextField amountField = new JTextField(10);

    // Combo Box for expense categories (modify as needed)
    String[] categories = {"Groceries", "Bills", "Entertainment", "Other"};
    JComboBox<String> categoryComboBox = new JComboBox<>(categories);

    // Button to add expense
    JButton addExpenseButton = new JButton("Add Expense");

    // Add components to the panel
    panel.add(dateLabel);
    panel.add(dateField);
    panel.add(descriptionLabel);
    panel.add(descriptionField);
    panel.add(amountLabel);
    panel.add(amountField);
    panel.add(categoryComboBox);
    panel.add(addExpenseButton);

    // Add the panel to the frame
    frame.add(panel);

    // Action listener for the "Add Expense" button (currently just prints to console)
    addExpenseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String date = dateField.getText();
        String description = descriptionField.getText();
        String amountStr = amountField.getText();
        double amount = Double.parseDouble(amountStr);
        String category = (String) categoryComboBox.getSelectedItem();

        // Logic to store expense data (replace with your implementation)
        System.out.println("Expense Added: " + date + ", " + description + ", " + amount + ", " + category);

        // Clear input fields for new entries
        dateField.setText("");
        descriptionField.setText("");
        amountField.setText("");
      }
    });

    // Make the frame visible
    frame.setVisible(true);
    // update the

    // This line is updated by Menghour
  }
}