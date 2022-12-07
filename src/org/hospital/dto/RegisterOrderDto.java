package org.hospital.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/6 12:38
 */
public class RegisterOrderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String doctorName;
    private String userName;
    private String status;
    private String registerTime;

    private String departmentName;

    private String departmentTypeName;

    private String hospitalName;

    public RegisterOrderDto() {
    }

    public RegisterOrderDto(Integer id, String doctorName, String userName, String status, String registerTime, String departmentTypeName, String departmentName) {
        this.id = id;
        this.doctorName = doctorName;
        this.userName = userName;
        this.status = status;
        this.registerTime = registerTime;
        this.departmentTypeName = departmentTypeName;
        this.departmentName = departmentName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getDepartmentTypeName() {
        return departmentTypeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentTypeName(String departmentTypeName) {
        this.departmentTypeName = departmentTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterOrderDto that = (RegisterOrderDto) o;
        return Objects.equals(id, that.id) && Objects.equals(doctorName, that.doctorName) && Objects.equals(userName, that.userName) && Objects.equals(status, that.status) && Objects.equals(registerTime, that.registerTime) && Objects.equals(departmentTypeName, that.departmentTypeName) && Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorName, userName, status, registerTime, departmentTypeName, departmentName);
    }

    @Override
    public String toString() {
        return "RegisterOrderDto{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", departmentTypeName='" + departmentTypeName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
