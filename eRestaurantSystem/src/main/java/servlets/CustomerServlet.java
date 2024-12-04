@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String foodType = request.getParameter("foodType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(1, foodType, quantity, quantity * 100, 1234); // Example order

        response.getWriter().println("Order placed successfully!");
    }
}
