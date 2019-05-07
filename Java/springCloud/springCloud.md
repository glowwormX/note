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

Ribbon和Feign的区别：

    Ribbon添加maven依赖 spring-starter-ribbon 使用@RibbonClient(value="服务名称") 使用RestTemplate调用远程服务对应的方法
    feign添加maven依赖 spring-starter-feign 服务提供方提供对外接口 调用方使用 在接口上使用@FeignClient("指定服务名")

    Ribbon和Feign都是用于调用其他服务的，不过方式不同。
    1.启动类使用的注解不同，Ribbon用的是@RibbonClient，Feign用的是@EnableFeignClients。
    2.服务的指定位置不同，Ribbon是在@RibbonClient注解上声明，Feign则是在定义抽象方法的接口中使用@FeignClient声明。
    3.调用方式不同，Ribbon需要自己构建http请求，模拟http请求然后使用RestTemplate发送给其他服务，步骤相当繁琐。
    Feign则是在Ribbon的基础上进行了一次改进，采用接口的方式，将需要调用的其他服务的方法定义成抽象方法即可，
    不需要自己构建http请求。不过要注意的是抽象方法的注解、方法签名要和提供服务的方法完全一致。
    
    源码大致实现过程如下：
    
    Feign内部通过几个组件进行请求的封装、调用和响应的解析。Contract实现上面接口方法的注解的解析以及请求的包装，Encoder实现上面接口方法参数的解析以及请求的组装，Feign.Builder实现核心类feign对象的建造器，Decoder实现响应的解析生成返回对象，Logger实现日志记录方式。这些接口都有默认实现，通过一个配置类配置了默认实现，默认实现为：
    
    Decoder feignDecoder: ResponseEntityDecoder(这是对SpringDecoder的封装)
    Encoder feignEncoder: SpringEncoder
    Logger feignLogger: Slf4jLogger
    Contract feignContract: SpringMvcContract
    Feign.Builder feignBuilder: HystrixFeign.Builder
    
    首先通过@EnableFeignCleints注解开启FeignCleint
    根据Feign的规则实现接口，并加@FeignCleint注解
    程序启动后，会进行包扫描，扫描所有的@ FeignCleint的注解的类，并将这些信息注入到ioc容器中。
    当接口的方法被调用，FeignClientFactoryBean通过jdk代理生成接口实例，来生成具体的RequesTemplate
    RequesTemplate在生成Request
    Request交给Client去处理，其中Client可以是HttpUrlConnection、HttpClient也可以是Okhttp
    最后Client被封装到LoadBalanceClient类，这个类结合类Ribbon做到了负载均衡。

springCloud断路器的作用

    当一个服务调用另一个服务由于网络原因或者自身原因出现问题时 调用者就会等待被调用者的响应 当更多的服务请求到这些资源时
    导致更多的请求等待 这样就会发生连锁效应（雪崩效应） 断路器就是解决这一问题
    断路器有完全打开状态：
            一定时间内 达到一定的次数无法调用 并且多次检测没有恢复的迹象 断路器完全打开，那么下次请求就不会请求到该服务
    半开：
            短时间内 有恢复迹象 断路器会将部分请求发给该服务 当能正常调用时 断路器关闭
    关闭：
            当服务一直处于正常状态 能正常调用 断路器关闭

Zuul

    zuul作为微服务网关，具有动态路由和过滤器链功能，基于zuul可以实现：
    认证&鉴权
    数据统计
    服务路由
    协助单点压测
    限流
    静态响应
    
    Zuul通过ZuulhanderMapping实现了SrpingMVC的AbstractUrlHandlerMapping，通过RouteLocator获取Route列表，映射对应route的fullPath到ZuulController，ZuulController继承了ServletWrappingController，会把对应请求代理到ZuulServlet，而ZuulServlet是zuul过滤器链的入口，过滤器链分三种类型，分别是pre、route、post分别对应请求执行前，请求路由执行，请求执行返回的操作，可以自定义各种过滤器实现特定需求。 
    解析下zuul的关键类：
    ZuulProperties是zuul的配置类，定义了prefix、stripPrefix、retryable等属性，表示请求前缀，忽略前缀、是否重试等，关键配置是routes，通过service的一个字符串对应到service url、serviceId等配置。
    ZuulConfiguration对应配置类的routes定义，通过SimpleRouteLocator将配置的routes解析使用AbstractUrlHandlerMapping的registerHandlers将对应path映射到ZuulController中处理， 通过@EnableZuulServer启用，这种方式对应静态路由。
    ZuulProxyConfiguration通过DiscoveryClientRouteLocator实现动态应用的路由加载，通过pre类型的filter PreDecorationFilter利用服务发现解析对应routes,通过route类型filter RibbonRoutingFilter 实现负载均衡的路由请求，通过@EnableZuulProxy启用，这种方式对应动态路由。
    
    使用非常简单
    Spring boot启动类增加注解@EnableZuulProxy开启zuul代理功能。
    配置 zuul.routes.express.path=/express/** 
    zuul.routes.express.serviceId=express-open-api 
    zuul.routes.express.stripPrefix=false 
    请求的不同path会路由到不同的服务，应用中我们使用zuul做了统一的api鉴权，参数处理，服务路由等功能。

demo：
https://github.com/glowwormX/note/tree/master/Java/springCloud/spring-cloud-parent
``` lua
spring-cloud-parent   
├── spring-cloud-eureka -- 8761 高可用注册中心，相互注册    
├── spring-cloud-eureka1 -- 8762 高可用注册中心，相互注册     
├── spring-cloud-config-server -- 8001 配置中心服务器，映射github上配置，使用Bus+RabbitMQ更新配置,/bus/refresh更新     
├── spring-cloud-provider -- 9000 服务提供者，向eureka注册   
├── spring-cloud-provider1 -- 9001 服务提供者1  有redis 
├── spring-cloud-provider2 -- 9002 使用spring cloud consul做注册中心
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

rabbitMQ安装   
https://www.jianshu.com/p/2d4b81c8b403   
http://blog.topspeedsnail.com/archives/4750   

实践   
http://tech.dianwoda.com/2017/09/04/spring-cloudxiang-guan-zu-jian-shi-jian/