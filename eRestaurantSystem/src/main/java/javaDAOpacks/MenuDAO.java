package javaDAOpacks;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuDAO {
    public void displayMenu() {
        String query = "SELECT * FROM Menu";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Food Type\tPrice");
            while (rs.next()) {
                System.out.println(rs.getString("food_type") + "\t" + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
