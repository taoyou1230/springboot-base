package com.springboot.base.common;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 阿里数据库连接池 Druid配置
 * 创建者 柒
 * 创建时间    2018年3月15日
 */
//@Configuration
public class DruidConfiguration {

    /**
     * 将自定义的 Druid 数据源添加到容器中，不再让 Spring Boot 自动创建
     * 这样做的目的是：绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource
     * 从而让它们生效
     * @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中 前缀为 spring.datasource
     * 的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
     *
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        try {
            return new DruidDataSource();
        }catch (Exception e){
            System.out.println("数据库连接异常");
            return null;
        }
    }

}
