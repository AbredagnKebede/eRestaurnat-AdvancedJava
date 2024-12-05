import javaDAOpacks.AdminDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Annotation to map the servlet to a URL
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create an instance of AdminDAO to handle database operations
        AdminDAO adminDAO = new AdminDAO();

        // Example logic: retrieve and display all orders
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>All Orders</h1>");
        response.getWriter().println("<ul>");
        adminDAO.displayAllOrders().forEach(order -> {
            try {
                response.getWriter().println("<li>" + order + "</li>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        response.getWriter().println("</ul>");
        response.getWriter().println("</body></html>");
    }
}
