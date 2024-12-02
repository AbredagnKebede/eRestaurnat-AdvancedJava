import java.util.Scanner;

public class ERestaurant {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CustomerDAO customerDAO = new CustomerDAO();
            OrderDAO orderDAO = new OrderDAO();
            MenuDAO menuDAO = new MenuDAO();
            AdminDAO adminDAO = new AdminDAO();

            System.out.println("Are you a customer or staff? Enter 1 for customer, 2 for staff:");
            int userType = scanner.nextInt();

            if (userType == 1) { // Customer
                System.out.println("Enter your name:");
                String name = scanner.next();
                System.out.println("Enter your address:");
                String address = scanner.next();

                customerDAO.addCustomer(name, address);

                System.out.println("Welcome, " + name + "! Here's our menu:");
                menuDAO.displayMenu();

                System.out.println("Enter the food type:");
                String foodType = scanner.next();
                System.out.println("Enter the quantity:");
                int quantity = scanner.nextInt();

                double price = 100; // Replace with a query to fetch price from the database
                double totalPrice = price * quantity;

                int verificationCode = (int) (Math.random() * 9000) + 1000;
                System.out.println("Your verification code: " + verificationCode);

                orderDAO.addOrder(1, foodType, quantity, totalPrice, verificationCode);
            } else if (userType == 2) { // Admin
                System.out.println("Enter your name:");
                String adminName = scanner.next();
                System.out.println("Enter your password:");
                String adminPassword = scanner.next();

                if (adminDAO.verifyAdmin(adminName, adminPassword)) {
                    System.out.println("Login successful! Welcome, " + adminName + ".");
                    boolean adminMenu = true;
                    while (adminMenu) {
                        System.out.println("What would you like to do?");
                        System.out.println("1. View all orders");
                        System.out.println("2. View recent orders (last 30 minutes)");
                        System.out.println("3. Add a new food item to the menu");
                        System.out.println("4. Update food price");
                        System.out.println("5. Logout");

                        int adminChoice = scanner.nextInt();
                        switch (adminChoice) {
                            case 1:
                                adminDAO.displayAllOrders();
                                break;
                            case 2:
                                adminDAO.displayRecentOrders();
                                break;
                            case 3:
                                System.out.println("Enter food name:");
                                scanner.nextLine(); // Consume newline
                                String foodName = scanner.nextLine();
                                System.out.println("Enter food price:");
                                double price = scanner.nextDouble();
                                adminDAO.addFoodToMenu(foodName, price);
                                break;
                            case 4:
                                System.out.println("Enter the food name to update:");
                                scanner.nextLine(); // Consume newline
                                String foodToUpdate = scanner.nextLine();
                                System.out.println("Enter the new price:");
                                double newPrice = scanner.nextDouble();
                                adminDAO.updateFoodPrice(foodToUpdate, newPrice);
                                break;
                            case 5:
                                adminMenu = false;
                                System.out.println("Logged out successfully.");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid admin name or password.");
                }
            } else {
                System.out.println("Invalid user type.");
            }
        }
    }
}
