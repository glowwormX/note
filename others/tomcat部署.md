1. tomcat默认编码
7默认ISO-8859-1 8以上UTF8
7修改8080端口节点  增加属性URIEncoding="UTF-8" 

1. tomcat热部署
修改文件则重新加载项目   
`server.xml里<Host></Host>里加：`   
`<Context path="/product-platform" docBase="product-platform" debug="99" reloadable="true" />`

1. spring boot 开发中热加载
pom加
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<optional>true</optional>
</dependency>
```

1. springboot 项目war包部署
* 修改pom
* 修改启动类
* maven install启动   
https://blog.csdn.net/qq_23739971/article/details/73752067   
https://www.cnblogs.com/gdpuzxs/p/7224959.html?utm_source=itdadao&utm_medium=referral    

1. Dynamic Web Module   
eclispe上修改不了 servlet   
项目/.setting/org.eclipse.wst.common.project.facet.core.xml修改   


1. tomcat优化   
内存、并发、缓存   
https://blog.csdn.net/kally_wang/article/details/74989885   
https://blog.csdn.net/centre10/article/details/50639693   

widows tomcat 注册成服务优化   
https://blog.csdn.net/lfsf802/article/details/46700553

jstat命令监控   
https://www.cnblogs.com/lizhonghua34/p/7307139.html

jconsole监控 配置   
https://www.cnblogs.com/aguncn/p/4177989.html
windows服务注册表配置：   
-Djava.rmi.server.hostname=192.168.0.56   
-Dcom.sun.management.jmxremote  
-Dcom.sun.management.jmxremote.port="8099"   
-Dcom.sun.management.jmxremote.authenticate="false"   
-Dcom.sun.management.jmxremote.ssl="false"   




