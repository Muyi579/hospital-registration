package org.hospital.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/5 23:54
 * @Description 科室类型实体类
 */
public class DepartmentType implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String departmentTypeName;
    private Integer hospitalId;
    private Date createTime;
    private Date updateTime;

    public DepartmentType() {
    }

    public DepartmentType(Integer id, String departmentTypeName, Integer hospitalId, Date createTime, Date updateTime) {
        this.id = id;
        this.departmentTypeName = departmentTypeName;
        this.hospitalId = hospitalId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentTypeName() {
        return departmentTypeName;
    }

    public void setDepartmentTypeName(String departmentTypeName) {
        this.departmentTypeName = departmentTypeName;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentType that = (DepartmentType) o;
        return Objects.equals(id, that.id) && Objects.equals(departmentTypeName, that.departmentTypeName) && Objects.equals(hospitalId, that.hospitalId) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentTypeName, hospitalId, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "DepartmentType{" +
                "id=" + id +
                ", departmentTypeName='" + departmentTypeName + '\'' +
                ", hospitalId=" + hospitalId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
