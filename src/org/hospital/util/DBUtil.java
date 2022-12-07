package org.hospital.util;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:20
 * @Description 数据库工具类
 */
public class DBUtil {

    /**
     * @author DBUtil连接数据库 全局使用常量driver，driverManager
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_appointment?useUnicode=true&characterEncoding=UTF-8&useOldAliasMetadataBehavior=true&useAffectedRows=true&serverTimezone=GMT";
    private static final String USRE = "root";
    private static final String PASSWORD = "root";

    /**
     * @return connection获取连接数据库对象
     * @author DBUtil连接数据库
     */
    public static Connection connection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USRE, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }

    /**
     * @return ResultSet返回结果集
     * @author 查询数据库操作
     */
    public static ResultSet doQuery(String sql, Object... objects) {
        Connection conn = connection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        CachedRowSet cach = null; // 构建缓存结果集
        try {

            pr = conn.prepareStatement(sql); // 预编译方法输入SQL文
            for (int i = 0; i < objects.length; i++) {
                pr.setObject(i + 1, objects[i]); // 为占位符赋值
            }
            rs = pr.executeQuery(); // 运行SQL文
            cach = new CachedRowSetImpl(); // 缓存结果集接口指向实现类
            cach.populate(rs); // 添加结果集

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源回收
            finaly(rs, pr, conn);
        }

        return cach; // 返回缓存结果集
    }

    /**
     * @return ResultSet返回int类型匹配行数
     * @author 增删改数据库操作
     */
    public static int doUpdate(String sql, Object... objects) {
        Connection conn = connection();
        PreparedStatement pr = null;
        int rs = 0;
        try {
            pr = conn.prepareStatement(sql); // 预编译SQl文
            for (int i = 0; i < objects.length; i++) {
                pr.setObject(i + 1, objects[i]); // 占位符赋值
            }
            rs = pr.executeUpdate(); // 运行增删改方法
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // 关闭资源先开后关
            finaly(null, pr, conn);
        }
        return rs; // 返回匹配行数

    }

    /**
     * @author 关闭资源
     */
    public static void finaly(ResultSet rs, PreparedStatement pr, Connection conn) {
        try {
            // 判断是否为空，不为空关闭资源
            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
