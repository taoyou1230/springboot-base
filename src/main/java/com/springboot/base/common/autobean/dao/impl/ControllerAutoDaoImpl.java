package com.springboot.base.common.autobean.dao.impl;

import com.springboot.base.common.autobean.bean.ColumnStruct;
import com.springboot.base.common.autobean.bean.TableStruct;
import com.springboot.base.common.autobean.dao.ControllerAutoDao;
import com.springboot.base.common.autobean.dao.GetTablesDao;
import com.springboot.base.common.autobean.utils.ConfigUtil;
import com.springboot.base.common.autobean.utils.DataTypeUtil;
import com.springboot.base.common.autobean.utils.FileUtil;
import com.springboot.base.common.autobean.utils.NameUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 生成Controller类的dao层实现类
 * @author
 *
 */
public class ControllerAutoDaoImpl implements ControllerAutoDao {

    //从GetTablesDaoImpl中获得装有所有表结构的List
    GetTablesDao getTables = new GetTablesDaoImpl();
    List<TableStruct> list = getTables.getTablesStruct();

    //通过表名、字段名称、字段类型创建Controller接口
    @Override
    public boolean createController() {
        //获得配置文件的参数
        //项目路径
        String projectPath = System.getProperty("user.dir");
        //是否生成Controller类
        String controllerFlag= ConfigUtil.controllerFlag;
        //Controller类的包名
        String controllerPackage=ConfigUtil.controllerPackage;
        //Bean实体类的包名
        String servicePackage=ConfigUtil.servicePackage;
        //Controller类继承的类
        String controllerExtends = ConfigUtil.controllerExtends;
        String controllerClass = null;
        if(StringUtils.isNotBlank(controllerExtends)) {
            controllerClass = controllerExtends.substring(controllerExtends.lastIndexOf(".") + 1);
        }
        if("true".equals(controllerFlag) ){
            //将包名com.xxx.xxx形式，替换成com/xxx/xxx形成
            String controllerPath=controllerPackage.replace(".", "/");
            //Service接口的路径
            String path =projectPath+"/src/main/java/"+controllerPath;

            //遍历装有所有表结构的List
            for (int i = 0; i < list.size(); i++) {
                //文件名
                String fileName= NameUtil.fileName(list.get(i).getTableName())+"Controller";
                String serviceName=NameUtil.fileName(list.get(i).getTableName())+"Service";
                String beanName =NameUtil.fileName(list.get(i).getTableName());
                //获得每个表的所有列结构
                List<ColumnStruct> columns =list.get(i).getColumns();
//                //主键变量名（属性名）
//                String columnName =NameUtil.columnName(columns.get(0).getColumnName());
//                //获得主键数据类型
//                String type = columns.get(0).getDataType();
//                //将mysql数据类型转换为java数据类型
//                String dateType = DataTypeUtil.getType(type);

                //(ServiceImpl实现类）文件内容
                String packageCon ="package "+controllerPackage+";\n\n";
                StringBuffer importCon=new StringBuffer();

                String className = null;
                String newServiceName = serviceName.substring(0,1).toLowerCase()+serviceName.substring(1);
                if(StringUtils.isNotBlank(controllerClass)){
                    className = "@Controller\n@RequestMapping(\""+newServiceName+"\")\npublic class " + fileName + " extends "+controllerClass+"{\n\n";
                    importCon.append("import "+controllerExtends+";\n");
                }else{
                    className = "@Controller\n@RequestMapping(\""+newServiceName+"\")\npublic class " + fileName + "{\n\n";
                }


                //生成导包内容
                importCon.append("import org.springframework.stereotype.Controller;\n");
                importCon.append("import javax.annotation.Resource;\n");
                importCon.append("import org.springframework.web.bind.annotation.*;\n");
                importCon.append("import org.apache.log4j.Logger;\n");
                importCon.append("import com.springboot.base.common.UserPermissionHelp;\n");
                importCon.append("import com.springboot.base.common.user.entity.User;\n");
                importCon.append("import "+servicePackage+"."+serviceName+";\n");
                //有date类型的数据需导包
//                if("Date".equals(dateType)){
//                    importCon.append("import java.util.Date;\n\n");
//                }
//                //有Timestamp类型的数据需导包
//                if("Timestamp".equals(dateType)){
//                    importCon.append("import java.sql.Timestamp;\n\n");
//                }

                StringBuffer classCon = new StringBuffer();
                classCon.append("\tprivate static Logger log = Logger.getLogger("+fileName+".class);\n");

                classCon.append("\t@Resource\n\tprivate "+serviceName+" "+newServiceName+";\n");
                classCon.append("\t@Resource\n\tprivate UserPermissionHelp userPermissionHelp;\n");
                classCon.append("\t@Resource\n\tprivate BaseController baseController;\n");
                //拼接(Controller类）文件内容
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
