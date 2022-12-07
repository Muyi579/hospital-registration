package org.hospital.servlet;

import com.google.gson.Gson;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.DepartmentService;
import org.hospital.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:29
 * @Description: 科室servlet
 */
@WebServlet("/departmentServlet")
public class DepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DepartmentService departmentService = new DepartmentServiceImpl();

    public DepartmentServlet() {
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        String op = req.getParameter("op");
        ResultMsg resultMsg = null;
        Gson gson = new Gson();
        if ("findByHospitalId".equals(op)) {
            resultMsg = this.doFindByHospitalId(req, res);
        }
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().write(gson.toJson(resultMsg));
    }

    private ResultMsg doFindByHospitalId(ServletRequest req, ServletResponse res) {
        return departmentService.findByHospitalId(Integer.parseInt(req.getParameter("hospitalId")));
    }
}
