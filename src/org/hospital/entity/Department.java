package org.hospital.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/5 23:55
 * @Description 科室实体类
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String departmentName;
    private Integer departmentTypeId;
    private Date createTime;
    private Date updateTime;

    public Department() {
    }

    public Department(Integer id, String departmentName, Integer departmentTypeId, Integer hospitalId, Date createTime, Date updateTime) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentTypeId = departmentTypeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentTypeId() {
        return departmentTypeId;
    }

    public void setDepartmentTypeId(Integer departmentTypeId) {
        this.departmentTypeId = departmentTypeId;
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
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(departmentName, that.departmentName) && Objects.equals(departmentTypeId, that.departmentTypeId) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentName, departmentTypeId, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentTypeId=" + departmentTypeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
