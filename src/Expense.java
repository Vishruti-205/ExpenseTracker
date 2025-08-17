import java.time.LocalDate;

public class Expense {
    private String category;
    private double amount;
    private LocalDate date;

    public Expense(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return date + "," + category + "," + amount;
    }

    // Parse from file line
    public static Expense fromString(String line) {
        String[] parts = line.split(",");
        return new Expense(parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[0]));
    }
}
