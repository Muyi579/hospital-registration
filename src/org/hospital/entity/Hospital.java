package org.hospital.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/5 23:51
 * @Description 医院实体类
 */
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalTell;
    private String hospitalDescription;
    private Date createTime;
    private Date updateTime;

    public Hospital() {
    }

    public Hospital(Integer id, String hospitalName, String hospitalAddress, String hospitalTell, String hospitalDescription, Date createTime, Date updateTime) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalTell = hospitalTell;
        this.hospitalDescription = hospitalDescription;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalTell() {
        return hospitalTell;
    }

    public void setHospitalTell(String hospitalTell) {
        this.hospitalTell = hospitalTell;
    }

    public String getHospitalDescription() {
        return hospitalDescription;
    }

    public void setHospitalDescription(String hospitalDescription) {
        this.hospitalDescription = hospitalDescription;
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
        Hospital hospital = (Hospital) o;
        return Objects.equals(id, hospital.id) && Objects.equals(hospitalName, hospital.hospitalName) && Objects.equals(hospitalAddress, hospital.hospitalAddress) && Objects.equals(hospitalTell, hospital.hospitalTell) && Objects.equals(hospitalDescription, hospital.hospitalDescription) && Objects.equals(createTime, hospital.createTime) && Objects.equals(updateTime, hospital.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hospitalName, hospitalAddress, hospitalTell, hospitalDescription, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                ", hospitalTell='" + hospitalTell + '\'' +
                ", hospitalDescription='" + hospitalDescription + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
