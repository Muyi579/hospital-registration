package org.hospital.servlet;

import com.google.gson.Gson;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.HospitalService;
import org.hospital.service.impl.HospitalServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:42
 * @Description: 医院servlet
 */
@WebServlet("/hospitalServlet")
public class HospitalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HospitalService hospitalService = new HospitalServiceImpl();

    public HospitalServlet() {
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        String op = req.getParameter("op");
        ResultMsg resultMsg = null;
        Gson gson = new Gson();
        if ("findAll".equals(op)) {
            resultMsg = this.doFindAll(req, res);
        }
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().write(gson.toJson(resultMsg));
    }

    private ResultMsg doFindAll(ServletRequest req, ServletResponse res) {
        return hospitalService.findAll();
    }


}
