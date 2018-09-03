## volatile 确保可见性，不保证原子性
低层转成汇编，有Lock指令   
1、Lock指令会引起处理器将线程独有内存（缓存）写入共享内存（以前的处理器多数锁定总线，现在处理器锁住部分共享内存区域）   
2、写回内存后会导致其他处理器的缓存无效   

* 满足下面条件才能使用volatile    
1、对变量的写入操作不依赖变量的当前值，或者确保只有一个线程更新变量   
2、该变量不会与其他状态变量一起纳入不变性条件中   
3、在访问变量时不需要加锁   

```java
class VolatileFeaturesExample {
    volatile long vl = 0L; // 使用volatile声明64位的long型变量

    public void set(long l) {
        vl = l; // 单个volatile变量的写
    }

    public void getAndIncrement() {
        vl++; // 复合（多个）volatile变量的读/写
    }
    
    public long get() {
        return vl; // 单个volatile变量的读
    }
}

//等价于下面，get set都具有原子性，但是getAndIncrement不在一个锁中，线程不安全
class VolatileFeaturesExample {
    long vl = 0L; // 64位的long型普通变量

    public synchronized void set(long l) { // 对单个的普通变量的写用同一个锁同步
        vl = l;
    }

    public void getAndIncrement() { // 普通方法调用
        long temp = get(); // 调用已同步的读方法
        temp += 1L; // 普通写操作
        set(temp); // 调用已同步的写方法
    }

    public synchronized long get() { // 对单个的普通变量的读用同一个锁同步
        return vl;
    }
}
```
