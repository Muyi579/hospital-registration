package org.hospital.servlet;

import com.google.gson.Gson;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.DoctorService;
import org.hospital.service.impl.DoctorServiceImpl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:38
 * @Description: 医生servlet
 */
@WebServlet("/doctorServlet")
public class DoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DoctorService doctorService = new DoctorServiceImpl();

    public DoctorServlet() {
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        String op = req.getParameter("op");
        ResultMsg resultMsg = null;
        Gson gson = new Gson();
        if ("findByDepartmentId".equals(op)) {
            resultMsg = this.doFindByDepartmentId(req, res);
        }
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().write(gson.toJson(resultMsg));
    }

    private ResultMsg doFindByDepartmentId(ServletRequest req, ServletResponse res) {
        return doctorService.findByDepartmentId(Integer.parseInt(req.getParameter("departmentId")));
    }
}
