package org.hospital.dao.impl;

import org.hospital.dao.DoctorDao;
import org.hospital.entity.Doctor;
import org.hospital.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:10
 * @Description: 医生信息数据访问层实现类
 */
public class DoctorDaoImpl implements DoctorDao {

    @Override
    public List<Doctor> findByDepartmentId(Integer departmentId) {
        ResultSet rs = DBUtil.doQuery("select d.* from doctor d inner join doctor_rela r on d.id = r.doctor_id where r.department_id = ?", departmentId);
        List<Doctor> doctors = new ArrayList<>();
        try {
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setDoctorName(rs.getString("doctor_name"));
                doctor.setDoctorDescription(rs.getString("doctor_description"));
                doctor.setCreateTime(rs.getDate("create_time"));
                doctor.setUpdateTime(rs.getDate("update_time"));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
