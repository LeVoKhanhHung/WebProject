package Controller;

import Models.inforTransaction.Transaction;
import Services.ServiceProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        value = "/transactionHistory"
)
public class displayTransactionHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction transaction = new Transaction();
        ServiceProduct pro = new ServiceProduct();
        String idUser = req.getParameter("idUser");
        try {
            pro.selectTransactionHistory(Integer.parseInt(idUser),transaction);
            System.out.println(transaction.getItems().size());
            HttpSession session = req.getSession(true);

            session.setAttribute("transactions",transaction);
            req.getRequestDispatcher("history.jsp").forward(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
