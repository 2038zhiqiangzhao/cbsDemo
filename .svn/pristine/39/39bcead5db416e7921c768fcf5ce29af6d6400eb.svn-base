-------------------- 代码模板说明 --------------------

* 每个项目使用6个基本模块进行开发；
    cbsadmin-model:存放模型po,dto,vo；
    cbsadmin-business:service和dao实现层；
    cbsadmin-interface:SOA接口；
    cbsadmin-service:SOA接口服务；
    cbsadmin-web:web项目；
    cbsadmin-api:web-api restful接口；


-------------------- doc说明 --------------------
abatorConfig.xml：ibatis用来生成代码的工具（ibatis）；
generatorConfig.xml : 用来生成代码的工具（mybatis）
create.sql：创建数据库脚本；
init.sql：数据库初始化脚本；

-------------------- Archetype 生成说明 --------------------
在当前目录cbsadmin下面：
cd cbsadmin
mvn archetype:create-from-project

cd target/generated-sources/archetype
mvn install
mvn deploy
之后就可以在 工具中发现这个 archetype 了


<dependency>
  <groupId>com.shendi</groupId>
  <artifactId>cbsadmin</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
