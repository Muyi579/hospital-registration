package org.hospital.servlet;

import com.google.gson.Gson;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.UserService;
import org.hospital.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 19:59
 * @Description: 用户servlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    public UserServlet() {
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String op = req.getParameter("op");
        ResultMsg resultMsg = null;
        Gson gson = new Gson();

        if ("getUserDetailByUserName".equals(op)) {
            resultMsg = this.doGetUserDetailByUserName(req, resp);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(gson.toJson(resultMsg));
    }

    private ResultMsg doGetUserDetailByUserName(HttpServletRequest req, HttpServletResponse resp) {
        return userService.getUserDetailByUserName(req.getParameter("userName"));
    }
}
