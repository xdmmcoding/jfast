<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="head.jsp"%>
<%@ include file="footer.jsp"%>
<script type="text/javascript" >
    var code = "${code}";
    if(code == 'nosession'){
    	try {
    		top.location.href = '${ctx}/login.html';
		}catch(err){
			window.location.href = '${ctx}/login.html';
		}
    }else if(code == '404'){
    	window.open('${ctx}/html/404.html','error');
    }else if(code == '403'){
    	window.open('${ctx}/html/403.html','error');
    }else{
    	window.open('${ctx}/html/500.html','error');
    }
</script>