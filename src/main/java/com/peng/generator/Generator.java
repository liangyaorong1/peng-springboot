package com.peng.generator;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * mybatis-plus代码生成器
 */
public class Generator {
    public static void main(String[] args) {
        String projectPath=System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/students-second?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                        "root", "root")
                .globalConfig(builder -> {
                    builder.author("peng") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()//禁止打开输出目录
                            .commentDate("yyyy-MM-dd")//注释日期，默认值:yyyy-MM-dd
                            .outputDir(projectPath+"/src/main/java"); // 指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("peng") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("order") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                    builder.entityBuilder()//entity策略配置
                            .idType(IdType.AUTO)
                            .enableLombok()//开启lombok
                            .enableRemoveIsPrefix()//开启 Boolean 类型字段移除 is 前缀
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            .logicDeleteColumnName("deleted")//逻辑删除字段名
                            .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略：下划线转驼
                            .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .addTableFills(
                                    new Column("created",FieldFill.INSERT),
                                    new Column("modified", FieldFill.INSERT_UPDATE)
                            );
                    builder.controllerBuilder()//Controller策略配置
                            .enableRestStyle();//开启生成@RestController 控制器
                    builder.mapperBuilder()//mapper策略配置
                            .enableMapperAnnotation()//开启 @Mapper 注解
                            .enableBaseColumnList() //是否生成<sql id="Base_Column_List">
                            .enableBaseResultMap()// 设置需要生成字段与实体类的映射Map <resultMap id="BaseResultMap" type="实体类全路径">
                            .serviceBuilder();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    /**
     * 处理 all 情况
     * @param tables
     * @return
     */
    protected  static List<String> getTables(String tables){
        return  "all".equals(tables)?Collections.emptyList(): Arrays.asList(tables.split(","));
    }

}
