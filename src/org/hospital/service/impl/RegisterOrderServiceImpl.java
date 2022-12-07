package org.hospital.service.impl;

import org.hospital.dao.RegisterOrderDao;
import org.hospital.dao.impl.RegisterOrderDaoImpl;
import org.hospital.dto.RegisterOrderDto;
import org.hospital.entity.RegisterOrder;
import org.hospital.pojo.ResultMsg;
import org.hospital.service.RegisterOrderService;

import java.util.Date;
import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 0:03
 * @Description 预约挂号服务实现类
 */
public class RegisterOrderServiceImpl implements RegisterOrderService {

    private RegisterOrderDao registerOrderDao = new RegisterOrderDaoImpl();

    @Override
    public ResultMsg insertRegisterOrder(RegisterOrder registerOrder) {
        int rows = registerOrderDao.insertRegisterOrder(registerOrder);
        if (rows > 0) {
            return new ResultMsg(ResultMsg.SUCCESS, null, "预约成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "预约失败");
    }

    @Override
    public ResultMsg selectRegisterOrderByOrderId(Integer id) {
        RegisterOrderDto result = registerOrderDao.selectRegisterOrderByOrderId(id);
        if (null != result) {
            return new ResultMsg(ResultMsg.SUCCESS, result, "查询成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "查询失败 暂无预约信息");
    }

    @Override
    public ResultMsg updateRegisterOrderDateByOrderId(Integer id, Date registerDate) {
        int rows = registerOrderDao.updateRegisterOrderDateByOrderId(id, registerDate);
        if (rows > 0) {
            return new ResultMsg(ResultMsg.SUCCESS, null, "修改成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "修改失败");
    }

    @Override
    public ResultMsg cancelRegisterOrder(Integer id) {
        int rows = registerOrderDao.cancelRegisterOrder(id);
        if (rows > 0) {
            return new ResultMsg(ResultMsg.SUCCESS, null, "取消成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "取消失败");
    }

    @Override
    public ResultMsg finishRegisterOrder(Integer id) {
        int rows = registerOrderDao.finishRegisterOrder(id);
        if (rows > 0) {
            return new ResultMsg(ResultMsg.SUCCESS, null, "完成就诊");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "完成就诊失败");
    }

    @Override
    public ResultMsg selectRegisterOrderByCondition(RegisterOrder registerOrder) {
        List<RegisterOrderDto> result = registerOrderDao.selectRegisterOrderByCondition(registerOrder);
        if (null != result) {
            return new ResultMsg(ResultMsg.SUCCESS, result, "查询成功");
        }
        return new ResultMsg(ResultMsg.NO_API_ERROR, null, "查询失败 暂无预约信息");
    }
}
