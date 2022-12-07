package org.hospital.test;

import org.hospital.dao.UserDao;
import org.hospital.dao.impl.UserDaoImpl;
import org.hospital.entity.User;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:53
 */
public class UserTest {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        // 测试查询所有用户
        List<User> users = userDao.selectAllUser();
        System.out.println("users = " + users);

    }
}
