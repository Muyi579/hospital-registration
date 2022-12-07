package org.hospital.service;

import org.hospital.entity.User;
import org.hospital.pojo.ResultMsg;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:16
 * @Description 用户业务层
 */
public interface UserService {
    /**
     * 判断手机号是否重复
     *
     * @param phone
     * @return
     * @author 吕牧
     */
    boolean isExistPhone(String phone);

    /**
     * 判断用户名是否重复
     *
     * @param username
     * @return
     * @author 吕牧
     */
    boolean isExistUserName(String username);

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     * @author 吕牧
     */
    User doLogin(String userName, String password);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     * @Author 吕牧
     */
    ResultMsg getUserDetailByUserName(String userName);
}
