package cn.shiyang.tflz.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpGenerator {

    public static void main(String[] args) {
        //创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前系统目录
        String projectPath = System.getProperty("user.dir");

        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("shiyang");  //生成作者注释
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/tflz?serverTimezone=Asia/Shanghai");  //url
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");  //数据库驱动
        dsc.setUsername("root");  //数据库账号
        dsc.setPassword("root");  //数据库密码
        dsc.setDbType(DbType.MYSQL);  //数据库类型
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("tflz"); //模块名,可以不设置
        pc.setParent("cn.shiyang");  //放在哪个包下
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("user");//对哪一张表生成代码
        strategy.setExclude("flyway_schema_history");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setLogicDeleteFieldName("deleted"); //逻辑删除字段设置

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setControllerMappingHyphenStyle(false); //url中驼峰转连字符
        mpg.setStrategy(strategy);

        //执行
        mpg.execute();

    }
}
