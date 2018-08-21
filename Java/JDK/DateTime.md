Date与String 装换
```java
  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String timeStr = df.format(new Date());
  Date timeDate = df.parse(timeStr);
```

Time计算   
需求：
如果今天下午3点前进行下单，那么发货时间是明天，   
如果今天下午3点后进行下单，那么发货时间是后天，   
如果被确定的时间是周日，那么在此时间上再加1天为发货时间。   
```java
  final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15,0,0,0);
  private Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime){//计算发布时间
      DateTime orderCreateDateTime = new DateTime(orderCreateTime);
      Date tomorrow = orderCreateDateTime.plusDays(1).toDate();
      Date theDayAfterTomorrow = orderCreateDateTime.plusDays(2).toDate();
      return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(theDayAfterTomorrow) : wrapDistributionTime(tomorrow);
  }
  private Date wrapDistributionTime(Date distributionTime){
      DateTime currentDistributionDateTime = new DateTime(distributionTime);
      DateTime plusOneDay = currentDistributionDateTime.plusDays(1);
      boolean isSunday = (DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek());
      return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate() ;
  }
```
