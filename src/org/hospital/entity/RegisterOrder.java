package org.hospital.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/5 23:58
 * @Description 挂号订单实体类
 */
public class RegisterOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer registerDepartmentId;
    private Integer doctorId;
    private Integer userId;
    private String status;
    private Date registerTime;
    private Date createTime;
    private Date updateTime;

    private Integer hospitalId;

    public RegisterOrder() {
    }

    public RegisterOrder(Integer id, Integer registerDepartmentId, Integer doctorId, Integer userId, Date registerTime, Date createTime, Date updateTime, String status) {
        this.id = id;
        this.registerDepartmentId = registerDepartmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.registerTime = registerTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegisterDepartmentId() {
        return registerDepartmentId;
    }

    public void setRegisterDepartmentId(Integer registerDepartmentId) {
        this.registerDepartmentId = registerDepartmentId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterOrder that = (RegisterOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(registerDepartmentId, that.registerDepartmentId) && Objects.equals(doctorId, that.doctorId) && Objects.equals(userId, that.userId) && Objects.equals(registerTime, that.registerTime) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registerDepartmentId, doctorId, userId, registerTime, createTime, updateTime, status);
    }

    @Override
    public String toString() {
        return "RegisterOrder{" +
                "id=" + id +
                ", registerDepartmentId=" + registerDepartmentId +
                ", doctorId=" + doctorId +
                ", userId=" + userId +
                ", registerTime='" + registerTime + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }
}