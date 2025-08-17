import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private final String FILE_NAME = "expenses.txt";

    // Constructor -> load saved expenses if file exists
    public ExpenseManager() {
        loadFromFile();
    }

    // Add a new expense
    public void addExpense(String category, double amount) {
        Expense e = new Expense(category, amount, LocalDate.now());
        expenses.add(e);
        saveToFile();
    }

    // Return all expenses as a list of strings (for UI display)
    public List<String> showAllExpenses() {
        List<String> list = new ArrayList<>();
        if (expenses.isEmpty()) {
            list.add("No expenses recorded yet.");
        } else {
            for (Expense e : expenses) {
                list.add(e.getDate() + " | " + e.getCategory() + " | ₹" + e.getAmount());
            }
        }
        return list;
    }

    // Calculate total for current month
    public double getMonthlyTotal() {
        return expenses.stream()
                .filter(e -> e.getDate().getMonth() == LocalDate.now().getMonth()
                        && e.getDate().getYear() == LocalDate.now().getYear())
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Calculate totals by category
    public Map<String, Double> getCategoryTotals() {
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense e : expenses) {
            categoryTotals.put(
                e.getCategory(),
                categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }
        return categoryTotals;
    }

    // Save all expenses to file
    private void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                out.println(e.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load expenses from file
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                expenses.add(Expense.fromString(sc.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
