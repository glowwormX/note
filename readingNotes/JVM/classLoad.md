虚拟机规范有且只有5中情况进行初始化
1. new、getstatic、pubstatic、invokestatic 四个字节码指令会触发初始化   
调用静态方法字段会触发getstatic、pubstatic、invokestatic（final修饰的字段预编译是会放入常量池）
1. 
