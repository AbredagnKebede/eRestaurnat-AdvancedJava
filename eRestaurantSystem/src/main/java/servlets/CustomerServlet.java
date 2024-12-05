import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaDAOpacks.OrderDAO;

import java.io.IOException;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract form data from the request
        String foodType = request.getParameter("foodType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Create an order using OrderDAO
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(1, foodType, quantity, quantity * 100, 1234); // Example data

        // Send a response back to the client
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Order Placed Successfully!</h1>");
        response.getWriter().println("<p>Food: " + foodType + "</p>");
        response.getWriter().println("<p>Quantity: " + quantity + "</p>");
        response.getWriter().println("</body></html>");
    }
}
