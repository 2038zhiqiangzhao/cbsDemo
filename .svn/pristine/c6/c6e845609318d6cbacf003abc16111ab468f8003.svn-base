利用abatorConfig.xml工具生成好之后 需要 做一些配置
DaoImpl 需要增加 @Service("mcChanelClientDAO")
SqlMap.xml
delete 需要改 为软删除 delete_is
select 需要增加 delete_is = 0
date   @DateTimeFormat(pattern="yyyy-MM-dd")
         @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")

private SqlMapClient sqlMapClient;
protected SqlMapClient sqlMapClient;

mysql 乱码
http://www.2cto.com/database/201108/101151.html
