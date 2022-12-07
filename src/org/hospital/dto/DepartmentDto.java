package org.hospital.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 10:08
 * @Description: 科室信息数据传输层
 */
public class DepartmentDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer hospitalId;

    private String hospitalName;

    private List<DepartmentTypeDto> departmentTypeDtoList;

    public static class DepartmentTypeDto implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer departmentTypeId;
        private String departmentTypeName;
        private List<DepartmentInfoDto> departmentList;

        public Integer getDepartmentTypeId() {
            return departmentTypeId;
        }

        public void setDepartmentTypeId(Integer departmentTypeId) {
            this.departmentTypeId = departmentTypeId;
        }

        public String getDepartmentTypeName() {
            return departmentTypeName;
        }

        public void setDepartmentTypeName(String departmentTypeName) {
            this.departmentTypeName = departmentTypeName;
        }

        public List<DepartmentInfoDto> getDepartmentList() {
            return departmentList;
        }

        public void setDepartmentList(List<DepartmentInfoDto> departmentList) {
            this.departmentList = departmentList;
        }

        public static class DepartmentInfoDto implements Serializable {
            private static final long serialVersionUID = 1L;
            private Integer departmentId;
            private String departmentName;

            public Integer getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(Integer departmentId) {
                this.departmentId = departmentId;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }
        }
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public List<DepartmentTypeDto> getDepartmentTypeDtoList() {
        return departmentTypeDtoList;
    }

    public void setDepartmentTypeDtoList(List<DepartmentTypeDto> departmentTypeDtoList) {
        this.departmentTypeDtoList = departmentTypeDtoList;
    }
}
