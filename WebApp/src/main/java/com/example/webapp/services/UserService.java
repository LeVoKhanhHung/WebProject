package com.example.webapp.services;

import com.example.webapp.daos.UserDAO;
import com.example.webapp.models.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Lấy danh sách tất cả người dùng
    public static List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Lấy thông tin người dùng theo ID
    public static User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    // Thêm người dùng mới
    public static boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    // Cập nhật thông tin người dùng
    public static boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    // Xóa người dùng
    public static boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }
}

