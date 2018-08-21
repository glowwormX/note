java.util.concurrent
# 线程池
```java
public interface Executor
```
```java
public interface ExecutorService extends Executor
```
```java
public abstract class AbstractExecutorService implements ExecutorService
```

```java
public class ThreadPoolExecutor extends AbstractExecutorService 
```

```java
    public ThreadPoolExecutor(int corePoolSize,//线程池中正常线程数
                              int maximumPoolSize,//最大线程数
                              long keepAliveTime,//超过正常线程数的线程存活时间
                              TimeUnit unit,//keepAliveTime的单位
                              BlockingQueue<Runnable> workQueue,//任务队列
                              ThreadFactory threadFactory,//线程工厂
                              RejectedExecutionHandler handler)//拒绝策略
```
有任务->  
实际线程数 < corePoolSize:创建线程  
实际线程数 > corePoolSize:（  
队列没满:放入队列  
队列满了:（  
总线程数 < maximumPoolSize:创建新线程执行  
总线程数 > maximumPoolSize:拒绝策略  
））  
  
队列：  
直接提交的队列(容量为0，永远都是：队列满了)：SynchronousQueue  
有界的任务队列(容量为常数)：ArrayBlockingQueue  
无界的任务队列(容量为正无穷)：LinkedBlockingQueue  
优先任务队列(PriorityBlockingQueue,其他的队列为先进先出,这个可以有自己的优先顺序)  
```java
BlockingQueue 方法以四种形式出现，对于不能立即满足但可能在将来某一时刻可以满足的操作，
这四种形式的处理方式不同：
第一种是抛出一个异常，
第二种是返回一个特殊值（null 或 false，具体取决于操作），
第三种是在操作可以成功前，无限期地阻塞当前线程，
第四种是在放弃前只在给定的最大时间限制内阻塞。
 	抛出异常        	特殊值 	阻塞 	超时
插入 	add(e) 	       offer(e) put(e) 	offer(e, time, unit)
移除 	remove() 	poll() 	take() 	poll(time, unit)
检查 	element() 	peek() 	不可用 	不可用
```
```java
Executors 工厂
//固定数量的线程池
public class Executors {
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
//只有一个线程的线程池
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
//有线程复用就用，没有就创建，队列为0，线程总数无穷大
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
//
    public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return new DelegatedScheduledExecutorService
            (new ScheduledThreadPoolExecutor(1));
    }
}
```

测试代码:
```java
public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(5, 
				5, 
				0L, 
				TimeUnit.MILLISECONDS, 
				new SynchronousQueue<Runnable>(),
				new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread th =new Thread(r);
						System.out.println("create"+th);
						return th;
					}
				}
				);
		for (int i = 0; i < 5; i++) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println("Runnable");
				}});
		}
	}
}
```


# 重入锁 可以反复进入、可中断