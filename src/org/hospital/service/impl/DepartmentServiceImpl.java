package org.hospital.service.impl;

import org.hospital.dao.DepartmentDao;
import org.hospital.dao.impl.DepartmentDaoImpl;
import org.hospital.dto.DepartmentDto;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.DepartmentService;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:33
 * @Description: 科室服务接口实现类
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public ResultMsg findByHospitalId(Integer hospitalId) {
        List<DepartmentDto> departmentDtos = departmentDao.findByHospitalId(hospitalId);
        if (null != departmentDtos) {
            return new ResultMsg(ResultMsg.SUCCESS, departmentDtos, "查询成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "查询失败");
    }
}
