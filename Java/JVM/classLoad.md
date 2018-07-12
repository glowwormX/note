虚拟机规范有且只有5中情况进行初始化
1. new、getstatic、pubstatic、invokestatic 四个字节码指令会触发初始化   
调用静态方法字段会触发getstatic、pubstatic、invokestatic（final修饰的字段预编译是会放入常量池）
1. java.lang.reflect 放射调用时进行初始化
1. 初始化某个类时，其父类还未初始化则先初始化父类
1. 虚拟机启动时用户执行的主类（main方法类）
1. jdk1.7 动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、REF_putStatic、REFinvokeStatic 的句柄，且这个句柄所对应的类没有进行过初始化（？）
