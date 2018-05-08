```java
bool类型 ?c转成true or false  或者myBool?string('yes', 'no')

combox:
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
