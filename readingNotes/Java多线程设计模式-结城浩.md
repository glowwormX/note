### Immutable 不变模式  
final字段（java.lang.String Boolean）  
### Guarded Suspension  要等我准备好  
### Balking 不需要的话就算了吧
### Producer-Consumer 生产者-消费者模式  
### Read—Write Lock 可以随便看，看的时候不能写，写时锁定
### Thread—Per—Message 这个工作就交给你了
### Worker Thread 等工作来，来了就工作
### Future  先给你这张提货单
### Two-Phase Termination 把玩具收拾好，去睡觉  
利用interrupt()改变线程状态为中断（不用interrupt不能马上中断sleep wait等）  
线程里sleep wait join会检测线程是否中断，中断则跑抛出InterruptException异常,线程finally里做终止最后的操作  