package org.hospital.dao.impl;

import org.hospital.dao.UserDao;
import org.hospital.entity.User;
import org.hospital.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:15
 * @Description 用户数据访问层实现类
 */
public class UserDaoImpl implements UserDao {

    @Override
    public boolean isExistPhone(String phone) {
        ResultSet rs = DBUtil.doQuery("select * from user where phone = ?", phone);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistUserName(String username) {
        ResultSet rs = DBUtil.doQuery("select * from user where user_name = ?", username);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User doLogin(String userName, String password) {
        ResultSet rs = DBUtil.doQuery("select * from user where user_name = ? and password = ?", userName, password);
        User user = null;
        try {
            while (rs.next()) {
                user = extractResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int insertUser(User user) {
        return DBUtil.doUpdate("insert into user(user_name, phone, password, create_time, update_time) values(?, ?, ?, now(), now())", user.getUserName(), user.getPhone(), user.getPassword());
    }

    @Override
    public int updateUser(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int updatePassword(Map<String, Object> map) {
        return 0;
    }

    @Override
    public User getUserDetailByUserName(String userName) {
        ResultSet rs = DBUtil.doQuery("select * from user where user_name = ?", userName);
        User user = null;
        try {
            while (rs.next()) {
                user = extractResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        ResultSet rs = DBUtil.doQuery("select * from user");
        try {
            while (rs.next()) {
                users.add(extractResult(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 提取结果集
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private User extractResult(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("user_name"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setSex(rs.getString("sex"));
        user.setAge(rs.getInt("age"));
        user.setAddress(rs.getString("address"));
        user.setCreateTime(rs.getDate("create_time"));
        user.setUpdateTime(rs.getDate("update_time"));
        return user;
    }
}
