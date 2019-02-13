package com.plumblum.entity;

import javax.persistence.*;


/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:17
 * @Description:
 */
@Entity
@Table(name = "sys_role", schema = "shiro", catalog = "")
public class SysRole {
    @Id
    private long id;
    private String role;
    private String description;
    private Byte available;

    @Basic
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "available")
    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRole sysRole1 = (SysRole) o;

        if (id != sysRole1.id) return false;
        if (role != null ? !role.equals(sysRole1.role) : sysRole1.role != null) return false;
        if (description != null ? !description.equals(sysRole1.description) : sysRole1.description != null) return false;
        if (available != null ? !available.equals(sysRole1.available) : sysRole1.available != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }


}
