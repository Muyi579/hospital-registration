package org.hospital.service.impl;

import org.hospital.dao.UserDao;
import org.hospital.dao.impl.UserDaoImpl;
import org.hospital.entity.User;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.UserService;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:17
 * @Description 用户业务层实现类
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean isExistPhone(String phone) {
        if (null == phone || "".equals(phone)) return true;
        return userDao.isExistPhone(phone);
    }

    @Override
    public boolean isExistUserName(String username) {
        if (null == username || "".equals(username)) return true;
        return userDao.isExistPhone(username);
    }

    @Override
    public User doLogin(String userName, String password) {
        return userDao.doLogin(userName, password);
    }

    @Override
    public int register(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public ResultMsg getUserDetailByUserName(String userName) {
        User user = userDao.getUserDetailByUserName(userName);
        if (null == user) {
            return new ResultMsg(ResultMsg.NO_API_ERROR, new User(), "用户不存在");
        }
        return new ResultMsg(ResultMsg.SUCCESS, user, "查询成功");
    }


}
