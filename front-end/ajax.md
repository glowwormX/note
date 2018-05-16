##ajax提交表单
*  表单序列化成 a=1&b=1&c=1提交，后台springmvc变量名与input[name]相同接收

```javaScript
$.ajax({
  type: "POST",
  dataType: "json",
  url: "../totalEvaluation/save",
  data: $('#total_form').serialize(),
  success: function (data) {
      if (data.statusCode == 200) {
          window.parent.$('#tb_total_eval').datagrid('reload');//重载数据
          showmessage(data.message);
          $("#in_id").val(data.id);
          window.parent.$('#dv_total').dialog("close");
      } else {
          $.messager.alert('警告', data.message, "warning");

      }
  },
});
```
*  以json形式提交 先将form装换成对象，再序列化成json
```javaScript
var formObject = {};
var formArray =$("#total_form").serializeArray();
$.each(formArray,function(i,item){
  formObject[item.name] = item.value;
});
var json = JSON.stringify(formObject);
 $.ajax({
    type: 'post',
    contentType:'application/json;charset=UTF-8',//json传输
    traditional:true,
    url: '../totalEvaluation/savePtePsp',
    data: json,
    dataType: 'json',
    success: function (data) {
      if (data.statusCode == 200) {
            window.parent.$('#tb_total_eval').datagrid('reload');//重载数据
            showmessage(data.message);
            $("#in_id").val(data.id);
            window.parent.$('#dv_total').dialog("close");
        } else {
            $.messager.alert('警告', data.message, "warning");

        }
    }
});
```

*  //需求：多行记录保存，前台提交多行，ajax异步
```java
//后台：@RequestBody List<对象>
@RequestMapping("/saveWeight")
@ResponseBody
public Map<String, String> saveWeight(@RequestBody List<ProjectInfo> projectInfos) {
  Map<String, String> map = new HashMap<String, String>();
  map.put("statusCode", "200");
  map.put("message", "保存成功！");
  return map;
}
  
//前端：
function saveWeight() {
    var tb_data = $('#tb_weight').datagrid('getData');//获取数据table,对象为{totals:2,rows:[数组]}
    $.ajax({
        type: 'post',
        contentType:'application/json;charset=UTF-8',//关键是要加上这行
        traditional:true,//这使json格式的字符不会被转码
        url: '../projectManagement/saveWeight',
        data: JSON.stringify(tb_data.rows),//提交一个数组
        dataType: 'json',
        success: function (data) {
        }
    });
}
```
