import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. Show All Expenses");
            System.out.println("3. Show Monthly Total");
            System.out.println("4. Show Category Totals");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    manager.addExpense(cat, amt);
                    break;
                case 2:
                    manager.showAllExpenses();
                    break;
                case 3:
                    manager.getMonthlyTotal();
                    break;
                case 4:
                    manager.getCategoryTotals();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
