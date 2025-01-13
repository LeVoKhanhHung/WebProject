package com.example.webapp.controllers;

import com.example.webapp.daos.SalesDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.webapp.daos.ProductDAO;
import com.example.webapp.models.Sale;

@WebServlet(name = "PromotionController", value = "/PromotionController")
public class PromotionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            listSales(request, response);
        } else {
            switch (action) {
                case "delete":
                    deleteSale(request, response);
                    break;
                case "editForm":
                    showEditForm(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addSale(request, response);
        } else if ("update".equals(action)) {
            updateSale(request, response);
        }
    }

    private void listSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Sale> sales = SalesDAO.getAllSales();
        request.setAttribute("sales", sales);
        request.getRequestDispatcher("/manage-sale.jsp").forward(request, response);
    }

    private void addSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idVariant = Integer.parseInt(request.getParameter("idVariant"));
        int salePercent = Integer.parseInt(request.getParameter("salePercent"));
        Date saleStartDate = parseDate(request.getParameter("saleStartDate"));
        Date saleEndDate = parseDate(request.getParameter("saleEndDate"));

        Sale sale = new Sale(0, idVariant, salePercent, saleStartDate, saleEndDate);

        if (SalesDAO.addSale(sale)) {
            response.sendRedirect("SalesController");
        } else {
            response.getWriter().println("Thêm thất bại!");
        }
    }

    private void updateSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idVariant = Integer.parseInt(request.getParameter("idVariant"));
        int salePercent = Integer.parseInt(request.getParameter("salePercent"));
        Date saleStartDate = parseDate(request.getParameter("saleStartDate"));
        Date saleEndDate = parseDate(request.getParameter("saleEndDate"));

        Sale sale = new Sale(id, idVariant, salePercent, saleStartDate, saleEndDate);

        if (SalesDAO.updateSale(sale)) {
            response.sendRedirect("SalesController");
        } else {
            response.getWriter().println("Cập nhật thất bại!");
        }
    }

    private void deleteSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        if (SalesDAO.deleteSale(id)) {
            response.sendRedirect("SalesController");
        } else {
            response.getWriter().println("Xóa thất bại!");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Sale> sales = SalesDAO.getAllSales();
        for (Sale sale : sales) {
            if (sale.getId() == id) {
                request.setAttribute("sale", sale);
                break;
            }
        }
        request.getRequestDispatcher("/edit-sale.jsp").forward(request, response);
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}