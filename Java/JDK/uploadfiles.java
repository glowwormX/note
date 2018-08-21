<form id="myform">
<table>
    <tr>
	<td>
	    <input type="file" id="file" name="file" />
	</td>
	<td>
	    <input type="text" id="sampleid" name="sampleid" />
	</td>
    </tr>
    <tr>
	<td>
	    <input type="file" id="file" name="file" />
	</td>
	<td>
	    <input type="text" id="sampleid" name="sampleid" />
	</td>
    </tr>
</table>
</form>
<input type="button" onclick="SubmitForm()" value="提交" />
    
<script type="text/javascript">
	function SubmitForm() {
		//获取表单中的数据
		var file = document.getElementById('myform');
		//FormDat对象
		var formobj = new FormData(file);
		//XMLHttpRequest对象
		var xmlobj = new XMLHttpRequest();
		//指定提交类型和选择要发送的地址
		xmlobj.open('post', 'http://localhost:8080/selfProduct/file/upload');
		// xmlobj.withCredentials = true; //设置传递cookie，如果不需要直接注释就好
		// xmlobj.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		// xmlobj.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		//发送数据
		xmlobj.send(formobj);
		xmlobj.onload = function () {
		    alert(xmlobj.responseText);//获取后台返回的数据
		}
	}

//参考：
//      var xmlHttp;  

//   function AjaxFunction(){  
//           createXMLHttpRequest();  
//           if(xmlHttp!=null){  
//       xmlHttp.onreadystatechange = callBack;  
//       xmlHttp.open("get/Post","URL",true/false);  
//       xmlHttp.send(null);  
//           }     
//   }     

//   //实例化XMLHttpRequest对象  
//   function createXMLHttpRequest(){  
//           if(window.XMLHttpRequest){  
//       xmlHttp = new XMLHttpRequest();   
//           }else if(window.ActiveXObject){  
//       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  
//           }  
//   }  

//   //指定响应函数  
//   function callBack(){  
//           if(xmlHttp.readyState==4){  
//                   if(xmlHttp.status==200){  
//               //do something with xmlHttp.responseText;  
//               xmlHttp.responseText;  
//                   }     
//           }  
//   } 
</script>

//后端接收参数  与前端form name标签相同，数组之间一一对应，未多次验证是否会出错
public Map<String, String> upload(@RequestParam("file") CommonsMultipartFile[] upfiles,
		@RequestParam("sampleid") String[] sampleids) throws IOException {
	...
}

//封装成对象传入：
@RequestMapping("upload1")
@ResponseBody
public void upload1(Files files) throws IOException {
	...
}
public class Files {
	private List<ModelFile> files;
	//geter...seter...
}
public class ModelFile {
	private CommonsMultipartFile file;
	private String sampleid;
	//geter...seter...
}

//前端
<form id="myform">
<table>
    <tr>
	<td>
	    <input type="file" id="file" name="files[0].file" />
	</td>
	<td>
	    <input type="text" id="sampleid" name="files[0].sampleid" />
	</td>
    </tr>
    <tr>
	<td>
	    <input type="file" id="file" name="files[1].file" />
	</td>
	<td>
	    <input type="text" id="sampleid" name="files[1].sampleid" />
	</td>
    </tr>
</table>
</form>
<input type="button" onclick="SubmitForm()" value="提交" />
