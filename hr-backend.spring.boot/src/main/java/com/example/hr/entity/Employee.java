package com.example.hr.entity;

import com.example.hr.validation.TcKimlikNo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@ApiModel
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    @TcKimlikNo
    private String identity;
    @Size(min = 5)
    private String fullName;
    @JsonProperty("year")
    private int birthYear;
    @Min(2000)
    private double salary;
    private byte[] photo;

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", identity='" + identity + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthYear=" + birthYear +
                ", salary=" + salary +
                '}';
    }
}
