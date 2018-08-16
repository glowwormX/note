Tomcat 9版本的主要特点:

1、添加对HTTP / 2和TLS虚拟主机的支持   
2、实现Servlet 4.0规范   
3、The BIO connectors, support for Windows Itanium and support for Comet have been removed   
4、Tomcat 9.0 is designed to run on Java SE 8 and later.    


作者：关中刀客在青岛
链接：https://www.zhihu.com/question/53784933/answer/201065726
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

Tomcat6：	Servlet 2.5、JSP 2.1、EL 2.1    
Tomcat7：	Servlet 3.0、JSP 2.2、EL 2.2    
Tomcat8：	Servlet 3.1、JSP 2.3、EL 3.0、Java WebSocket 1.0    

二、Servlet 3.1介绍   
Apache Tomcat 8支持Java EE 7规范，包括Java Servlet 3.1、JSP 2.3、Java统一表达式语言EL 3.0等。Servlet 3.1最大的改进是实现了非阻塞式的I/O通信。这使得性能得到巨大的改进，数据可以通过ReadListener和WriteListener接口以异步的方式进行管理。
Servlet 3.1的第二大改进是HTTP协议的升级。它允许客户端指定双方通信所使用的协议。比如说，服务器和客户端原本使用HTTP通信协议，能够升级成WebSockets通信。

三、JSP 2.3介绍  JSP 2.3相比2.2版本，并没有增加什么新功能或新特性，主要是维护和清理。

四、EL 3.0介绍   EL 3.0较之前的版本有很大的改进和增强。主要有：
1）EL可以以"独立模式"的方式运行，即运行于Servlets和JSP页面之外。
简而言之，此增强可以使用ELProcessor类在Servlet容器内或容器外进行直接调用。
2）EL表达式还支持Lambda表达式，这一点与Java 8相似，这些表达式可以简化代码。
表达式包括：参数集、Lambda操作符、函数体。
3）集合操作符允许构造Set、List和Map等集合类型。
4）Java EL流式API包含了forEach、reduce、filter、map等过滤器。
这里可以获得集合或列表List，调用字符串夫妇，或者在列表List运行外部的过滤器或聚合函数。比如，你可以对字符串求和。
5）通过EL上下文的导入，开发者能够直接访问任何类的静态字段和方法。

五、WebSocket 1.0介绍   
Tomcat 8正式支持WebSocket 1.0这个标准的API。尽管Tomcat 7也提供了支持，但是它只是部分支持，因为在Tomcat 7的时代，标准还未正式制定。目前，大多数网站还以半双工模式运行HTTP通信，这意味着通信在两端都可能发生，但是在同一时间点，只能有1个方向的通信。这有点像对讲机。
WebSockets协议是一个全双工协议，它意味着在同一时间点，通信是双向传输数据的。WebSockets也是HTML5的规范之一。

六、Tomcat 8内部的改进   
Tomcat 8最大的变化是资源Resource。Tomcat 8的资源进行了重构，可以更好地支持外部资源。原先的别名Aliases、VirtualLoader、VirtualDirContext、JAR资源和外部仓库等，现在都以单个的、一致的方法进行配置。这个新的资源的实现也可以用于主WAR包，并作为其它多个Web应用程序的基础。


此外，还有对SPDY协议的支持，这里还有些争议，因为SPDY还不够完善。


最后，Tomcat 8还有些安全方面的增强，主要是SSL加密提供了额外的诊断信息。
