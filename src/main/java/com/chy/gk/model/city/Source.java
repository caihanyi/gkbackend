package com.chy.gk.model.city;

import com.chy.gk.model.uesr.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//资源品种表
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"source_name"}))
public class Source implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //资源名字
    @Column(name = "source_name",  length = 10)
    private String sourceName;
    //资源等级
    @Column(name = "level",  length = 3)
    private int level;
    //下级资源id
//    @Column(name = "level",  length = 3)
    private long lowerSource;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getLowerSource() {
        return lowerSource;
    }

    public void setLowerSource(long lowerSource) {
        this.lowerSource = lowerSource;
    }
}
