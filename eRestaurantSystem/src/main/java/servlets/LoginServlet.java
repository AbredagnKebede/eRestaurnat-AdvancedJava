import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaDAOpacks.AdminDAO;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve login credentials from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        // Logic to verify user credentials
        AdminDAO adminDAO = new AdminDAO();
        if ("admin".equals(userType) && adminDAO.verifyAdmin(username, password)) {
            // Redirect to admin dashboard
            response.sendRedirect("admin.html");
        } else if ("customer".equals(userType)) {
            // Redirect to customer portal
            response.sendRedirect("customer.html");
        } else {
            // Invalid credentials
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Invalid Credentials</h1>");
            response.getWriter().println("<p>Please try again.</p>");
            response.getWriter().println("</body></html>");
        }
    }
}
