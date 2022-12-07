package org.hospital.dao;

import org.hospital.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:15
 * @Description 用户数据访问层
 */
public interface UserDao {

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
     * 添加用户
     *
     * @param user
     * @return
     * @author 吕牧
     */
    int insertUser(User user);

    /**
     * 修改用户信息
     *
     * @param map
     * @return
     * @author 吕牧
     */
    int updateUser(Map<String, Object> map);

    /**
     * 修改用户密码
     *
     * @param map
     * @return
     * @author 吕牧
     */
    int updatePassword(Map<String, Object> map);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     * @Author 吕牧
     */
    User getUserDetailByUserName(String userName);

    /**
     * 查询所有用户
     *
     * @return
     * @Author 吕牧
     */
    List<User> selectAllUser();
}
