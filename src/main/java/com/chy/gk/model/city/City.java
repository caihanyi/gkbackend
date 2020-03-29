package com.chy.gk.model.city;

import com.chy.gk.model.uesr.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"city_name"}))
public class City implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //用户编号
    private long id;
    //城市名
    @Column(name = "city_name", nullable = false, length = 40)
    private String cityName;
    //市长 允许没有市长
    @Column(name = "masterId",  length = 40)
    private String masterId;
    //地图坐标-左 单位百分比
    @Column(name = "position_left",nullable = false, length = 3)
    private int positionLeft;
    //地图坐标-上 单位百分比
    @Column(name = "position_top",nullable = false, length = 3)
    private int positionTop;
    //人口数
    @Column(name = "population",nullable = false, length = 3)
    private long population;
    //状态
    @Column(name = "status",nullable = false, length = 3)
    private String  status;
    //状态
    @Column(name = "province_id",nullable = false)
    private long  provinceId;

//    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roleSet;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public int getPositionLeft() {
        return positionLeft;
    }

    public void setPositionLeft(int positionLeft) {
        this.positionLeft = positionLeft;
    }

    public int getPositionTop() {
        return positionTop;
    }

    public void setPositionTop(int positionTop) {
        this.positionTop = positionTop;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }
}