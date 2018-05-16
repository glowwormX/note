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
