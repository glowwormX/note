## Stream流分组，统计，求和
```java
public class Test {

    public static void main(String[] args) {
        List<OrdersDO> list = new ArrayList<>();//查询昨天一天的所有交易
        OrdersDO o1 = new OrdersDO();
        o1.setAppId(1L);
        o1.setTradeAmount(100L);
        o1.setStatus(1);
        list.add(o1);
        OrdersDO o2 = new OrdersDO();
        o2.setAppId(5L);
        o2.setTradeAmount(300L);
        o2.setStatus(2);
        list.add(o2);
        OrdersDO o3 = new OrdersDO();
        o3.setAppId(1L);
        o3.setTradeAmount(100L);
        o3.setStatus(3);
        list.add(o3);
        OrdersDO o4 = new OrdersDO();
        o4.setAppId(5L);
        o4.setTradeAmount(300L);
        o4.setStatus(4);
        list.add(o4);
        OrdersDO o5 = new OrdersDO();
        o5.setAppId(5L);
        o5.setTradeAmount(300L);
        o5.setStatus(4);
        list.add(o5);
        //统计每个应用实际支付总额
        Map<Long, Long> tradeAmountMap = list.stream().filter(o->o.getStatus()==2)
                .collect(Collectors.groupingBy(OrdersDO::getAppId,
                        Collectors.summingLong(OrdersDO::getTradeAmount)));
        System.out.println(tradeAmountMap);

        //统计每个应用取消总额
        Map<Long, Long> cancelAmountMap = list.stream()
                .collect(Collectors.groupingBy(OrdersDO::getAppId,
                        Collectors.summingLong(OrdersDO::getTradeAmount)));
        System.out.println(cancelAmountMap);

        //统计每个应用下交易笔数
        Map<Long, Long> appTradeNum = list.stream().collect(Collectors.groupingBy(OrdersDO::getAppId, Collectors.counting()));
        System.out.println(appTradeNum);

        //统计每个应用每种状态下交易笔数
        Map<Long, Map<Integer, Long>> tradeNumMap = list.stream().
                collect(Collectors.groupingBy(OrdersDO::getAppId,
                        Collectors.groupingBy(OrdersDO::getStatus,
                                Collectors.counting())));
        System.out.println(tradeNumMap);

        //每个应用下交易笔数按数量排序
        Map<Long,Long> finalMap = new LinkedHashMap<>();
        appTradeNum.entrySet().stream().sorted(Map.Entry.<Long, Long>comparingByValue().reversed()).forEachOrdered(e->finalMap.put(e.getKey(),e.getValue()));
        System.out.println(finalMap);
    }
}
```
