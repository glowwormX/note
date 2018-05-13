

1.sleep：Thread类的方法，必须带一个时间参数。会让当前线程休眠进入阻塞状态并释放CPU，提供其他线程运行的机会且不考虑优先级，但如果有同步锁则sleep不会释放锁即其他线程无法获得同步锁

2.yield：Thread类的方法，类似sleep但无法指定时间并且只会提供相同或更高优先级的线程运行的机会，不推荐使用

3.wait：Object类的方法，必须放在循环体和同步代码块中，执行该方法的线程会释放锁，进入线程等待池中等待被再次唤醒(notify随机唤醒，notifyAll全部唤醒，线程结束自动唤醒)即放入锁池中竞争同步锁

4.join：一种特殊的wait，当前运行线程调用另一个线程的join方法，当前线程进入阻塞状态直到另一个线程运行结束

利用interrupt()改变线程状态为中断（不用interrupt不能马上中断sleep wait等）  
线程里sleep wait join会检测线程是否中断，中断则跑抛出InterruptException异常,线程finally里做终止最后的操作  

