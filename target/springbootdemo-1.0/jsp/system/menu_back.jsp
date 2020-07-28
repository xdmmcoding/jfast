<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../head.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/static/assets/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/assets/css/view.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/css/global.css">  
    <link rel="stylesheet" href="${ctx}/static/assets/css/eleTree.css">
    <title></title>
</head>

<body class="attestationInfo layui-view-body">
    <div class="layui-content">
        <div class="layui-row">
        	<div class="layui-col-sm3 layui-col-md3">
                <div class="layui-card">
                    <div class="layui-card-header">菜单</div>                   
	                <div id="menutree" class="demo-tree-more" lay-filter="menudata"></div>
                </div>
            </div>
            <div class="layui-col-sm9 layui-col-md9">
                <div class="layui-card">
                    <div class="layui-card-header">菜单列表</button></div>
                    <form class="tableHeader layui-form">
                        <div class="layui-col-xs12 layui-col-sm10 layui-col-md10 ">
                            <div class="layui-form-item">
                                <div class="layui-inline mar-t-10">
                                    <label class="layui-form-label" style="width:70px">菜单名称：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" value="" style="width: 180px" placeholder="请输入菜单名称"
                                            autocomplete="off" class="layui-input" id="roleName">
                                    </div>
                                </div>
                                <div class="layui-inline mar-t-10">
                                    <label class="layui-form-label" style="width:70px">菜单状态：</label>
                                    <div class="layui-input-inline">
                                        <select style="height:38px;width: 180px" data-url="${ctx}/dict/selected?type=menustate" id="state"></select>
                                    </div>
                                </div>
                                <div class="layui-inline mar-t-10" style="margin-left:15px">
                                    <button class="layui-btn layui-btn-blue" lay-submit="" id="query">查询</button>
                                    <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
                                    <button type="button" id="add" class="layui-btn layui-btn-blue"><i class="layui-icon">&#xe654;</i>增加</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div style="clear:both"></div>
                    <div class="layui-card-body">
                        <table class="layui-table" id="datagrid" lay-filter="datagrid"></table>
                    </div>
                </div>
            </div>
            <!-- edit add  弹框 -->
            <form class="layui-form mar-t-20" id="add-form" action="" style="display:none">
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px"><span class="mustFill"></span>菜单名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="text" value="" style="width:400px"
                            lay-verify="required" maxlength="50" placeholder="请输入菜单名称" lay-reqtext="请输入菜单名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px"><span class="mustFill"></span>菜单编码</label>
                    <div class="layui-input-block">
                        <input type="text" name="code" value="" style="width:400px"
                            lay-verify="required" maxlength="50" placeholder="请输入菜单编码" lay-reqtext="请输入菜单编码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">菜单地址</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" value="" style="width:400px" maxlength="50" placeholder="请输入菜单地址"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">菜单图标</label>
                    <div class="layui-input-block">
                        <input type="text" name="icon" value="" style="width:400px" maxlength="50" placeholder="请输入菜单图标"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">排序</label>
                    <div class="layui-input-block">
                        <input type="text" name="sort" value="" style="width:400px" maxlength="50" placeholder="请输入排序" lay-reqtext="请输入排序" autocomplete="off" class="layui-input">
                    </div>
                    <input type="hidden" name="id"/>
                    <input type="hidden" name="parentCode"/>
                </div>
                <div class="layui-form-item" style="width:570px;position:absolute;bottom:10px;">
                    <div class="layui-input-block text-c" style="margin-left:0">
                        <button class="layui-btn layui-btn-blue" lay-submit lay-filter="save">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <style>
        .cardButton {
            position: absolute;
            right: 15px;
            top: 6px;
        }

        .layui-layer-content {
            padding: 0 15px;
        }
        .layui-table-view .layui-form-checkbox {
    		top: 3px!important;
    	}
    </style>
