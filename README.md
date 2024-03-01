AR头条
----

[详细开发笔记](./AR头条.md)

- 工程结构：

```
arleadnews								父工程，统一管理项目依赖（定义通用包的版本），springboot
		leadnews-basic
    	file-starter				MinIO文件存储系统
		leadnews-common				通用配置（常量、全局异常、Redis、swagger、阿里云配置）
		leadnews-feign-api		feign对外的接口
		leadnews-gateway			网关
    	leadnews-app-gateway			【51601】
      leadnews-wemedia-gateway		【51602】
		leadnews-model				pojo、dto
		leadnews-utils				通用工具
		leadnews-service			管理微服务
			leadnews-article		文章								【51802】
			leadnews-schedule		延迟、定时任务				【51701】
			leadnews-user				用户端							【51801】
			leadnews-wemedia		自媒体								【51803】
			leadnews-search			搜索
			leadnews-comment		评论
			leadnews-behavior		
		leadnews-test					测试案例
```

- 三个平台：

```
用户移动端
自媒体管理平台
后台管理
```

- 数据库：

- leadnews_user
- leadnews_wemedia
- leadnews_schedule
- leadnews_article

- 第三方：

阿里云内容审核（文字、图片）

- Nginx

在Nginx中单独建立项目相关配置，用于项目的两个前端系统

```
/opt/homebrew/etc/nginx/leadnews.conf/
```

http://localhost:8801/  手机端

http://localhost:8802/  自媒体

- nacos使用本地

http://localhost:8848/nacos

- minio

本地搭建 minioadmin minioadmin

```shell
./minio server --config-dir=/Users/andyron/myfield/env/minio/config --address=:9000 /Users/andyron/myfield/env/minio/data
```







项目不同环境需要修改的地方

logback.xml，日志存储位置



- redis

- leadnews-schedule



### 问题

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/andyron/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/andyron/.m2/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.13.3/log4j-slf4j-impl-2.13.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
```



- 文件服务minio，域名是否应该写在数据库中？





