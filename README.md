# ssm-studying
关于ssm学习项目的进度记录



# 架构

* 框架：SSM+Springboot

* 测试：swagger
* 数据库镜像：docker(mysql)

# 目录树
>.mvn
>
>src
>
>>
>>
>>>java/com/tyutjohn/test
>>>
>>>bean
>>>
>>>>Person
>>>>
>>>>Account
>>>
>>>config
>>>
>>>>SwaggerConfig
>>>
>>>controller
>>>
>>>>AccountController
>>>>
>>>>PersonController
>>>
>>>mapper
>>>
>>>>AccountMapper
>>>>
>>>>PersonMapper
>>>
>>>service
>>>
>>>> AccountService
>>>>
>>>> PersonService
>>>>
>>>> Impl
>>>>
>>>> >AccountImpl
>>>> >
>>>> >PersonImpl
>>>
>>>TestApplication
>>
>>
>>
>>resources
>>
>>>mapper
>>>
>>>>AccountMapper.xml
>>>>
>>>>PersonMapper.xml
>>>
>>>application.yml/properties
>>
>>
>
>
>
>pom.xml



# 进度

* 2020/3/25

* first commit

```properties
/*
 * resources.application.properties配置
 */
spring.datasource.username=root									//mysql数据库用户名
spring.datasource.password=123456								//mysql数据库密码
spring.datasource.url=jdbc:mysql://localhost:3306/test			//mysql数据库jdbc配置
server.port=8080												//server端口配置
mybatis.mapper-locations=classpath:mapper/*Mapper.xml			//mybatis初始化，扫描mapper
																//文件下所有的Mapper.xml
mybatis.configuration.map-underscore-to-camel-case=true			//mybatis配置开启设置自动转化																   //驼峰标识

```

```java
/*
 * main/~/TestApplication.java	//入口文件
 */
@MapperScan("com.tyutjohn.test.mapper")							//添加mapper注解，自动扫描
```

```java
/*
 * main/~/config/SwaggerConfig的初始化配置
 */
package com.tyutjohn.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration												//添加自定义配置注解
@EnableSwagger2												//添加swagger2官方注解
public class SwaggerConfig {								//实例化一个配置类
    //配置docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    //配置apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact=new Contact("johnwang","www.tyutjohn.com","tyutjohnwang@163.com");
        return new ApiInfo(
          "johnwang的SwaggerAPI文档",
                "记录swagger",
                "V1.0",
                "www.tyutjohn.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}

```



```sql
/*
 *设计数据库表 person
 */
 /*
 Navicat Premium Data Transfer

 Source Server         : springboot
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 31/03/2020 18:40:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, 'john', '123');
INSERT INTO `person` VALUES (2, 'tyut', '123');
INSERT INTO `person` VALUES (3, 'xiaomin', '4321');

SET FOREIGN_KEY_CHECKS = 1;

```



```mysql
/*
 *main/~/bean/person类的创建（BEAN层）
 */
 package com.tyutjohn.test.bean;

public class Person {
    private int id;
    private String name;
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

```

```java
/*
 * main/~/mapper/PersonMapper接口（dao层），定义增删改查基本方法，springmvc中mapper处理简单的增删  *  改查
 */
package com.tyutjohn.test.mapper;

import com.tyutjohn.test.bean.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    List<Person> selectPerson();								//声明一个list的person类型的 																 //查询方法
    void insertPerson(@Param("person")Person person);			//声明一个返回值为空的插入方																	//法，传入Param为person字段
}

```

```java
/*
 * main/~/service/PersonService接口，调用serverImpl,抽象出具体接口方法（业务逻辑层）
 */
package com.tyutjohn.test.service;

import com.tyutjohn.test.bean.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();								//抽象出impl中的查询方法
    String addPerson(Person person);							//抽象出impl中的插入方法
}

```

```java
/*
 * main/~/service/Impl/PersonImpl接口，具体操作DAO层
 */
package com.tyutjohn.test.service.Impl;

import com.tyutjohn.test.bean.Person;
import com.tyutjohn.test.mapper.PersonMapper;
import com.tyutjohn.test.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service													//添加service注解，自动装配bean
public class PersonImpl implements PersonService {			
    @Resource												//默认装配字段名，可以指定name属性
    PersonMapper personMapper;								//实例化Mapper(dao层)
    public List<Person> getAllPerson(){						//实现service接口定义的函数的实现
        return personMapper.selectPerson();					//返回出Mapper中的接口函数
    }
    public String addPerson(Person person){
        try {
            personMapper.insertPerson(person);
            return "success";
        }catch (Exception e){
            return ("error:\n"+e);
        }
    }
}

```

```java
/*
 * main/~/controller/PersonController处理前台发送请求（action层）
 */
package com.tyutjohn.test.controller;

import com.tyutjohn.test.bean.Person;
import com.tyutjohn.test.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller											//引入controller注解,标记一个类便于扫描
public class PersonController {
    @Resource										//依赖注入，service接口
    PersonService personService;					//实例化service接口
    @RequestMapping(value = "/getAllPerson",method = RequestMethod.GET)	//路径映射,GET方法
    @ResponseBody									//将java对象转为json数据
    public List<Person> GetAllPerson(){				//实现service的方法
        List<Person> personList=personService.getAllPerson();
        return personList;
    }
    @RequestMapping(value = "/addPerson",method = RequestMethod.POST)  //路径映射，POST方法
    @ResponseBody									//将java对象转为json数据
    public String addPerson(@RequestBody Person person){	//@RequestBody接收前端传来的json  	//数据，可以和@RequestParam同时使用，但@RequestBody只能有一个，Param可以有多个，此为接收person字段
        String result=personService.addPerson(person);		
        return result;
    }
}

```

```xml
<!--
 * resources/~/mapper/PersonMapper.xml中sql语句的编写
 -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tyutjohn.test.mapper.PersonMapper">		<!--此处为mapper的path-->
    <!--此处为bean的path,id为下方sql语句调用id，将字段封装为pojo类-->
    <resultMap id="personReuslt" type="com.tyutjohn.test.bean.Person"> 
        <id property="id" column="id" />
        <result property="name" column="name" />
        <result property="password" column="password" />
    </resultMap>
    <!--id为Mapper接口中定义的方法，resultMap映射上面的pojo-->
    <select id="selectPerson" resultMap="personReuslt">
        select * from person
    </select>
    <!--id为Mapper接口中定义的方法，parameterType为定义Mapper接口方法接收的参数类型，此为复杂类型中的类类型，路径为Bean类路径-->
    <insert id="insertPerson" parameterType="com.tyutjohn.test.bean.Person">
        insert person (name,password) values (#{person.name},#{person.password})
    </insert>
</mapper>

```



### 总结

* ssm框架是spring+spring MVC+MyBatis目前比较主流的Java EE企业级框架，本项目采用springboot，docker，swagger进行快速搭建开发测试，主要以学习spring+springboot为主要目的，日后会根据时间进行不定期的更新和完善。



# 关于作者

* 邮箱(tyutjohnwang@163.com)
* author:tyutjohn
* 个人网站(www.tyutjohn.com)  维护中--(2020/12/25)



