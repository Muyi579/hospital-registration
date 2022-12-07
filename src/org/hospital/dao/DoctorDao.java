package org.hospital.dao;

import org.hospital.entity.Doctor;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:10
 * @Description: 医生信息数据访问层
 */
public interface DoctorDao {
    /**
     * 查询该科室下的坐诊医生
     *
     * @param departmentId
     * @return
     * @auther 吕牧
     */
    List<Doctor> findByDepartmentId(Integer departmentId);
}
