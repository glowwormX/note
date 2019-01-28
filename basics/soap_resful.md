
## soa、soap、WebService、WSDL、restful等一些概念   
soa:面向服务架构，微服务：粒度更小    
soap:基于http和xml的协议 https://www.cnblogs.com/huanghongbo/p/5920123.html   
restful:对数据的crud，无状态，轻量，支持多种数据格式 https://blog.csdn.net/defonds/article/details/49000993   
WebService:在服务器上基于某种协议的程序，可以为soap和rest https://stevenjohn.iteye.com/blog/1442776   
```
  Restful url只用名词不用动词
  REST对于资源型服务接口来说很合适，同时特别适合对于效率要求很高，但是对于安全要求不高的场景。
  而SOAP的成熟性可以给需要提供给多开发语言的，对于安全性要求较高的接口设计带来便利。
  所以我觉得纯粹说什么设计模式将会占据主导地位没有什么意义，关键还是看应用场景。 
```
WSDL:描述WebService的语言，用xml描述将服务器上的接口调用方式等(应该只支持soap)   
