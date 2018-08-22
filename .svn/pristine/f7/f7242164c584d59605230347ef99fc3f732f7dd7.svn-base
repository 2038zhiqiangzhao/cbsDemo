-------------------- 代码模板说明 --------------------

* 每个项目使用6个基本模块进行开发；
    user-model:存放模型po,dto,vo；
    user-business:service和dao实现层；
    user-interface:dubbo接口；
    user-service:服务接口；
    user-web:web项目；
    user-api:web-api；


-------------------- doc说明 --------------------
abatorConfig.xml：ibatis用来生成代码的工具（ibatis）；
generatorConfig.xml : 用来生成代码的工具（mybatis）
create.sql：创建数据库脚本；
init.sql：数据库初始化脚本；

-------------------- Archetype 生成说明 --------------------
在当前目录user下面：
cd user
mvn archetype:create-from-project

cd target/generated-sources/archetype
mvn install
mvn deploy
之后就可以在 工具中发现这个 archetype 了


<dependency>
  <groupId>com.people2000</groupId>
  <artifactId>user</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
