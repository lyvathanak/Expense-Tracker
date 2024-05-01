import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseTracker {

    private static JTextField dayField;
    private static JTextField monthField;
    private static JTextField yearField;
    private static JTextField descriptionField;
    private static JTextField amountField;

    public static void main(String[] args) {
        // Create the main window
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Adjust size as needed

        // Create a panel to hold UI elements with adjusted gaps
        JPanel panel = new JPanel(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        // Labels and Text Fields for user input
        JLabel dayLabel = new JLabel("Day:");
        dayField = new JTextField();
        JLabel monthLabel = new JLabel("Month:");
        monthField = new JTextField();
        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();


        inputPanel.add(dayLabel);
        inputPanel.add(dayField);
        inputPanel.add(monthLabel);
        inputPanel.add(monthField);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        // Combo Box for expense categories (modify as needed)
        String[] categories = {"Groceries", "Bills", "Entertainment", "Transportation", "Utilities", "Other"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);

        // Set JComboBox width to match JTextField components
        categoryComboBox.setPreferredSize(dayField.getPreferredSize());

        // JLabel for Categories
        JLabel categoriesLabel = new JLabel("Categories:");

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Button to add expense
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setPreferredSize(new Dimension(120, 30));

        // Button to generate report
        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setPreferredSize(new Dimension(120, 30));

        buttonPanel.add(addExpenseButton);
        buttonPanel.add(generateReportButton);

        // Text area to display the report
        JTextArea reportTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(reportTextArea);

        // Add components to the panel with specific order
        panel.add(inputPanel, BorderLayout.NORTH);
        // Add the JLabel "Categories" next to the JComboBox
        panel.add(categoriesLabel, BorderLayout.WEST);
        panel.add(categoryComboBox, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.EAST);


        // Add the panel to the frame
        frame.add(panel);

        // List to store expenses
        List<Expense> expenses = new ArrayList<>();

        // Action listener for the "Add Expense" button
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

                // Create Expense object and add to the list
                Expense expense = new Expense(day, month, year, description, amount, category);
                expenses.add(expense);

                // Clear input fields for new entries
                clearInputFields();
            }
        });

        // Action listener for the "Generate Report" button
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate report based on expense data
                StringBuilder reportBuilder = new StringBuilder();
                Map<String, Double> categoryTotalMap = new HashMap<>();

                reportBuilder.append("Expense Report:\n\n");
                reportBuilder.append(String.format("%-15s %-20s %-10s\n", "Category", "Description", "Amount"));
                reportBuilder.append("----------------------------------------------\n");

                for (Expense expense : expenses) {
                    reportBuilder.append(String.format("%-15s %-20s $%-10.2f\n", expense.getCategory(), expense.getDescription(), expense.getAmount()));

                    // Calculate total amount for each category
                    categoryTotalMap.put(expense.getCategory(), categoryTotalMap.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
                }

                reportBuilder.append("----------------------------------------------\n");
                reportBuilder.append("Category Totals:\n");
                for (Map.Entry<String, Double> entry : categoryTotalMap.entrySet()) {
                    reportBuilder.append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
                }

                // Create a new frame to display the report
                JFrame reportFrame = new JFrame("Expense Report");
                JTextArea reportTextArea = new JTextArea(reportBuilder.toString());
                reportTextArea.setEditable(false); // Make the text area non-editable
                JScrollPane scrollPane = new JScrollPane(reportTextArea);
                reportFrame.add(scrollPane);
                reportFrame.setSize(400, 300); // Adjust size as needed
                reportFrame.setVisible(true);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to clear input fields
    private static void clearInputFields() {
        dayField.setText("");
        monthField.setText("");
        yearField.setText("");
        descriptionField.setText("");
        amountField.setText("");
    }
}

// Expense class to represent each expense entry
class Expense {
    private String day;
    private String month;
    private String year;
    private String description;
    private double amount;
    private String category;

    public Expense(String day, String month, String year, String description, double amount, String category) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}
