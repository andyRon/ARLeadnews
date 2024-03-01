AR头条
----

[详细开发笔记](./AR头条.md)

工程结构：

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

三个平台：

```
用户移动端
自媒体管理平台
后台管理
```

数据库：

- leadnews_user
- leadnews_wemedia
- leadnews_schedule
- leadnews_article

第三方：

阿里云内容审核（文字、图片）







项目不同环境需要修改的地方

logback.xml，日志存储位置



开发时搭建的环境

- nacos使用本地

http://localhost:8848/nacos

- Nginx

```
/opt/homebrew/etc/nginx/leadnews.conf/
```



http://localhost:8801/  手机端

http://localhost:8802/  自媒体

- 后端

后端微服务的端口是518**

leadnews-user  http://localhost:51801/swagger-ui.html

leadnews-article http://localhost:51802/swagger-ui.html

leadnews-wemedia  http://localhost:51803/swagger-ui.html

- 网关  端口516**

- minio

本地搭建 minioadmin





- redis



- leadnews-schedule
