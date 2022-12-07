package org.hospital.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/5 23:56
 * @Description 医生实体类
 */
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String doctorName;
    private String doctorDescription;
    private Date createTime;
    private Date updateTime;

    public Doctor() {
    }

    public Doctor(Integer id, String doctorName, String doctorDescription, Date createTime, Date updateTime) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorDescription = doctorDescription;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDescription() {
        return doctorDescription;
    }

    public void setDoctorDescription(String doctorDescription) {
        this.doctorDescription = doctorDescription;
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
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(doctorName, doctor.doctorName) && Objects.equals(doctorDescription, doctor.doctorDescription) && Objects.equals(createTime, doctor.createTime) && Objects.equals(updateTime, doctor.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorName, doctorDescription, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", doctorDescription='" + doctorDescription + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
