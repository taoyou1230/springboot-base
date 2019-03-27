package com.springboot.base.common.autobean.dao.impl;

import com.springboot.base.common.autobean.bean.ColumnStruct;
import com.springboot.base.common.autobean.bean.TableStruct;
import com.springboot.base.common.autobean.dao.DaoAutoDao;
import com.springboot.base.common.autobean.dao.GetTablesDao;
import com.springboot.base.common.autobean.utils.ConfigUtil;
import com.springboot.base.common.autobean.utils.DataTypeUtil;
import com.springboot.base.common.autobean.utils.FileUtil;
import com.springboot.base.common.autobean.utils.NameUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 生成Dao接口的dao层实现类
 * @author
 *
 */
public class DaoAutoDaoImpl implements DaoAutoDao {

    //从GetTablesDaoImpl中获得装有所有表结构的List
    GetTablesDao getTables = new GetTablesDaoImpl();
    List<TableStruct> list = getTables.getTablesStruct();

    //通过表名、字段名称、字段类型创建Dao接口
    @Override
    public boolean createDao() {
        //获得配置文件的参数
        //项目路径
        String projectPath = System.getProperty("user.dir");
        //是否生成Dao
        String daoFalg= ConfigUtil.daoFlag;
        //Dao接口的包名
        String daoPackage=ConfigUtil.daoPackage;
        //Bean实体类的包名
        String beanPackage=ConfigUtil.beanPackage;
        //Dao接口继承的类
        String daoExtends = ConfigUtil.daoExtends;
        String daoClass = null;
        if(StringUtils.isNotBlank(daoExtends)) {
            daoClass = daoExtends.substring(daoExtends.lastIndexOf(".") + 1);
        }
        if("true".equals(daoFalg) ){
            //将包名com.xxx.xxx形式，替换成com/xxx/xxx形成
            String daoPath=daoPackage.replace(".", "/");
            //Dao接口的路径
            String path =projectPath+"/src/main/java/"+daoPath;
            //遍历装有所有表结构的List
            for (int i = 0; i < list.size(); i++) {
                //文件名
                String fileName=NameUtil.fileName(list.get(i).getTableName())+"Mapper";
                String beanName = NameUtil.fileName(list.get(i).getTableName());
                //获得每个表的所有列结构
                List<ColumnStruct> columns =list.get(i).getColumns();
                //主键变量名（属性名）目前只支持单主键
//                String columnName = null;
//                //获得数据类型
//                String type = null;
//                //将mysql数据类型转换为java数据类型
//                String dateType = null;
//                for(ColumnStruct colu:columns){
//                    if("PRI".equals(colu.getColumnKey())) {
//                        columnName = colu.getColumnName();
//                        type = colu.getDataType();
//                        dateType = DataTypeUtil.getType(type);
//                        if("Timestamp".equals(dateType)){
//                            dateType = "Date";
//                        }
//                        break;
//                    }
//                }

                //(Dao接口）文件内容
                String packageCon ="package "+daoPackage+";\n\n";
                StringBuffer importCon=new StringBuffer();
                importCon.append("import org.apache.ibatis.annotations.Mapper;\n");
                String className = null;
                if(StringUtils.isNotBlank(daoClass)){
                    className = "@Mapper\npublic interface " + fileName + " extends "+daoClass+"<"+beanName+">{\n\n";
                    importCon.append("import "+daoExtends+";\n");
                }else{
                    className = "@Mapper\npublic interface " + fileName + "{\n\n";
                }
                StringBuffer classCon = new StringBuffer();

                //生成导包内容
                importCon.append("import"+"\t"+beanPackage+"."+beanName+";\n\n");
                //有date类型的数据需导包
//                if("Date".equals(dateType)){
//                    importCon.append("import java.util.Date;\n\n");
//                }
                importCon.append("import java.util.List;\n\n");
                importCon.append("import java.util.Map;\n\n");
                //生成接口方法
                classCon.append("\t/**\n"
                        +"\t* 根据条件查询数据\n"
                        +"\t* @param map = {\n"
                        +"\t*  filters:查询条件，\n"
                        +"\t*  sortInfo：排序条件\n"
                        +"\t* }\n"
                        +"\t* @return\n"
                        +"\t*/\n"
                        +"\t"+"public List<"+beanName+"> queryByCondition(Map<String,Object>map);//添加一条完整记录\n\n");

                //拼接(Dao接口）文件内容
                StringBuffer content=new StringBuffer();
                content.append(packageCon);
                content.append(importCon.toString());
                content.append(className);
                content.append(classCon.toString());
                content.append("\n}");
                FileUtil.createFileAtPath(path+"/", fileName+".java", content.toString());
            }
            return true;
        }
        return false;
    }

}
