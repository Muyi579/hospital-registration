package org.hospital.dao;

import org.hospital.entity.Hospital;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:10
 * @Description: 医院信息数据访问层
 */
public interface HospitalDao {
    /**
     * 查询所有医院信息
     *
     * @return
     * @auther 吕牧
     */
    List<Hospital> findAll();
}
