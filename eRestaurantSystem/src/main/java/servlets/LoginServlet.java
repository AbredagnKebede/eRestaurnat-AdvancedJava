@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        AdminDAO adminDAO = new AdminDAO();
        if (userType.equals("admin") && adminDAO.verifyAdmin(username, password)) {
            response.sendRedirect("admin.html");
        } else if (userType.equals("customer")) {
            response.sendRedirect("customer.html");
        } else {
            response.getWriter().println("Invalid credentials!");
        }
    }
}
