//需求：多行记录保存，前台提交多行，ajax异步
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