</body>
<%@ include file="../footer.jsp"%>
<script>
    layui.use(['jquery', 'table', 'form', 'layer','common','eleTree'], function (obj) {
        var $ = layui.jquery, table = layui.table, layer = layui.layer, form = layui.form, common = layui.common,etree = layui.eleTree,el;
     	var parentCode = '00';
     	//初始化菜单树
     	common.ajax('post','${ctx}/menu/get/all.html',null,function(res){
      		if(res.success){
	      		// 树状渲染
	   	        el = etree.render({
	   	            elem: '#menutree',
	   	            data: make_tree(res.data),
	   	         	expandOnClickNode: false,
	   	         	highlightCurrent:true,
	   	        	checkOnClickNode: true,
		   	        searchNodeMethod: function(value,data) {
		   	          if (!value) return true;
		   	          return data.label.indexOf(value) !== -1;
		   	      	}
	   	        });
	   	   		//展开所有
	      		el.expandAll();
	   	   		//
	   	   		etree.on("nodeClick(menudata)",function(d) {
	   	   			parentCode = d.data.currentData.code;
	   	   			query();
				})
      		}
      	});
	 	// 表格渲染
        table.render({
            elem: '#datagrid',
            height: 'full',
            cols: [[
	            { field: 'text', title: '菜单名称', width: 200},
                { field: 'code', title: '菜单编码' },
                { field: 'url', title: '菜单地址' },
                { field: 'updateTime', title: '更新时间', width: 200, templet: function (d) {
                    	return common.formatDatetimebox(d.updateTime);
                  } 
                },
                { field: 'state', title: '是否启用', width: 100, templet: function (d) {
                        if (d.state === '0') {
                            return '<input type="checkbox" checked name="open" lay-skin="switch" lay-text="是|否">';
                        } else {
                        	return '<input type="checkbox"  lay-skin="switch" lay-text="是|否">';
                        }
                  },event: 'isstart'
                },
                { field: 'caozuo', title: '操作', width: 240, templet: function (d) {
                    	return '<a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="delete">删除</a>'                       
                  }
                }
            ]],
            url:'${ctx}/menu/pagination.html',
            method:'post',
            page: true,  //是否显示分页
            limits: [10, 20, 30, 50],
            limit: 10, //每页默认显示的数量
            where:where(),
 			text: {
                none:'<div class="auto text-c noRegister" style="width:210px;padding:50px 0 80px 0;" >'+
	                 	'<img style="width:140px;" src="${ctx}/static/assets/images/text.png" alt="">'+
	                    '<p class="fw-600 fz-18 cr-5a5a5a  mar-t-10">请选择要查询的父菜单</p>'+
	            	 '</div>'
            },
        });
        table.on('tool(datagrid)', function (res) {
	        var data = res.data;
	        if (res.event === 'edit') {//编辑
	        	aeFormLayer('编辑','edit',data);
	        } else if (res.event === 'delete') {//删除
	        	del(data);
	        } else if (res.event === 'isstart') {//是否启用
	        	if(data.roleType != '1')isstart(data);
	        }
	    });
	    
	    function where(){
     		return {
     			text:$('#text').val(),
     			state:$('#state').val(),
     			parentCode:parentCode,
     		};
     	}
	    function query(){
	    	table.reload('datagrid',{
     			where:where(),
     			page:{curr: 1},
     			text: {
                    none:'<div class="auto text-c noRegister" style="width:210px;padding:50px 0 80px 0;" >'+
		                 	'<img style="width:140px;" src="${ctx}/static/assets/images/text.png" alt="">'+
		                    '<p class="fw-600 fz-18 cr-5a5a5a  mar-t-10">未查询到相关数据</p>'+
		                    '<p class="cr-666 fz-14">建议您修改相关查询条件重新再试</p>'+
		            	 '</div>'
                },
     		})
     	}
     	//查询
     	$(document).on('click','#query',function(){
     		query();
     		return false;
        });
     	//查询
     	$(document).on('click','#closeBtn',function(){
     		$('textarea[name="description"]').text('');
     		$('.text_count').html('0');
        });
     	//新增
     	$(document).on('click','#add',function(){
     		aeFormLayer('增加','add',null);
        });
     	var aeIndex;
        // 弹层和表单验证
        function aeFormLayer(title,type,data) {
            //页面层
            layer.open({
                type: 1,
                title: title,
                shadeClose: true,
                area: ['600px', '430px'], //宽高
                content: $("#add-form"), //调到新增页面
                success: function (layero, index) {
                	aeIndex = index;
                	if(type === 'edit'){
                		$("input[name='text']").val(data.text);
                		$("input[name='code']").val(data.code);
                		$("input[name='url']").val(data.url);
                		$("input[name='icon']").val(data.icon);
                		$("input[name='parentCode']").val(data.parentCode);
                		$("input[name='id']").val(data.id);
                	}else{
                		$("input[name='text']").val('');
                		$("input[name='code']").val('');
                		$("input[name='url']").val('');
                		$("input[name='icon']").val('');
                		$("input[name='parentCode']").val(parentCode);
                		$("input[name='id']").val('');
                	}
                },
                yes: function () {
                	
                },
                end: function () {
                	
                }
            });
        }
		//菜单信息保存
        form.on('submit(save)', function (data) {
		    //表单数据formData
		    common.ajax('post','${ctx}/menu/submit.html',data.field,function(res){
		    	if(res.success){
		    		window.location.reload();
		    	}else{
		    		if(res.message){
		    			layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
		    		}
		    	}
		    })
		    return false;
		});
		//
		function del(data){
			layer.confirm('确定删除?', function (index) {
				common.ajax('post','${ctx}/menu/del.html?id='+data.id,null,function(res){
			    	if(res.success){
			    		window.location.reload();
			    	}else{
			    		if(res.message){
			    			layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
			    		}
			    	}
			    })
			})
		}
		//是否启用
		function isstart(data){
			var state = data.state == '0'?'-1':'0';
			common.ajax('post','${ctx}/menu/isstart.html',{id:data.id,state:state},function(res){
		    	if(res.success){
		    		query();
		    	}else{
		    		if(res.message){
		    			layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
		    		}
		    		query();
		    	}
		    })
		}
		
    });
  	//递归成树
  	function make_tree(datas){
		var dataArray = [];		
		var objp = {
        		code:'00',
        		parentCode:'',
        		label:"<span style='color:#AAA'>菜单</span>",//节点标题	String	未命名
        		id:0,//节点唯一索引值，用于对指定节点进行各类操作	String/Number	任意唯一的字符或数字
        		field:'',//节点字段名	String	一般对应表字段名
        		children:[]//
        }
        dataArray.push(objp);
	    for(var j = 0; j < datas.length; j++) {
	    	var data = datas[j];
	        var parentCode = data.parentCode;
	        if(parentCode == '' || parentCode == null) {
	            var obj = {
	            		code:data.code,
	            		parentCode:data.parentCode,
	            		label:data.text+"&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#AAA'><"+data.code+"></span>",//节点标题	String	未命名
	            		id:data.id,//节点唯一索引值，用于对指定节点进行各类操作	String/Number	任意唯一的字符或数字
	            		field:'',//节点字段名	String	一般对应表字段名
	            		children:[]//
	            }
	            dataArray.push(obj);
	        }
	    }
	    return recursive_tree(datas, dataArray);
	}
	function recursive_tree(datas, dataArray) {
	    for(var j = 0; j < dataArray.length; j++) {
	        var dataArrayIndex = dataArray[j];
	        var childrenArray = [];
	        var catl_idp = dataArrayIndex.code;
	        for(var i = 0; i < datas.length; i++) {
	            var data = datas[i];
	            var parentCode = data.parentCode;
	            if(parentCode == catl_idp) {//判断是否为儿子节点
	            	var obj = {
	            			code:data.code,
		            		parentCode:data.parentCode,
		            		label:data.text+"&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#AAA'><"+data.code+"></span>",//节点标题	String	未命名
		            		id:data.id,//节点唯一索引值，用于对指定节点进行各类操作	String/Number	任意唯一的字符或数字
		            		field:'',//节点字段名	String	一般对应表字段名
		            		children:[]//	
	                }
	                childrenArray.push(obj);
	            }
	        }
	        dataArrayIndex.children = childrenArray;
	        if(childrenArray.length > 0) {//有儿子节点则递归
	        	recursive_tree(datas, childrenArray);
	        }

	    }
	    return dataArray;
	}
</script>
</body>

</html>