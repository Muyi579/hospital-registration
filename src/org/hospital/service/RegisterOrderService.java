package org.hospital.service;

import org.hospital.entity.RegisterOrder;
import org.hospital.pojo.ResultMsg;

import java.util.Date;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 0:02
 * @Description 预约挂号服务接口
 */
public interface RegisterOrderService {

    /**
     * 新增预约挂号订单
     *
     * @param registerOrder
     * @return
     * @author 吕牧
     */
    ResultMsg insertRegisterOrder(RegisterOrder registerOrder);

    /**
     * 根据订单号查询订单
     *
     * @param id
     * @return
     * @author 吕牧
     */
    ResultMsg selectRegisterOrderByOrderId(Integer id);

    /**
     * 根据订单id修改预约日期
     *
     * @param id
     * @param registerDate
     * @return
     * @author 吕牧
     */
    ResultMsg updateRegisterOrderDateByOrderId(Integer id, Date registerDate);

    /**
     * 取消预约
     *
     * @param id
     * @return
     * @author 吕牧
     */
    ResultMsg cancelRegisterOrder(Integer id);

    /**
     * 完成就诊
     *
     * @param id
     * @return
     * @author 吕牧
     */
    ResultMsg finishRegisterOrder(Integer id);

    /**
     * 多条件查询订单
     *
     * @param registerOrder
     * @return
     * @author 吕牧
     */
    ResultMsg selectRegisterOrderByCondition(RegisterOrder registerOrder);
}
