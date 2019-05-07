Eureka：服务治理：注册和发现，Eureka客户端向Eureka服务端注册、发现微服务  
Ribbon：负载均衡（微服务对内调用），通过Eureka Server获取服务列表、再通过负载均衡算法调用微服务  
Hystrix：实现断路器、线程隔离、信号隔离等容错功能  
Zuul：网关（对外调用），路由转发，如以"user/"开头都到user微服务去  
Config：配置文件管理  
Bus：微服务之间消息队列，松耦合  

springcloud如何实现服务的注册和发现

    服务在发布时 指定对应的服务名（服务名包括了IP地址和端口） 将服务注册到注册中心（eureka或者zookeeper）
    这一过程是springcloud自动实现 只需要在main方法添加@EnableDisscoveryClient  同一个服务修改端口就可以启动多个实例
    调用方法：传递服务名称通过注册中心获取所有的可用实例 通过负载均衡策略调用（ribbon和feign）对应的服务

 

ribbon和feign区别

    Ribbon添加maven依赖 spring-starter-ribbon 使用@RibbonClient(value="服务名称") 使用RestTemplate调用远程服务对应的方法
    feign添加maven依赖 spring-starter-feign 服务提供方提供对外接口 调用方使用 在接口上使用@FeignClient("指定服务名")

Ribbon和Feign的区别：

    Ribbon和Feign都是用于调用其他服务的，不过方式不同。
    1.启动类使用的注解不同，Ribbon用的是@RibbonClient，Feign用的是@EnableFeignClients。
    2.服务的指定位置不同，Ribbon是在@RibbonClient注解上声明，Feign则是在定义抽象方法的接口中使用@FeignClient声明。
    3.调用方式不同，Ribbon需要自己构建http请求，模拟http请求然后使用RestTemplate发送给其他服务，步骤相当繁琐。
    Feign则是在Ribbon的基础上进行了一次改进，采用接口的方式，将需要调用的其他服务的方法定义成抽象方法即可，
    不需要自己构建http请求。不过要注意的是抽象方法的注解、方法签名要和提供服务的方法完全一致。

springcloud断路器的作用

    当一个服务调用另一个服务由于网络原因或者自身原因出现问题时 调用者就会等待被调用者的响应 当更多的服务请求到这些资源时
    导致更多的请求等待 这样就会发生连锁效应（雪崩效应） 断路器就是解决这一问题
    断路器有完全打开状态：
            一定时间内 达到一定的次数无法调用 并且多次检测没有恢复的迹象 断路器完全打开，那么下次请求就不会请求到该服务
    半开：
            短时间内 有恢复迹象 断路器会将部分请求发给该服务 当能正常调用时 断路器关闭
    关闭：
            当服务一直处于正常状态 能正常调用 断路器关闭

demo：
https://github.com/glowwormX/note/tree/master/Java/springCloud/spring-cloud-parent
``` lua
spring-cloud-parent   
├── spring-cloud-eureka -- 8761 高可用注册中心，相互注册    
├── spring-cloud-eureka1 -- 8762 高可用注册中心，相互注册     
├── spring-cloud-config-server -- 8001 配置中心服务器，映射github上配置，使用Bus+RabbitMQ更新配置,/bus/refresh更新     
├── spring-cloud-provider -- 9000 服务提供者，向eureka注册   
├── spring-cloud-provider2 -- 9001 服务提供者1  有redis 
├── spring-cloud-consumer -- 9100 消费者，熔断机制，spring-cloud-config客户端, 
├                             RabbitMQ集成,交换、队列机制， 接受请求发送给RabbitMQ，consumer、provider中有消费
├── spring-cloud-consumer1 -- 9101 消费者1，熔断机制，spring-cloud-config客户端    
└── spring-cloud-zuul -- 8888 网关（入口，控制登录token等）   

http://localhost:8888/spring-cloud-consumer/hello/111?token=111
多次访问会出现四种情况
consumer调用provider
consumer1调用provider
consumer调用provider1
consumer1调用provider1
```

rabbit安装   
https://www.jianshu.com/p/2d4b81c8b403   
http://blog.topspeedsnail.com/archives/4750   