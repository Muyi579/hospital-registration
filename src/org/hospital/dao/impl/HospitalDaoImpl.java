package org.hospital.dao.impl;

import org.hospital.dao.HospitalDao;
import org.hospital.entity.Hospital;
import org.hospital.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:10
 * @Description 医院信息数据访问层实现类
 */
public class HospitalDaoImpl implements HospitalDao {
    @Override
    public List<Hospital> findAll() {
        ResultSet rs = DBUtil.doQuery("select * from hospital");
        List<Hospital> hospitals = new ArrayList<>();
        try {
            while (rs.next()) {
                Hospital hospital = new Hospital();
                hospital.setId(rs.getInt("id"));
                hospital.setHospitalName(rs.getString("hospital_name"));
                hospital.setHospitalAddress(rs.getString("hospital_address"));
                hospital.setHospitalTell(rs.getString("hospital_tell"));
                hospital.setHospitalDescription(rs.getString("hospital_description"));
                hospital.setCreateTime(rs.getDate("create_time"));
                hospital.setUpdateTime(rs.getDate("update_time"));
                hospitals.add(hospital);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospitals;
    }
}
