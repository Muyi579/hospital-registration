package org.hospital.service.impl;

import org.hospital.dao.DoctorDao;
import org.hospital.dao.impl.DoctorDaoImpl;
import org.hospital.entity.Doctor;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.DoctorService;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:36
 * @Description: 医生服务service实现类
 */
public class DoctorServiceImpl implements DoctorService {
    private DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public ResultMsg findByDepartmentId(Integer departmentId) {
        List<Doctor> doctors = doctorDao.findByDepartmentId(departmentId);
        if (doctors != null && doctors.size() > 0) {
            return new ResultMsg(ResultMsg.SUCCESS, doctors, "查询成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "查询失败");
    }
}
