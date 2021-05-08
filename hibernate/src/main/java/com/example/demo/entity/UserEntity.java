package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2017/12/15
 * Time: 22:42
 * Describe:
 */
@Entity
@Table(name = "user", schema = "test", catalog = "")
public class UserEntity {
    private int id;
    private String code;
    private String name;
    private String password;
    private Integer age;
    private String sex;
    private Timestamp brithday;
    private String deptid;
    private String deptname;
    private String picture;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "brithday")
    public Timestamp getBrithday() {
        return brithday;
    }

    public void setBrithday(Timestamp brithday) {
        this.brithday = brithday;
    }

    @Basic
    @Column(name = "deptid")
    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    @Basic
    @Column(name = "deptname")
    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (brithday != null ? !brithday.equals(that.brithday) : that.brithday != null) return false;
        if (deptid != null ? !deptid.equals(that.deptid) : that.deptid != null) return false;
        if (deptname != null ? !deptname.equals(that.deptname) : that.deptname != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (brithday != null ? brithday.hashCode() : 0);
        result = 31 * result + (deptid != null ? deptid.hashCode() : 0);
        result = 31 * result + (deptname != null ? deptname.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }
}
