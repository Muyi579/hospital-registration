package org.hospital.test;

import org.hospital.dao.DoctorDao;
import org.hospital.dao.impl.DoctorDaoImpl;
import org.hospital.entity.Doctor;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 11:33
 */
public class DoctorTest {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        List<Doctor> byDepartmentId = doctorDao.findByDepartmentId(1);
        System.out.println("byDepartmentId = " + byDepartmentId);
    }
}
