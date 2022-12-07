package org.hospital.service;

import org.hospital.pojo.ResultMsg;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:33
 * @Description: 科室服务接口
 */
public interface DepartmentService {
    /**
     * 查询该医院下所有科室信息
     *
     * @return
     * @auther 吕牧
     */
    ResultMsg findByHospitalId(Integer hospitalId);
}
