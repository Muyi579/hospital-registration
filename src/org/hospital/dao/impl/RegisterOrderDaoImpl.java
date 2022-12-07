package org.hospital.dao.impl;

import org.hospital.dao.RegisterOrderDao;
import org.hospital.dto.RegisterOrderDto;
import org.hospital.entity.RegisterOrder;
import org.hospital.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 0:02
 * @Description 挂号订单数据访问层实现类
 */
public class RegisterOrderDaoImpl implements RegisterOrderDao {

    @Override
    public int insertRegisterOrder(RegisterOrder registerOrder) {
        return DBUtil.doUpdate("insert into register_order(user_id, register_time, status, doctor_id, register_department_id, create_time, update_time)" +
                        " value(?,?,0,?,?,now(), now())", registerOrder.getUserId(), registerOrder.getRegisterTime(), registerOrder.getDoctorId(),
                registerOrder.getRegisterDepartmentId());
    }

    @Override
    public RegisterOrderDto selectRegisterOrderByOrderId(Integer id) {
        ResultSet rs = DBUtil.doQuery("select r.id, u.user_name, d.doctor_name, h.hospital_name, dep.department_name, r.status, dept.department_type_name, " +
                "r.register_time from register_order r join user u on r.user_id = u.id join doctor d on r.doctor_id = d.id " +
                "join department dep on r.register_department_id = dep.id join department_type dept on dep.department_type_id = dept.id join hospital h on dept.hospital_id = h.id where r.id = ?", id);
        RegisterOrderDto registerOrder = null;
        try {
            while (rs.next()) {
                registerOrder = extractResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registerOrder;
    }

    @Override
    public int updateRegisterOrderDateByOrderId(Integer id, Date registerDate) {
        return DBUtil.doUpdate("update register_order set register_time = ?, update_time = now() where id = ?", registerDate, id);
    }

    @Override
    public int cancelRegisterOrder(Integer id) {
        return DBUtil.doUpdate("update register_order set status = 1, update_time = now() where id = ?", id);
    }

    @Override
    public int finishRegisterOrder(Integer id) {
        return DBUtil.doUpdate("update register_order set status = 2, update_time = now() where id = ?", id);
    }

    @Override
    public List<RegisterOrderDto> selectRegisterOrderByCondition(RegisterOrder registerOrder) {
        StringBuilder sb = new StringBuilder("select r.id, u.user_name, d.doctor_name, h.hospital_name, dep.department_name, r.status, dept.department_type_name, " +
                "r.register_time from register_order r join user u on r.user_id = u.id join doctor d on r.doctor_id = d.id join department " +
                "dep on r.register_department_id = dep.id join department_type dept on dep.department_type_id = dept.id join hospital h on dept.hospital_id = h.id where 1 = 1");
        if (registerOrder.getDoctorId() != null) {
            sb.append(" and doctor_id = ").append(registerOrder.getDoctorId());
        }
        if (registerOrder.getRegisterDepartmentId() != null) {
            sb.append(" and register_department_id = ").append(registerOrder.getRegisterDepartmentId());
        }
        if (registerOrder.getRegisterTime() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            sb.append(" and date(register_time) = '").append(simpleDateFormat.format(registerOrder.getRegisterTime())).append("'");
        }
        if (registerOrder.getStatus() != null) {
            sb.append(" and status = ").append(registerOrder.getStatus());
        }
        if (registerOrder.getHospitalId() != null) {
            sb.append(" and h.id = ").append(registerOrder.getHospitalId());
        }
        // 排序
        sb.append(" order by r.create_time desc");
        ResultSet rs = DBUtil.doQuery(sb.toString());
        List<RegisterOrderDto> registerOrderList = new ArrayList<>();
        try {
            while (rs.next()) {
                registerOrderList.add(extractResult(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registerOrderList;
    }

    /**
     * 提取结果集
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private RegisterOrderDto extractResult(ResultSet rs) throws SQLException {
        RegisterOrderDto registerOrder = new RegisterOrderDto();
        registerOrder.setId(rs.getInt("id"));
        registerOrder.setUserName(rs.getString("user_name"));
        registerOrder.setRegisterTime(rs.getString("register_time"));
        String status = rs.getString("status");
        if ("0".equals(status)) {
            registerOrder.setStatus("未就诊");
        } else if ("1".equals(status)) {
            registerOrder.setStatus("已取消");
        } else if ("2".equals(status)) {
            registerOrder.setStatus("已就诊");
        }
        registerOrder.setDoctorName(rs.getString("doctor_name"));
        registerOrder.setDepartmentName(rs.getString("department_name"));
        registerOrder.setDepartmentTypeName(rs.getString("department_type_name"));
        registerOrder.setHospitalName(rs.getString("hospital_name"));
        return registerOrder;
    }
}
