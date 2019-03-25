### 嵌套类(定义在一个类的内部的类)：静态成员类、非静态成员类、局部类(方法中)、匿名类   
1. 只需要一个地方创建实例，且有一个预置的类型说明这个类的特征（Comparator、Thread等），则使用匿名类，不然可以用局部类
1. 在方法之外仍然可见或者太长，则使用成员类而非局部类
1. 静态、非静态：如果每个成员类的实例都需要一个指向其外围实例的应用，则使用非静态，不然使用静态

### 泛型
使用List<?>替代List
    泛型              List<E>
    无限制通配符       List<?>
    有限制类型参数     <E extends Number> <E super Number>
    有限制通配符类型   <? extends Number>
    递归类型限制       <T extends Comparable<T>>
    泛型方法          static <E> List<E> asList(E[] a)
