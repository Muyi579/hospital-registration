package org.hospital.dao;

import org.hospital.dto.RegisterOrderDto;
import org.hospital.entity.RegisterOrder;

import java.util.Date;
import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 0:01
 * @Description 挂号订单数据访问层
 */
public interface RegisterOrderDao {

    /**
     * 新增预约挂号订单
     *
     * @param registerOrder
     * @return
     * @author 吕牧
     */
    int insertRegisterOrder(RegisterOrder registerOrder);

    /**
     * 根据订单号查询订单
     *
     * @param id
     * @return
     * @author 吕牧
     */
    RegisterOrderDto selectRegisterOrderByOrderId(Integer id);

    /**
     * 根据订单id修改预约日期
     *
     * @param id
     * @param registerDate
     * @return
     * @author 吕牧
     */
    int updateRegisterOrderDateByOrderId(Integer id, Date registerDate);

    /**
     * 取消预约
     *
     * @param id
     * @return
     * @author 吕牧
     */
    int cancelRegisterOrder(Integer id);

    /**
     * 完成就诊
     *
     * @param id
     * @return
     * @author 吕牧
     */
    int finishRegisterOrder(Integer id);

    /**
     * 多条件查询订单
     *
     * @param registerOrder
     * @return
     * @author 吕牧
     */
    List<RegisterOrderDto> selectRegisterOrderByCondition(RegisterOrder registerOrder);

}
