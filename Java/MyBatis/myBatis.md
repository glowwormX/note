### 映射器  
Mybatis传多个参数（三种解决方案）   
https://www.2cto.com/database/201409/338155.html   
1、sql标签，引用其他id标签的sql语句（去重复，laravel模板引擎）  
2、级联操作  
assiciation 一对一  
collection 一对多  
discrimination 鉴别级联（男女体检项目实体类不同）  
3、动态代理方式  
JDK动态代理  
```java
// 接口  
public interface HelloService{  
    void sayHello(String name);  
}  
// 实现类  
public class HelloServiceImpl implements HelloService {  
    @Override  
    public void sayHello(String name) {
        System.out.println("hello"+name);
    }
}
// 代理类（bind绑定方法，invoke代理方法，通过代理对象首先进入这个方法）
public class HelloServiceProxy implements InvocationHandler {
    private Object target;
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("进入动态代理");
        Object result = null;
        System.out.println("动态代理前");
        method.invoke(target, args);
        System.out.println("动态代理后");
        return result;
    }
}
// 调用（创建代理类，bind绑定接口并返回实现类，调用方法）
    public static void main(String[] args) {
        HelloServiceProxy helloHandler = new HelloServiceProxy();
        HelloService proxy = (HelloService)helloHandler.bind(new HelloServiceImpl());
        //通过代理对象调用
        proxy.sayHello("xxx");
    }
```
CGLIB动态代理  
无需接口  
4、调用存储过程、游标、分表、分页  
配置参数 mode = OUT/IN   