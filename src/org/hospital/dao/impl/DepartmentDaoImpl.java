package org.hospital.dao.impl;

import org.hospital.dao.DepartmentDao;
import org.hospital.dto.DepartmentDto;
import org.hospital.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:08
 * @Description: 科室信息数据访问层实现类
 */
public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public List<DepartmentDto> findByHospitalId(Integer hospitalId) {
        String sql = "select d.id as departmentId, d.department_name, t.id as departmentTypeId, t.department_type_name, h.id as hospitalId, h.hospital_name from department d" +
                " join department_type t on d.department_type_id = t.id join hospital h on t.hospital_id = h.id where h.id = ?";
        ResultSet rs = DBUtil.doQuery(sql, hospitalId);
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        try {
            List<Map<String, Object>> listMap = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> map = new java.util.HashMap<>();
                map.put("departmentId", rs.getInt("departmentId"));
                map.put("departmentName", rs.getString("department_name"));
                map.put("departmentTypeId", rs.getInt("departmentTypeId"));
                map.put("departmentTypeName", rs.getString("department_type_name"));
                map.put("hospitalId", rs.getInt("hospitalId"));
                map.put("hospitalName", rs.getString("hospital_name"));
                listMap.add(map);
            }
            Map<Object, List<Map<String, Object>>> objectListMap = listMap.stream().collect(Collectors.groupingBy(map -> map.get("hospitalId")));
            for (Object key : objectListMap.keySet()) {
                DepartmentDto departmentDto = new DepartmentDto();
                departmentDto.setHospitalId((Integer) key);
                departmentDto.setHospitalName((String) objectListMap.get(key).get(0).get("hospitalName"));
                Map<Object, List<Map<String, Object>>> departmentTypeMap = objectListMap.get(key).stream().collect(Collectors.groupingBy(map -> map.get("departmentTypeId")));
                List<DepartmentDto.DepartmentTypeDto> departmentTypes = new ArrayList<>();
                for (Object departmentTypeKey : departmentTypeMap.keySet()) {
                    DepartmentDto.DepartmentTypeDto departmentTypeDto = new DepartmentDto.DepartmentTypeDto();
                    departmentTypeDto.setDepartmentTypeId((Integer) departmentTypeKey);
                    departmentTypeDto.setDepartmentTypeName((String) departmentTypeMap.get(departmentTypeKey).get(0).get("departmentTypeName"));

                    List<DepartmentDto.DepartmentTypeDto.DepartmentInfoDto> departmentInfoDtos = departmentTypeMap.get(departmentTypeKey).stream().map(map -> {
                        DepartmentDto.DepartmentTypeDto.DepartmentInfoDto departmentInfoDto = new DepartmentDto.DepartmentTypeDto.DepartmentInfoDto();
                        departmentInfoDto.setDepartmentId((Integer) map.get("departmentId"));
                        departmentInfoDto.setDepartmentName((String) map.get("departmentName"));
                        return departmentInfoDto;
                    }).collect(Collectors.toList());
                    departmentTypeDto.setDepartmentList(departmentInfoDtos);

                    departmentTypes.add(departmentTypeDto);
                }
                departmentDto.setDepartmentTypeDtoList(departmentTypes);
                departmentDtos.add(departmentDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departmentDtos;
    }
}
