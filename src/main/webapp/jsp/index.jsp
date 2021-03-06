<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/static/assets/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/assets/css/admin.css">
    <link rel="stylesheet" href="${ctx}/static/assets/css/view.css">
    <link rel="icon" href="${ctx}/static/assets/images/favicon.ico" />
    <title>${pro_name}</title>
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header custom-header">
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item slide-sidebar">
                    <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
                </li>
            </ul>
           	<ul class="layui-nav layui-layout-right" >        	
                <!-- <li class="layui-nav-item">
                    <a href="" id="refresh"><i class="layui-icon layui-icon-refresh-3" ></i></a>  
                </li> -->
                <li class="layui-nav-item">
                    <a href="javascript:;" style="font-size: 16px;">${user.username}</a>
                </li>
                <li class="layui-nav-item">
                    <a href="" id="out">
                    	<svg class="layui-icon-ali" aria-hidden="true">
							<use xlink:href="#layui-icon-ali-tuichu1"></use>
						</svg>
					</a>  
                </li>
           	</ul>
        </div>

        <div class="layui-side custom-admin">
            <div class="layui-side-scroll">
                <%-- <div class="custom-logo">
                    <img src="${ctx}/static/assets/images/logo.png" alt="" />
                    <h1 style="font-size: 15px;">后台管理系统</h1>
                </div> --%>
                <div class="custom-logo" style="text-align: center;">
                    <h1 style="font-size: 15px;margin: 0 50px 0 0">${pro_name}</h1>
                </div>
                <ul id="Nav" class="layui-nav layui-nav-tree"></ul>
            </div>
        </div>
        <div class="layui-body">
            <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
                <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
                <div id="appTabPage" class="layui-tab-content"></div>
            </div>
        </div>
        <div class="layui-footer fz-12">
            <!-- 底部固定区域 -->
            <p>Copyright © www.jfast.com, All Rights Reserved.</p>
            <p>创新</p>
        </div>
        <div class="mobile-mask"></div>
    </div>
    <%@ include file="footer.jsp"%>
    <script>
	    layui.use(['jquery','common','home'], function () {
	        var $ = layui.jquery, common = layui.common;
	        $('#out').on('click', function () {
	            common.ajax('post','${ctx}/login/out.html',null,function(res){
	            	if(res.success){
	            		window.location.href = '${ctx}/index.html';
	            	}
	            })
	        })
	        $('#refresh').on('click', function () {
	            common.ajax('get','${ctx}/refresh',null,function(res){
	            	if(res.success){
	            		window.location.href = '${ctx}/index.html';
	            	}
	            })
	        })
	    })
	</script>
</body>
</html>