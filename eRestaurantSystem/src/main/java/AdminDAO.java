import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public boolean verifyAdmin(String name, String password) {
        String query = "SELECT * FROM Admins WHERE name = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // If a result exists, credentials are valid
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void displayRecentOrders() {
        String query = "SELECT o.order_id, c.name, c.address, o.food_type, o.quantity, o.total_price, o.order_date " +
                       "FROM Orders o JOIN Customers c ON o.customer_id = c.customer_id " +
                       "WHERE o.order_date >= NOW() - INTERVAL 30 MINUTE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Recent Orders (Last 30 Minutes):");
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer Name: " + rs.getString("name"));
                System.out.println("Customer Address: " + rs.getString("address"));
                System.out.println("Food Type: " + rs.getString("food_type"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Total Price: " + rs.getDouble("total_price"));
                System.out.println("Order Date: " + rs.getTimestamp("order_date"));
                System.out.println("-------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllOrders() {
        String query = "SELECT o.order_id, c.name, c.address, o.food_type, o.quantity, o.total_price, o.order_date " +
                       "FROM Orders o JOIN Customers c ON o.customer_id = c.customer_id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("All Orders:");
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer Name: " + rs.getString("name"));
                System.out.println("Customer Address: " + rs.getString("address"));
                System.out.println("Food Type: " + rs.getString("food_type"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Total Price: " + rs.getDouble("total_price"));
                System.out.println("Order Date: " + rs.getTimestamp("order_date"));
                System.out.println("-------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     // Method to add a new food item to the menu
    public void addFoodToMenu(String foodType, double price) {
        String query = "INSERT INTO Menu (food_type, price) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, foodType);
            stmt.setDouble(2, price);
            stmt.executeUpdate();
            System.out.println("Food item added successfully: " + foodType + " - " + price + " birr");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update the price of an existing food item
    public void updateFoodPrice(String foodType, double newPrice) {
        String query = "UPDATE Menu SET price = ? WHERE food_type = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newPrice);
            stmt.setString(2, foodType);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Price updated successfully for: " + foodType + " - New Price: " + newPrice + " birr");
            } else {
                System.out.println("Food item not found: " + foodType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
