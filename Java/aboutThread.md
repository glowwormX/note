

1.sleep：Thread类的方法，必须带一个时间参数。会让当前线程休眠进入阻塞状态并释放CPU，提供其他线程运行的机会且不考虑优先级，但如果有同步锁则sleep不会释放锁即其他线程无法获得同步锁

2.yield：Thread类的方法，类似sleep但无法指定时间并且只会提供相同或更高优先级的线程运行的机会，不推荐使用

3.wait：Object类的方法，必须放在循环体和同步代码块中，执行该方法的线程会释放锁，进入线程等待池中等待被再次唤醒(notify随机唤醒，notifyAll全部唤醒，线程结束自动唤醒)即放入锁池中竞争同步锁

4.join：一种特殊的wait，当前运行线程调用另一个线程的join方法，当前线程进入阻塞状态直到另一个线程运行结束

利用interrupt()改变线程状态为中断（不用interrupt不能马上中断sleep wait等）  
线程里sleep wait join会检测线程是否中断，中断则跑抛出InterruptException异常,线程finally里做终止最后的操作  

```java
public class SimpleThreads {  

    // Display a message, preceded by  
    // the name of the current thread  
    static void threadMessage(String message) {  
        String threadName =  
            Thread.currentThread().getName();  
        System.out.format("%s: %s%n",  
                          threadName,  
                          message);  
    }  

    private static class MessageLoop  
        implements Runnable {  
        public void run() {  
            String importantInfo[] = {  
                "Mares eat oats",  
                "Does eat oats",  
                "Little lambs eat ivy",  
                "A kid will eat ivy too"  
            };  
            try {  
                for (int i = 0;  
                     i < importantInfo.length;  
                     i++) {  
                    // Pause for 4 seconds  
                    Thread.sleep(4000);  
                    // Print a message  
                    threadMessage(importantInfo[i]);  
                }  
            } catch (InterruptedException e) {  
                threadMessage("I wasn't done!");  
            }  
        }  
    }  

    public static void main(String args[])  
        throws InterruptedException {  

        // Delay, in milliseconds before  
        // we interrupt MessageLoop  
        // thread (default one hour).  
        long patience = 1000 * 60 * 60;  

        // If command line argument  
        // present, gives patience  
        // in seconds.  
        if (args.length > 0) {  
            try {  
                patience = Long.parseLong(args[0]) * 1000;  
            } catch (NumberFormatException e) {  
                System.err.println("Argument must be an integer.");  
                System.exit(1);  
            }  
        }  

        threadMessage("Starting MessageLoop thread");  
        long startTime = System.currentTimeMillis();  
        Thread t = new Thread(new MessageLoop());  
        t.start();  

        threadMessage("Waiting for MessageLoop thread to finish");  
        // loop until MessageLoop  
        // thread exits  
        while (t.isAlive()) {  
            threadMessage("Still waiting...");  
            // Wait maximum of 1 second  
            // for MessageLoop thread  
            // to finish.  
            t.join(1000);  
            // 如果时间过长，则中断t线程
            if (((System.currentTimeMillis() - startTime) > patience)  
                  && t.isAlive()) {  
                threadMessage("Tired of waiting!");  
                t.interrupt();  
                // Shouldn't be long now  
                // -- wait indefinitely  
                t.join();  
            }  
        }  
        threadMessage("Finally!");  
    }  
}  
```java
