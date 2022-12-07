package org.hospital.test;

import org.hospital.dao.DepartmentDao;
import org.hospital.dao.impl.DepartmentDaoImpl;
import org.hospital.dto.DepartmentDto;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 11:27
 */
public class DepartmentTest {

    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        List<DepartmentDto> byHospitalId = departmentDao.findByHospitalId(1);
        System.out.println("byHospitalId = " + byHospitalId);
    }
}
