package Services;

import Dao.ConnDB;
import Dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUser {
    ConnDB dao = new ConnDB();
    UserDao userDao = new UserDao();

    public int check(String email, String pass) throws SQLException {
      return userDao.check(email, pass);
    }

    public static void main(String[] args) throws SQLException {
        ServiceUser s  = new ServiceUser();
        System.out.println(s.check("jane.doe@example.com","password456"));




















    }
}
