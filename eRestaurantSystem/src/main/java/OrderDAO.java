import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
    public void addOrder(int customerId, String foodType, int quantity, double totalPrice, int verificationCode) {
        String query = "INSERT INTO Orders (customer_id, food_type, quantity, total_price, verification_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, foodType);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, totalPrice);
            stmt.setInt(5, verificationCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
