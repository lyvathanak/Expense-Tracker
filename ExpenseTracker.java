import java.awt.BorderLayout;
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
    frame.setSize(600, 400); // Adjust size as needed

    // Create a panel for the header
    JPanel headerPanel = new JPanel(new FlowLayout());
    JLabel titleLabel = new JLabel("Expense Tracker"); // You can add an icon here
    headerPanel.add(titleLabel);

    // Create a panel to hold UI elements for user input
    JPanel inputPanel = new JPanel(new FlowLayout());

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

    // Add components to the input panel
    inputPanel.add(dateLabel);
    inputPanel.add(dateField);
    inputPanel.add(descriptionLabel);
    inputPanel.add(descriptionField);
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryComboBox);
    inputPanel.add(addExpenseButton);

    // Main content panel (can be extended to include expense list or summary)
    JPanel contentPanel = new JPanel(); // Modify layout as needed

    // Add action listener for the "Add Expense" button (similar to previous code)

    // Add panels to the frame with layout manager
    frame.setLayout(new BorderLayout());
    frame.add(headerPanel, BorderLayout.NORTH);
    frame.add(inputPanel, BorderLayout.CENTER);
    frame.add(contentPanel, BorderLayout.SOUTH); // Adjust position for content

    // Make the frame visible
    frame.setVisible(true);
  }
}