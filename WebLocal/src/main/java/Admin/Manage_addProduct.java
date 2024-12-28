package Admin;

import Dao.ProductsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB: Kích thước file tối thiểu để lưu tạm
        maxFileSize = 1024 * 1024 * 10,      // 10MB: Kích thước tối đa cho mỗi file
        maxRequestSize = 1024 * 1024 * 50    // 50MB: Tổng kích thước request tối đa
)
@WebServlet(value = "/addProduct")
public class Manage_addProduct extends HttpServlet {

    private final ProductsDao dao = new ProductsDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PreparedStatement psSale = null;
        PreparedStatement psVariant = null;
        try (Connection conn = dao.getConn()) {
            conn.setAutoCommit(false);

            // 1. Lấy dữ liệu từ form
            String productName = req.getParameter("product_Name");
            float productPrice = parseFloatSafe(req.getParameter("product_Price"), 0);
            int productQuantity = parseIntSafe(req.getParameter("product_Quantity"), 0);
            String productDescription = req.getParameter("product_Description");
            String productSupplier = req.getParameter("product_Supplier");
            String productCategory = req.getParameter("product_Category");
            String productStatus = req.getParameter("product_Status");
            String salePercentStr = req.getParameter("sale_Percent");
            String saleStartDate = req.getParameter("sale_StartDate");
            String saleEndDate = req.getParameter("sale_EndDate");
            String productWeightStr = req.getParameter("product_Weight");

            int productId;

            // 2. Kiểm tra xem sản phẩm đã tồn tại chưa
            String sqlCheckProduct = "SELECT id FROM products WHERE productName = ?";
            try (PreparedStatement psCheck = conn.prepareStatement(sqlCheckProduct)) {
                psCheck.setString(1, productName);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next()) {
                        productId = rs.getInt("id");
                    } else {
                        // 3. Thêm mới sản phẩm nếu chưa tồn tại
                        String sqlProduct = "INSERT INTO products (productName, idCategory, idSupplier, isActive) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement psProduct = conn.prepareStatement(sqlProduct, PreparedStatement.RETURN_GENERATED_KEYS)) {
                            psProduct.setString(1, productName);
                            psProduct.setString(2, productCategory);
                            psProduct.setString(3, productSupplier);
                            psProduct.setBoolean(4, "Còn hàng".equals(productStatus));
                            psProduct.executeUpdate();

                            try (ResultSet rsProduct = psProduct.getGeneratedKeys()) {
                                if (rsProduct.next()) {
                                    productId = rsProduct.getInt(1);
                                } else {
                                    throw new SQLException("Không thể lấy ID sản phẩm đã tạo.");
                                }
                            }
                        }
                    }
                }
            }

            // 4. Xử lý upload ảnh
            processImages(req, conn, productId);

            // 5. Thêm biến thể sản phẩm
            if (productWeightStr != null && !productWeightStr.isEmpty()) {
                float productWeight = Float.parseFloat(productWeightStr);
                String sqlVariant = "INSERT INTO product_variants (idProduct, weight, price, isActive, productDescription, importDate, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
                psVariant = conn.prepareStatement(sqlVariant, PreparedStatement.RETURN_GENERATED_KEYS);
                psVariant.setInt(1, productId);
                psVariant.setFloat(2, productWeight);
                psVariant.setDouble(3, productPrice);
                psVariant.setBoolean(4, true);
                psVariant.setString(5, productDescription);
                psVariant.setDate(6, Date.valueOf(LocalDate.now()));
                psVariant.setInt(7, productQuantity);
                psVariant.executeUpdate();

                // Lấy ID biến thể vừa tạo
                try (ResultSet rsVariant = psVariant.getGeneratedKeys()) {
                    if (rsVariant.next()) {
                        int variantId = rsVariant.getInt(1);

                        // 6. Thêm khuyến mãi nếu có
                        if (salePercentStr != null && !salePercentStr.isEmpty()) {
                            int salePercent = Integer.parseInt(salePercentStr);
                            String sqlSale = "INSERT INTO sales (idVariant, salePercent, saleStartDate, saleEndDate) VALUES (?, ?, ?, ?)";
                            psSale = conn.prepareStatement(sqlSale);
                            psSale.setInt(1, variantId);
                            psSale.setInt(2, salePercent);
                            psSale.setDate(3, Date.valueOf(saleStartDate));
                            psSale.setDate(4, Date.valueOf(saleEndDate));
                            psSale.executeUpdate();
                        }
                    }
                }
            }

            // Commit transaction
            conn.commit();
            resp.sendRedirect("getCategory");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    private void processImages(HttpServletRequest req, Connection conn, int productId) throws IOException, SQLException, ServletException {
        String uploadPath = getServletContext().getRealPath("") + "img";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Kiểm tra xem đã có ảnh tồn tại cho sản phẩm này chưa
        String sqlCheckImages = "SELECT COUNT(*) FROM images WHERE idProduct = ?";
        try (PreparedStatement psCheckImages = conn.prepareStatement(sqlCheckImages)) {
            psCheckImages.setInt(1, productId);
            try (ResultSet rs = psCheckImages.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    // Đã có ảnh, không thêm mới
                    System.out.println("Sản phẩm đã có ảnh. Không thêm mới.");
                    return;
                }
            }
        }

        // Xử lý thêm ảnh nếu chưa tồn tại
        String[] imageFields = {"product_Image1", "product_Image2", "product_Image3", "product_Image4"};
        for (String fieldName : imageFields) {
            Part filePart = req.getPart(fieldName);
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                String filePath = uploadPath + File.separator + uniqueFileName;

                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                }

                String sqlImage = "INSERT INTO images (idProduct, imageData) VALUES (?, ?)";
                try (PreparedStatement psImage = conn.prepareStatement(sqlImage)) {
                    psImage.setInt(1, productId);
                    psImage.setString(2, uniqueFileName);
                    psImage.executeUpdate();
                }
            }
        }
    }

    private float parseFloatSafe(String str, float defaultValue) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private int parseIntSafe(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
