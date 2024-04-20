import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Add_Check {

    private JFrame mainFrame;
    private ArrayList<Expense> expenses;

    public Add_Check() {
        expenses = new ArrayList<>();
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Expense Tracker");
        mainFrame.setSize(600, 400);
        mainFrame.setLayout(new BorderLayout());
    
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around the buttons
    
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        addExpenseButton.setFocusPainted(false); // Remove border around the text
        addExpenseButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        addExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddExpenseDialog();
            }
        });
    
        JButton checkExpenseButton = new JButton("Check Expenses");
        checkExpenseButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        checkExpenseButton.setFocusPainted(false); // Remove border around the text
        checkExpenseButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        checkExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showExpenses();
            }
        });
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addExpenseButton, gbc);
    
        gbc.gridy = 1;
        buttonPanel.add(checkExpenseButton, gbc);
    
        mainFrame.add(buttonPanel, BorderLayout.CENTER); // Center the button panel
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null); // Center the frame on the screen
    }
    

    private void showAddExpenseDialog() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
    
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = {"Housing", "Shopping", "Entertainment", "Rent", "Vacation"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        JLabel dateLabel = new JLabel("Date (dd/mm/yyyy):");
        JTextField dateField = new JTextField();
        JButton saveButton = new JButton("Add");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String category = (String) categoryComboBox.getSelectedItem();
                    Date date = new SimpleDateFormat("dd/mm/yyyy").parse(dateField.getText());
                    expenses.add(new Expense(amount, category, date));
                    saveExpenses();
                    // mainFrame.dispose(); Remove this line to keep the frame open after saving
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
                }
            }
        });
    
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(saveButton);
    
        JOptionPane.showMessageDialog(mainFrame, panel, "Add Expense", JOptionPane.PLAIN_MESSAGE);
    }
    

    private void showExpenses() {
        JPanel panel = new JPanel(new GridLayout(expenses.size(), 1));
        for (Expense expense : expenses) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            JLabel label = new JLabel(String.format("Amount: %.2f, Category: %s, Date: %s",
                    expense.getAmount(), expense.getCategory(), dateFormat.format(expense.getDate())));
            panel.add(label);
        }
        JOptionPane.showMessageDialog(mainFrame, panel, "Expenses", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("expenses.txt"))) {
            for (Expense expense : expenses) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                writer.println(String.format("%.2f,%s,%s", expense.getAmount(), expense.getCategory(), dateFormat.format(expense.getDate())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Expense {
        private double amount;
        private String category;
        private Date date;

        public Expense(double amount, String category, Date date) {
            this.amount = amount;
            this.category = category;
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }

        public Date getDate() {
            return date;
        }
    }

    public static void main(String[] args) {
        new Add_Check();
    }
}
