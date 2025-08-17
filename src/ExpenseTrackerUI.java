import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExpenseTrackerUI extends JFrame {
    private ExpenseManager manager;
    private JTextField categoryField, amountField;
    private JTextArea outputArea;

    public ExpenseTrackerUI() {
        manager = new ExpenseManager();

        setTitle("Expense Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Amount (₹):"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Expense");
        JButton allBtn = new JButton("Show All");
        JButton monthBtn = new JButton("Monthly Total");
        JButton catBtn = new JButton("Category Totals");

        buttonPanel.add(addBtn);
        buttonPanel.add(allBtn);
        buttonPanel.add(monthBtn);
        buttonPanel.add(catBtn);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String cat = categoryField.getText();
            String amtText = amountField.getText();
            if (cat.isEmpty() || amtText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter category and amount!");
                return;
            }
            try {
                double amt = Double.parseDouble(amtText);
                manager.addExpense(cat, amt);
                outputArea.append("Added: " + cat + " ₹" + amt + "\n");
                categoryField.setText("");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number!");
            }
        });

        allBtn.addActionListener(e -> {
            outputArea.setText(""); // clear
            for (String s : manager.showAllExpenses()) {
                outputArea.append(s + "\n");
            }
        });

        monthBtn.addActionListener(e -> {
            outputArea.setText("Total this month: ₹" + manager.getMonthlyTotal() + "\n");
        });

        catBtn.addActionListener(e -> {
            outputArea.setText("Category Totals:\n");
            manager.getCategoryTotals().forEach((cat, amt) ->
                    outputArea.append(cat + ": ₹" + amt + "\n"));
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerUI().setVisible(true));
    }
}
