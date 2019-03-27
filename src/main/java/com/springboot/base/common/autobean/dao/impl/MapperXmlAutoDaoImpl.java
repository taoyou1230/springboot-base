package com.springboot.base.common.autobean.dao.impl;

import com.springboot.base.common.autobean.bean.ColumnStruct;
import com.springboot.base.common.autobean.bean.TableStruct;
import com.springboot.base.common.autobean.dao.GetTablesDao;
import com.springboot.base.common.autobean.dao.MapperXmlAutoDao;
import com.springboot.base.common.autobean.utils.*;

import java.util.List;

/**
 * 生成Mapper.xml的dao层实现类
 * @author
 *
 */
public class MapperXmlAutoDaoImpl implements MapperXmlAutoDao {

    //从GetTablesDaoImpl中获得装有所有表结构的List
    GetTablesDao getTables = new GetTablesDaoImpl();
    List<TableStruct> list = getTables.getTablesStruct();

    //通过表名、字段名称、字段类型创建Mapper.xml
    @Override
    public boolean createMapperXml() {
        //获得配置文件的参数
        //项目路径
        String projectPath = System.getProperty("user.dir");
        //是否生成Mapper.xml
        String mapperXmlFalg=ConfigUtil.mapperXmlFlag;
        //Mapper.xml的包名
        String mapperXmlPackage=ConfigUtil.mapperXmlPackage;
        //Bean实体类的包名
        String beanPackage=ConfigUtil.beanPackage;
        //Dao接口的包名
        String daoPackage= ConfigUtil.daoPackage;
        if("true".equals(mapperXmlFalg) ){
            //将包名com.xxx.xxx形式，替换成com/xxx/xxx形成
            String mapperXmlPath=mapperXmlPackage.replace(".", "/");
            //Mapper.xml的路径
            String path =projectPath+"/src/main/resources/"+mapperXmlPath;
            //遍历装有所有表结构的List
            for (int i = 0; i < list.size(); i++) {
                //表名
                String tableName =list.get(i).getTableName();

                //文件名
                String fileName=NameUtil.fileName(tableName)+"Mapper";
                String beanName = NameUtil.fileName(tableName);
                String daoName =NameUtil.fileName(tableName)+"Mapper";

                //获得每个表的所有列结构
                List<ColumnStruct> columns =list.get(i).getColumns();

                //主键名
                String beanIdName=NameUtil.columnName(columns.get(0).getColumnName());
                String IdName = columns.get(0).getColumnName();
                //主键类型
                String IdType = DataTypeUtil.getType(columns.get(0).getDataType());
                String IdParamType = ParamTypeUtil.getParamType(IdType);
                String IdJdbcType = JdbcTypeUtil.getJdbcType(IdType);
                if(IdJdbcType=="INT"||"INT".equals(IdJdbcType)){
                    IdJdbcType="INTEGER";
                }

                //(Mapper.xml）文件内容
                StringBuffer headCon = new StringBuffer();
                headCon.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
                headCon.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
                headCon.append("<mapper namespace=\""+daoPackage+"."+daoName+"\">\n");

                StringBuffer resultMapCon = new StringBuffer();
                resultMapCon.append("\t"+"<resultMap id=\"BaseResultMap\" type=\""+beanPackage+"."+beanName+"\">\n");

                StringBuffer baseColCon = new StringBuffer();
                baseColCon.append("\t"+"<sql id=\"Base_Column_List\">\n");

                StringBuffer sortCondition = new StringBuffer();
                sortCondition.append("\t<sql id=\"Sort_Condition\">\n");
                sortCondition.append("\t\t<trim suffixOverrides=\",\">\n");
                sortCondition.append("\t\torder by\n");
                sortCondition.append("\t\t<choose>\n");
                sortCondition.append("\t\t\t<when test=\"sortInfo != null and sortInfo.size>0\">\n");

                StringBuffer queryCondition = new StringBuffer();
                queryCondition.append("\t<sql id=\"Query_Condition\">\n");
                queryCondition.append("\t\t1=1\n");
                queryCondition.append("\t\t<if test=\"filters !=null and filters.size>0\">\n");

                StringBuffer queryByCondition = new StringBuffer();
                queryByCondition.append("\t"+"<select id=\"queryByCondition\" resultMap=\"BaseResultMap\" parameterType=\"java.util.Map\">\n");
                queryByCondition.append("\t\t"+"<where>\n");
                queryByCondition.append("\t\t\t"+"<include refid=\"Query_Condition\"/>\n");
                queryByCondition.append("\t\t"+"</where>\n");
                queryByCondition.append("\t\t"+"<include refid=\"Sort_Condition\"/>\n");
                queryByCondition.append("\t</select>\n");

                //遍历List，将字段名称和字段类型、属性名写进文件
                for (int j = 0; j <columns.size(); j++) {
                    //字段名
                    String columnName =columns.get(j).getColumnName();
                    //属性（变量）名
                    String attrName =NameUtil.columnName(columns.get(j).getColumnName());
                    //字段类型
                    String type=DataTypeUtil.getType(columns.get(j).getDataType());;
                    String jdbcType =JdbcTypeUtil.getJdbcType(type);
                    if("INT".equalsIgnoreCase(jdbcType)){
                        jdbcType="INTEGER";
                    }
                    if("PRI".equalsIgnoreCase(columns.get(j).getColumnKey())){
                        resultMapCon.append("\t\t"+"<id column=\""+columnName+"\" property=\""+attrName+"\" jdbcType=\""+jdbcType+"\"/>\n");
                    }else{
                        resultMapCon.append("\t\t"+"<result column=\""+columnName+"\" property=\""+attrName+"\" jdbcType=\""+jdbcType+"\"/>\n");
                    }
                    if(j==0) {
                        baseColCon.append("\t\t" + columnName);
                    }else{
                        baseColCon.append(","+columnName);
                    }
                    sortCondition.append("\t\t\t\t<if test=\"sortInfo."+attrName+" =='desc'\">\n");
                    sortCondition.append("\t\t\t\t\t"+columnName+" desc,\n");
                    sortCondition.append("\t\t\t\t</if>\n");
                    sortCondition.append("\t\t\t\t<if test=\"sortInfo."+attrName+" =='asc'\">\n");
                    sortCondition.append("\t\t\t\t\t"+columnName+" asc,\n");
                    sortCondition.append("\t\t\t\t</if>\n");

                    if("INTEGER".equalsIgnoreCase(jdbcType)){
                        queryCondition.append("\t\t\t<if test=\"filters."+attrName+" !=null and filters."+attrName+" !=0\">\n");
                    }else if("VARCHAR".equalsIgnoreCase(jdbcType)){
                        queryCondition.append("\t\t\t<if test=\"filters."+attrName+" !=null and filters."+attrName+" !=''\">\n");
                    }else{
                        queryCondition.append("\t\t\t<if test=\"filters."+attrName+" !=null\">\n");
                    }

                    queryCondition.append("\t\t\t\tand "+columnName+" = #{filters."+attrName+"}\n");
                    queryCondition.append("\t\t\t</if>\n");
                }
                resultMapCon.append("\t"+"</resultMap>\n\n");
                baseColCon.append("\n\t"+"</sql>\n\n");
                sortCondition.append("\t\t\t</when>\n\t\t\t<otherwise>\n\t\t\t\tcreated_at desc\n\t\t\t</otherwise>\n\t\t</choose>\n\t\t</trim>\n\t</sql>\n\n");
                queryCondition.append("\t\t</if>\n\t</sql>\n\n");

                //拼接(Mapper.xml）文件内容
                StringBuffer content=new StringBuffer();
                content.append(headCon);
                content.append(resultMapCon);
                content.append(baseColCon);
                content.append(sortCondition);
                content.append(queryCondition);
                content.append(queryByCondition);
                content.append("</mapper>");

                FileUtil.createFileAtPath(path+"/", fileName+".xml", content.toString());
            }
            return true;
        }
        return false;
    }

}
