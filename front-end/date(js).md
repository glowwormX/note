```javaScript
//js
  //获取前三个月的时间
  var date1 = new Date("2018-06-06");
  date1.setMonth(date1.getMonth()-2);//Date会自动判断month是否小于0，小于减小年份
  var year1=date1.getFullYear();
  var month1=date1.getMonth()+1;
  month1 =(month1<10 ? "0"+month1:month1);//格式化 mm
  date = (year1.toString()+'-'+month1.toString())+'-01';//2018-04-01
```
