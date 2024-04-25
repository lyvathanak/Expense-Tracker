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
      frame.setSize(500, 300); // Adjust size as needed

      // Create a panel to hold UI elements with adjusted gaps
      JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

      // Labels and Text Fields for user input
      JLabel dayLabel = new JLabel("Day: ");
      JTextField dayField = new JTextField(3);
      JLabel monthLabel = new JLabel("Month: ");
      JTextField monthField = new JTextField(3);
      JLabel yearLabel = new JLabel("Year: ");
      JTextField yearField = new JTextField(4);
      JLabel descriptionLabel = new JLabel("Description: ");
      JTextField descriptionField = new JTextField(20);
      JLabel amountLabel = new JLabel("Amount: ");
      JTextField amountField = new JTextField(10);

      // Combo Box for expense categories (modify as needed)
      String[] categories = { "Groceries", "Bills", "Entertainment", "Transportation", "Utilities", "Other" };
      JComboBox<String> categoryComboBox = new JComboBox<>(categories);

      // Button to add expense with fixed width
      JButton addExpenseButton = new JButton("Add Expense");
      addExpenseButton.setPreferredSize(new java.awt.Dimension(120, 30));

      // Button to generate report
      JButton generateReportButton = new JButton("Generate Report");
      generateReportButton.setPreferredSize(new java.awt.Dimension(120, 30));

      // Add components to the panel with specific order
      panel.add(dayLabel);
      panel.add(dayField);
      panel.add(monthLabel);
      panel.add(monthField);
      panel.add(yearLabel);
      panel.add(yearField);
      panel.add(descriptionLabel);
      panel.add(descriptionField);
      panel.add(amountLabel);
      panel.add(amountField);
      panel.add(categoryComboBox);
      panel.add(addExpenseButton);
      panel.add(generateReportButton); // Add generate report button

      // Add the panel to the frame
      frame.add(panel);

      // Action listener for the "Add Expense" button (currently just prints to
      // console)
      addExpenseButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String day = dayField.getText();
          String month = monthField.getText();
          String year = yearField.getText();
          String description = descriptionField.getText();
          String amountStr = amountField.getText();
          double amount = Double.parseDouble(amountStr);
          String category = (String) categoryComboBox.getSelectedItem();

          // Logic to store expense data (replace with your implementation)
          System.out.println(
              "Expense Added: " + day + "/" + month + "/" + year + ", " + description + ", " + amount + ", " + category);

          // Clear input fields for new entries
          dayField.setText("");
          monthField.setText("");
          yearField.setText("");
          descriptionField.setText("");
          amountField.setText("");
        }
      });

    // Action listener for the "Generate Report" button
    generateReportButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Logic to generate report
        System.out.println("Report Generated!");
      }
    });

      // Make the frame visible
      frame.setVisible(true);
    }
  }
