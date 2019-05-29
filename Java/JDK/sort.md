1.常用排序工具类java.util.Arrays和java.util.Collections，不过底层都使用了Arrays.sort()   
2.使用TreeSet
```java
       TreeSet<String> treeSet = new TreeSet<String>();
        String[] strs = new String[]{"3","5","6","2"};
        for (int i = 0; i < strs.length; i++) {
            treeSet.add(strs[i]);
        }

        //倒序
        Iterator iterator = treeSet.descendingIterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        //升序
        Iterator iterator1 = treeSet.iterator();
        while(iterator1.hasNext()){
            System.out.print(iterator1.next()+" ");
        }
```

Arrays.sort()源码解读   
https://www.jianshu.com/p/d7ba7d919b80   
