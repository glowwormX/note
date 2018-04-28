# input标签  
### 单选框  
默认选中 <input type="radio" checked="checked" >  
分组即，多个单选框，都在一个分组里，同一时间，只能选中一个单选框，设置name属性相同即可 
```  
dota<input type="radio" name="activity" value="dota" > <br/>  
LOL<input type="radio" name="activity"  value="lol"> <br/>  
```
### 复选框
```
<p>今天晚上做什么？</p>
学习java<input type="checkbox" value="学习java" > <br/>
东京热<input type="checkbox" checked="checked"  name="activity" value="tokyohot" > <br/>
dota<input type="checkbox" value="dota" > <br/>
LOL<input type="checkbox" value="lol"> <br/>
```
### 下拉列表  
```
<select >
 <option >苍老师</option>
 <option >高树玛利亚</option>
 <option >遥美</option>
</select>
//---size显示几列，multiple是否多选，selected默认选中---
<select size="3" multiple="multiple">
 <option >苍老师</option>
 <option selected="selected">高树玛利亚</option>
 <option selected="selected">遥美</option>
</select>
```
### 文本域
```
<textarea>abc
def
</textarea>
``` 
### 重置按钮  
`<input type="reset" value="重置">`

# 选择器  
## css的三种选择器  
```
1、选择所有p标签
<style>
p{ <!-- 选择所有p标签 -->
  color:red;
}
#p1{ <!-- 选择所有id为p1的标签 -->
  color:blue;
}
.class1{ <!-- 选择所有class为class1的标签 -->
  color:blue;
}
</style>
p.blue{ <!-- class为"blue"的标签 -->
  color:blue;
}
```
## jquery选择器  
```
$("p")  $("#p1")  $(".class1")  

$("selector1 selector2")  :选择 selector1下的selector2元素 。 

$("selector:first") 满足选择器条件的第一个元素  
$("p:first")
$("selector:last") 满足选择器条件的最后一个元素   

$("selector:odd") 满足选择器条件的奇数元素
$("selector:even") 满足选择器条件的偶数元素
因为是基零的，所以第一排的下标其实是0(是偶数) 


```

# 位置
```
HTML DOM position 属性   
static 	           默认。位置设置为 static 的元素，它始终会处于页面流给予的位置
                   （static 元素会忽略任何 top、bottom、left 或 right 声明）。
relative 	位置被设置为 relative 的元素，可将其移至相对于其正常位置的地方，
                 因此 "left:20" 会将元素移至元素正常位置左边 20 个           像素的位置。
absolute 	位置设置为 absolute 的元素，可定位于相对于包含它的元素的指定坐标。
                 此元素的位置可通过 "left"、"top"、"right" 以及                     "bottom" 属性来规定。
fixed 	          位置被设置为 fixed 的元素，可定位于相对于浏览器窗口的指定坐标。
                  此元素的位置可通过 "left"、"top"、"right" 以及"bottom" 属性来规定。不论窗口滚动与否，元素都会留在那个位置。
                 工作于 IE7（strict 模式）。

```
# 文档流
从上到下   
引入外部文件(js，css)则相当于入栈   
$(function(){...})加载完成后   
js先对var变量和function进行预编译，var变量均为undefined   
document.write()比较复杂，延迟加载，不会立即入栈，先载完本页面再入栈   
