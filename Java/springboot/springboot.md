1. **入口**：XXXApplication类 使用@SpringBootApplication注解   
* 加载application.yml或者application.properties 配置文件   
springboot会自动加载spring.datasource.*相关配置，数据源就会自动注入到sqlSessionFactory中，   
sqlSessionFactory会自动注入到Mapper中   
在启动类中添加对mapper包扫描@MapperScan    
* 自动扫描XXXApplication同级目录及子目录所有带注解的类   
com.self   
---XXXApplication   
com.self.controller   
com.self.service   
