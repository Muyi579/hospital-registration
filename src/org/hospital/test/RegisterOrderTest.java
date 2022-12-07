package org.hospital.test;

import org.hospital.dao.RegisterOrderDao;
import org.hospital.dao.impl.RegisterOrderDaoImpl;
import org.hospital.dto.RegisterOrderDto;
import org.hospital.entity.RegisterOrder;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 11:37
 */
public class RegisterOrderTest {
    public static void main(String[] args) {
        RegisterOrderDao registerOrderDao = new RegisterOrderDaoImpl();
        RegisterOrder registerOrder = new RegisterOrder();
        registerOrder.setRegisterDepartmentId(15);
        List<RegisterOrderDto> registerOrder1 = registerOrderDao.selectRegisterOrderByCondition(registerOrder);
        System.out.println("registerOrderDao = " + registerOrder1);
        System.out.println(registerOrderDao.selectRegisterOrderByOrderId(15));
    }
}
