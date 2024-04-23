import javax.swing.*;

import static java.lang.Math.round;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Add_Check {

    private JFrame mainFrame;
    private ArrayList<Expense> expenses;

    public Add_Check() {
        expenses = new ArrayList<>();
        loadExpenses(); // Load expenses when the application starts
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

        JButton display_pieButton = new JButton("Display Graphical");
        display_pieButton.setPreferredSize(new Dimension(200, 50)); // Set button size
        display_pieButton.setFocusPainted(false); // Remove border around the text
        display_pieButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        display_pieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayPieChart();
                
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addExpenseButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(checkExpenseButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(display_pieButton, gbc);

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
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateField.getText()); // Corrected date format
                    expenses.add(new Expense(amount, category, date));
                    saveExpenses();
                    // mainFrame.dispose(); Remove this line to keep the frame open after saving
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use dd/mm/yyyy.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount format. Please enter a valid number.");
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
        if (expenses.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "No expenses recorded yet.", "Expenses", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JPanel panel = new JPanel(new GridLayout(expenses.size(), 1));
        for (Expense expense : expenses) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            JLabel label = new JLabel(String.format("Amount: %.2f, Category: %s, Date: %s",
                    expense.getAmount(), expense.getCategory(), dateFormat.format(expense.getDate())));
            panel.add(label);
        }
        JOptionPane.showMessageDialog(mainFrame, panel, "Expenses", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveExpenses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("expenses.txt"))) {
            for (Expense expense : expenses) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                writer.println(String.format("%.2f,%s,%s", expense.getAmount(), expense.getCategory(), dateFormat.format(expense.getDate())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double amount = Double.parseDouble(parts[0]);
                String category = parts[1];
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(parts[2]);
                expenses.add(new Expense(amount, category, date));
            }
        } catch (IOException | ParseException e) {
            // If file doesn't exist or format is incorrect, just continue with an empty list
        }
    }

    private void displayPieChart() {
        // Create a map to store the total expenses for each category
        Map<String, Double> categoryExpenses = new HashMap<>();

        // Calculate total expenses for each category
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();
            categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + amount);
        }

        // Create and configure a new JFrame for displaying the pie chart
        JFrame pieFrame = new JFrame("Expense Distribution Pie Chart");
        pieFrame.setSize(600, 600);
        pieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JPanel for drawing the pie chart
        JPanel piePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g, getWidth(), getHeight(), categoryExpenses);
            }
        };

        // Add the pie panel to the frame and make it visible
        pieFrame.add(piePanel);
        pieFrame.setLocationRelativeTo(null);
        pieFrame.setVisible(true);
    }

    // Method to draw the pie chart
    private void drawPieChart(Graphics g, int width, int height, Map<String, Double> categoryExpenses) {
        // Define colors for different categories
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA};

        // Store colors for each category
        Map<String, Color> categoryColors = new HashMap<>();
        int colorIndex = 0;
        for (String category : categoryExpenses.keySet()) {
            categoryColors.put(category, colors[colorIndex % colors.length]);
            colorIndex++;
        }

        // Calculate total expenses
        double totalExpenses = categoryExpenses.values().stream().mapToDouble(Double::doubleValue).sum();

        // Draw each category slice
        int startAngle = 0;
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            double expense = entry.getValue();
            int arcAngle = (int) round((expense / totalExpenses) * 360);
            String category = entry.getKey();
            g.setColor(categoryColors.get(category));
            g.fillArc(50, 50, width - 100, height - 100, startAngle, arcAngle);
            startAngle += arcAngle;
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
