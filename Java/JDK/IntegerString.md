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
```

* String

存在于.class文件中的常量池，在运行期间被jvm装载，并且可以扩充。String的intern()方法就是扩充常量池的一个方法；当一个String实例str调用intern()方法时，java查找常量池中是否有相同unicode的字符串常量，如果有，则返回其引用，如果没有，则在常量池中增加一个unicode等于str的字符串并返回它的引用。  
例3：  
```
        String str = new String("abc");//先在常量池中创建，再在堆中创建
        String str1 = "abc";//直接返回常量池中的引用
        String str2 = new String("abc");//根据常量池中已经存在的值在堆中创建另一个对象

        System.out.println(str == str1);//false
        System.out.println(str == str2);//false
        System.out.println(str1 == str2);//false
        System.out.println(str == str.intern());//false str.intern()返回常量池中的引用

        System.out.println(str1 == str1.intern());//true
        System.out.println(str.intern() == str2.intern());//true

        String hello = "hello";
        String hel = "hel";
        String lo = "lo";

        System.out.println(hello == "hel" + "lo");//true 编译时直接将两个相加，放到常量池
        System.out.println(hello == "hel" + lo);//false 运行时才相加，放到堆中
```
