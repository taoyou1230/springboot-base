package com.springboot.base.module.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.springboot.base.common.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * 活动表
 */
@Table(name = "activity")
public class Activity extends BaseEntity {

    private static final long serialVersionUID = 5199200306752426434L;
    /**活动ID*/
    @Id
    @GeneratedValue(generator = "JDBC")
//    @ApiModelProperty(value = "活动ID", example = "123")
    @Column(name = "id", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
    private Integer id;
    /**活动名称*/
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 100)
    private String name;
    /**活动描述*/
    @Column(name = "descr", type = MySqlTypeConstant.VARCHAR, length = 255)
    private String descr;
    /**运营商*/
    @Column(name = "company_name", type = MySqlTypeConstant.VARCHAR, length = 50)
    private String companyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
