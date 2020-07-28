<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" >
		//跳转页面
		var code = getQueryString('code');
	    if(code == '-1'){
	    	try {
	    		top.location.href = '${ctx}/login.html';
			}catch(err){
				window.location.href = '${ctx}/login.html';
			}
	    }else if(code == '404'){
	    	window.open('${ctx}/html/404.html?title=${pro_name}','error');
	    }else if(code == '403'){
	    	window.open('${ctx}/html/403.html?title=${pro_name}','error');
	    }else{
	    	window.open('${ctx}/html/500.html?title=${pro_name}','error');
	    }
	    //获取参数
	    function getQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null){
				return decodeURI(r[2]);
			}
			return null;
		}
		</script>
	</head>
</html>