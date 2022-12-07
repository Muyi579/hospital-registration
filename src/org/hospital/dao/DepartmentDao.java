package org.hospital.dao;

import org.hospital.dto.DepartmentDto;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:08
 * @Description: 科室信息数据访问层
 */
public interface DepartmentDao {
    /**
     * 查询该医院下所有科室信息
     *
     * @return
     * @auther 吕牧
     */
    List<DepartmentDto> findByHospitalId(Integer hospitalId);
}
