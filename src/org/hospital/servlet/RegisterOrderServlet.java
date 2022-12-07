package org.hospital.servlet;

import com.google.gson.Gson;
import org.hospital.entity.RegisterOrder;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.RegisterOrderService;
import org.hospital.service.impl.RegisterOrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 0:03
 * @Description 预约挂号servlet
 */
@WebServlet("/registerOrderServlet")
public class RegisterOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RegisterOrderService registerOrderService = new RegisterOrderServiceImpl();

    public RegisterOrderServlet() {
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String op = req.getParameter("op");
        Gson gson = new Gson();
        ResultMsg resultMsg = null;
        if ("query".equals(op)) {
            resultMsg = this.doQueryOrder(req, resp);
        } else if ("cancel".equals(op)) {
            resultMsg = this.doCancelOrder(req, resp);
        } else if ("finish".equals(op)) {
            resultMsg = this.doFinishOrder(req, resp);
        } else if ("queryDetail".equals(op)) {
            resultMsg = this.doQueryOrderDetail(req, resp);
        } else if ("insert".equals(op)) {
            resultMsg = this.doInsertOrder(req, resp);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(gson.toJson(resultMsg));
    }

    private ResultMsg doQueryOrder(HttpServletRequest req, HttpServletResponse resp) {
        RegisterOrder registerOrder = new RegisterOrder();
        String doctorId = req.getParameter("doctorId");
        if (doctorId != null && !"".equals(doctorId)) {
            registerOrder.setDoctorId(Integer.parseInt(doctorId));
        }
        String departmentId = req.getParameter("departmentId");
        if (departmentId != null && !"".equals(departmentId)) {
            registerOrder.setRegisterDepartmentId(Integer.parseInt(departmentId));
        }
        String status = req.getParameter("status");
        if (status != null && !"".equals(status)) {
            registerOrder.setStatus(status);
        }
        String registerTime = req.getParameter("registerTime");
        if (registerTime != null && !"".equals(registerTime)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                registerOrder.setRegisterTime(simpleDateFormat.parse(registerTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String hospitalId = req.getParameter("hospitalId");
        if (hospitalId != null && !"".equals(hospitalId)) {
            registerOrder.setHospitalId(Integer.parseInt(hospitalId));
        }
        return registerOrderService.selectRegisterOrderByCondition(registerOrder);
    }

    private ResultMsg doInsertOrder(HttpServletRequest req, HttpServletResponse resp) {
        RegisterOrder registerOrder = new RegisterOrder();
        String doctorId = req.getParameter("doctorId");
        String departmentId = req.getParameter("departmentId");
        String userId = req.getParameter("userId");
        String registerTime = req.getParameter("registerTime");
        registerOrder.setDoctorId(Integer.parseInt(doctorId));
        registerOrder.setRegisterDepartmentId(Integer.parseInt(departmentId));
        registerOrder.setUserId(Integer.parseInt(userId));
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            registerOrder.setRegisterTime(simpleDateFormat.parse(registerTime.replace("T", " ")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return registerOrderService.insertRegisterOrder(registerOrder);
    }

    private ResultMsg doQueryOrderDetail(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("id");
        return registerOrderService.selectRegisterOrderByOrderId(Integer.parseInt(orderId));
    }

    private ResultMsg doCancelOrder(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("id");
        return registerOrderService.cancelRegisterOrder(Integer.parseInt(orderId));
    }

    private ResultMsg doFinishOrder(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("id");
        return registerOrderService.finishRegisterOrder(Integer.parseInt(orderId));
    }
}
