Eureka：服务治理：注册和发现，Eureka客户端向Eureka服务端注册、发现微服务  
Ribbon：负载均衡（微服务对内调用），通过Eureka Server获取服务列表、再通过负载均衡算法调用微服务  
Hystrix：实现断路器、线程隔离、信号隔离等容错功能  
Zuul：网关（对外调用），路由转发，如以"user/"开头都到user微服务去  
Config：配置文件管理  
Bus：微服务之间消息队列，松耦合  