package org.hospital.servlet;

import com.google.gson.Gson;
import org.hospital.entity.User;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.UserService;
import org.hospital.service.impl.UserServiceImpl;
import org.hospital.util.MD5Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:18
 * @Description 登录注册
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    private Gson gson = new Gson();

    public LoginServlet() {
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String op = req.getParameter("op");
        ResultMsg resultMsg = null;
        if ("login".equals(op)) {
            resultMsg = this.doLogin(req, resp);
        } else if ("register".equals(op)) {
            resultMsg = this.doRegister(req, resp);
        } else if ("checked".equals(op)) {
//            resultMsg = this.doChecked(req, resp);
        } else if ("isLogin".equals(op)) {
//            resultMsg = this.isLogin(req, resp);
        } else if ("logout".equals(op)) {
//            resultMsg = this.doLogout(req, resp);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(gson.toJson(resultMsg));
    }

    private ResultMsg doRegister(HttpServletRequest req, HttpServletResponse resp) {
        String phone = req.getParameter("phone");
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        String md5Password = MD5Util.getMd5(password);
        User user = new User();
        user.setPhone(phone);
        user.setUserName(username);
        user.setPassword(md5Password);
        ResultMsg resultMsg = new ResultMsg(ResultMsg.SUCCESS, null, "注册成功");
        if (this.userService.isExistPhone(phone)) {
            resultMsg.setCode(ResultMsg.NO_API_ERROR);
            resultMsg.setMsg("该手机号已被注册");
        } else if (this.userService.isExistUserName(username)) {
            resultMsg.setCode(ResultMsg.NO_API_ERROR);
            resultMsg.setMsg("该用户名已被注册");
        } else {
            int register = this.userService.register(user);
            if (register == 0) {
                resultMsg.setCode(ResultMsg.NO_API_ERROR);
                resultMsg.setMsg("注册失败");
            }
        }
        return resultMsg;
    }

    private ResultMsg doLogin(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String md5Password = MD5Util.getMd5(password);
        User user = this.userService.doLogin(userName, md5Password);
        ResultMsg resultMsg = new ResultMsg(ResultMsg.SUCCESS, null, "登录成功");
        if (null != user) {
//            req.getSession().setAttribute("isLogin", true);
            resultMsg.setData(user);
        } else {
            resultMsg.setCode(ResultMsg.NO_API_ERROR);
            resultMsg.setMsg("用户名或密码错误");
        }
        return resultMsg;
    }

    private ResultMsg isLogin(HttpServletRequest req, HttpServletResponse resp) {
        Object isLogin = req.getSession().getAttribute("isLogin");
        if (null == isLogin) {
            return new ResultMsg(ResultMsg.NO_API_ERROR, null, "");
        }
        return new ResultMsg(ResultMsg.SUCCESS, null, "");
    }

    private ResultMsg doLogout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("isLogin");
        return new ResultMsg(ResultMsg.SUCCESS, null, "退出成功");
    }

    private ResultMsg doChecked(HttpServletRequest req, HttpServletResponse resp) {
        String phone = req.getParameter("phone");
        String username = req.getParameter("username");
        ResultMsg resultMsg = new ResultMsg(ResultMsg.SUCCESS, null, "该手机号和用户名可用");
        if (this.userService.isExistPhone(phone)) {
            resultMsg.setCode(ResultMsg.NO_API_ERROR);
            resultMsg.setMsg("该手机号已被注册");
        } else if (this.userService.isExistUserName(username)) {
            resultMsg.setCode(ResultMsg.NO_API_ERROR);
            resultMsg.setMsg("该用户名已被注册");
        }
        return resultMsg;
    }
}
