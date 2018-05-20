* Integer
1、-128到128之间 Integer i = 10 这样的创建方式会被缓存，new则不会   
2、Integer 与 int ==操作符时会将Integer自动拆箱
```Java
Integer a = 10;
Integer b = 10;
Integer c = new Integer(10);
a==b //true
a==c //false

Integer a = 1000;
Integer b = 1000;
int c = 1000;
a==b //false
a==c //true
Integer e = new
```

* String

存在于.class文件中的常量池，在运行期间被jvm装载，并且可以扩充。String的intern()方法就是扩充常量池的一个方法；当一个String实例str调用intern()方法时，java查找常量池中是否有相同unicode的字符串常量，如果有，则返回其引用，如果没有，则在常量池中增加一个unicode等于str的字符串并返回它的引用。  
例3：  
```
String s0=”kvill”;
String s1=new String(“kvill”);
String s2=new String(“kvill”);
System.out.println(s0==s1);
S1.intern();
S2=s2.intern();
System.out.println(s0==s1);
System.out.prntln(s0==s1.intern());
System.out.println(s0==s2);
```
结果为：  
```
False
False //虽然执行了s1.intern()，但它的返回值没有赋给s1
True
True
```
最后再破除一个错误的理解：  
有人说，“使用String.intern()方法可以将一个String类保存到一个全局的String表中，如果具有相同值的unicode字符串已经在这个表中，那么该方法返回表中已有字符串的地址，如果在表中没有相同值的字符串，则将自己的地址注册到表中”如果把这个全局的String表理解为常量吃的话，最后一句话“如果在表中没有相同值的字符串，则将自己的地址注册到表中”是错的。  
例4：  
```
String s1=new String(“kvill”);
String s2=s1.intern();
System.out.println(s1==s1.intern());
System.out.println(s1+” ”+s2);
System.out.println(s2==s1.intern());
```
结果是：  
```
False
Kvill kvill
True
```
我们没有声明一个”kvill”常量，所以常量池中一开始没有”kvill”的，当我们调用s1.intern()后就在常量池中新添加了一个”kvill”常量，原来的不在常量池中的”kvill”仍然存在，也就不是“把自己的地址注册到常量池中”了。  
例5：  
```
String str1=”java”;
String str2=”blog”;
String s=str1+str2;
System.out.println(s==”javablog”);
```
结果是false。Jvm确实对型如String str1=”java”;的String对象放在常量池里，但是它是在编译时那么做的，而String s=str1+str2;是在运行时刻才能知道，也就是说str1+str2是在堆里创建的，所以结果为false了。   
