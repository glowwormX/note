```java
bool类型 ?c转成true or false  或者myBool?string('yes', 'no')

combobox:
<input class="easyui-textbox" name="hasChild" id="hasChild" value="${bean.hasChild?c}" />
//写死
$('#hasChild').combobox({
    data:[{
          hasChild: "false",
          value: '无'
          },{
            hasChild: "true",
            value: '有'
          }],
    valueField: 'hasChild',
    textField: 'value',
    editable: false, //是否可编辑
    panelHeight: 'auto',
    //readonly: 'readonly'
});

//url获取字典
$('#depart_input').combobox({
  url: 'authority/listdept',
  valueField: 'departid',
  textField: 'departname',
  editable: false, //是否可编辑
  panelHeight: 'auto',
  readonly: 'readonly'
}); 

//不用js
<input class="easyui-combobox" data-options="
		valueField: 'label',
		textField: 'value',
		data: [{
			label: 'java',
			value: 'Java'
		},{
			label: 'perl',
			value: 'Perl'
		},{
			label: 'ruby',
			value: 'Ruby'
		}]" />


```

### combotree
```javascript
//选择某个节点
var tree = $('#in_participants').combotree('tree');//转换成父类tree才能用tree的方法
var node = tree.tree('find', "S13442");//寻找
tree.tree('check', node.target)//选择该节点 触发onBeforeSelect事件

//直接设置值，不会触发onBeforeSelect	
$('#in_participants').combotree("setValues",["S13442","CS001"])
```

### easUI前端渲染问题
1. 取checkbox值
$("#proddescribe").is(':checked')
1. textbox动态设置readonly属性1. 
$('#seriecode').attr('readonly','readonly');//失败   
$("#seriecode").textbox({'readonly':'readonly'});//成功  


### 请求
```
products 为全局变量
1、
	$.ajax({  
		type:'post',  
		url:'property/findAll', 
		data:{
			catalog:"防护等级",
		}
		dataType:'json',  
		async:true,  
		success:function(data){  
			products = data.rows;
		}  
	});  
2、
	$.post('property/findAll',{
		catalog:'防护等级',
		}, function(data) {
			products = data.rows;
		}, 'json');
3、
	$.getJSON("property/findAll?catalog=防护等级",function(data){
		products = data.rows;
	});
```
