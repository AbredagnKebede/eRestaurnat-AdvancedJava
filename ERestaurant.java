import java.util.Scanner;

public class ERestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        OrderDAO orderDAO = new OrderDAO();
        MenuDAO menuDAO = new MenuDAO();

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
        }
    }
}