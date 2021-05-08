package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2017/12/16
 * Time: 22:09
 * Describe:
 */
@Entity
@Table(name = "dept", schema = "test", catalog = "")
public class DeptEntity {
    private int id;
    private String deptid;
    private String deptname;
    private String content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeptEntity that = (DeptEntity) o;

        if (id != that.id) return false;
        if (deptid != null ? !deptid.equals(that.deptid) : that.deptid != null) return false;
        if (deptname != null ? !deptname.equals(that.deptname) : that.deptname != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (deptid != null ? deptid.hashCode() : 0);
        result = 31 * result + (deptname != null ? deptname.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "DeptEntity{" +
                "id=" + id +
                ", deptid='" + deptid + '\'' +
                ", deptname='" + deptname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
