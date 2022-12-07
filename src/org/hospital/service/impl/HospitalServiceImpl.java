package org.hospital.service.impl;

import org.hospital.dao.HospitalDao;
import org.hospital.dao.impl.HospitalDaoImpl;
import org.hospital.entity.Hospital;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.HospitalService;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 13:41
 * @Description: 医院服务service实现类
 */
public class HospitalServiceImpl implements HospitalService {
    private HospitalDao hospitalDao = new HospitalDaoImpl();

    @Override
    public ResultMsg findAll() {
        List<Hospital> result = hospitalDao.findAll();
        if (result != null && result.size() > 0) {
            return new ResultMsg(ResultMsg.SUCCESS, result, "查询成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "查询失败");
    }
}
