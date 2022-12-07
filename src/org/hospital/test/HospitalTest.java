package org.hospital.test;

import org.hospital.dao.HospitalDao;
import org.hospital.dao.impl.HospitalDaoImpl;
import org.hospital.entity.Hospital;

import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 11:35
 */
public class HospitalTest {
    public static void main(String[] args) {
        HospitalDao hospitalDao = new HospitalDaoImpl();
        List<Hospital> all = hospitalDao.findAll();
        System.out.println("all = " + all);
    }
}
