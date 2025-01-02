package servlets;

import com.google.gson.Gson;
import models.User;
import daos.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ request (dưới dạng JSON)
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonData = sb.toString();
        Gson gson = new Gson();
        User user = gson.fromJson(jsonData, User.class);

        // Xử lý cập nhật thông tin người dùng trong database
        boolean success = UserDAO.updateUser(user);

        // Trả về kết quả
        response.setContentType("application/json");
        response.getWriter().write("{\"success\": " + success + "}");
    }
}