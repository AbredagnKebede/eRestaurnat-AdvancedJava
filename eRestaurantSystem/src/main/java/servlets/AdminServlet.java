@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminDAO adminDAO = new AdminDAO();
        String action = request.getParameter("action");

        if ("recent".equals(action)) {
            adminDAO.displayRecentOrders().forEach(order -> response.getWriter().println(order));
        } else if ("all".equals(action)) {
            adminDAO.displayAllOrders().forEach(order -> response.getWriter().println(order));
        }
    }
}
