package org.hospital.service;

import org.hospital.pojo.ResultMsg;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:40
 * @Description: 医院服务service接口
 */
public interface HospitalService {
    /**
     * 查询所有医院信息
     *
     * @return
     * @auther 吕牧
     */
    ResultMsg findAll();
}
