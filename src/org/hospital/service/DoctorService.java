package org.hospital.service;

import org.hospital.pojo.ResultMsg;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:36
 * @Description: 医生服务
 */
public interface DoctorService {

    /**
     * 查询该科室下的坐诊医生
     *
     * @param departmentId
     * @return
     * @auther 吕牧
     */
    ResultMsg findByDepartmentId(Integer departmentId);
}
