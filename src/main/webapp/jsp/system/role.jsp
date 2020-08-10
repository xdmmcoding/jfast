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
        <div class="layui-row layui-col-space20">
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">角色列表</div>
                    <div class="layui-card-body">                        
	                    <form class="tableHeader layui-form">
	                        <div class="layui-form-item">
                                <div class="layui-inline ">
                                    <label class="layui-form-label" style="width:70px">角色名称：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" value="" style="width: 180px" placeholder="请输入角色名称"
                                            autocomplete="off" class="layui-input" id="roleName">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label" style="width:70px">角色状态：</label>
                                    <div class="layui-input-inline">
                                        <select style="height:38px;width: 180px" data-url="${ctx}/dict/selected?type=roleStatus" id="roleStatus"></select>
                                    </div>
                                </div>
                                <div class="layui-inline" style="margin-left:15px">
                                    <div class="layui-input-inline">
                                        <button class="layui-btn layui-btn-blue" lay-submit="" id="query">查询</button>
                                        <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
                                    </div>
                                </div>
                            </div>
	                    </form>
	                    <div style="clear:both"></div>
	                    <fieldset class="layui-elem-field layui-field-title-zd">
							<legend style="">
								<button type="button" class="layui-btn layui-btn-sm layui-btn-blue" id="add">
									<i class="layui-icon">&#xe654;</i>增加角色
								</button>
							</legend>
						</fieldset> 
                        <table class="layui-table" id="datagrid" lay-filter="datagrid"></table>
                    </div>
                </div>
            </div>
            <!-- edit add  弹框 -->
            <form class="layui-form mar-t-20" id="add-form" action="" style="display:none">
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px"><span class="mustFill"></span>角色名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="roleName" value="" style="width:400px"
                            lay-verify="required" maxlength="50" placeholder="请输入角色名称" lay-reqtext="请输入角色名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">角色描述：</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" id="description" value="" placeholder="请输入角色描述" oninput="wordLeg(this);"
                            maxlength="150" onpropertychange="if(value.length>150) value=value.substr(0,150)"
                            name="description" class="layui-layer-input" style="width:400px;height:150px"></textarea>
                        <p style="color:#666;position:absolute;right:75px;bottom:5px"><span
                                class="text_count">0</span>/150</p>
                        <input type="hidden" name="roleId"  value="" />
                    </div>
                </div>
                <div class="layui-form-item" style="width:570px;position:absolute;bottom:10px;">
                    <div class="layui-input-block text-c" style="margin-left:0">
                        <button class="layui-btn layui-btn-blue" lay-submit lay-filter="save">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
                    </div>
                </div>
            </form>

            <!-- 编辑角色 -->
            <script type="text/html" id="role" style="display:none">
                <table class="layui-table" id="personnel" lay-filter="personnel" ></table>
            </script>

            <!-- 设置权限 -->
            <script type="text/html" id="authority" style="display:none">
            	<form class=" layui-form layui-input-block" style="margin-left:0;margin-top:15px;">
					<div class="layui-form-item"">
                    	<div style="padding:10px;border:1px solid #e6e6e6">
                        	<input type="text" id="search" value="" style="width: 100%;height:35px;margin-bottom:10px" placeholder="请输入选项名称" autocomplete="off" class="layui-input">
                        	<div  style="height:300px;overflow-y:auto;">
								<div id="authtree" class="demo-tree-more"></div>
							</div>
                    	</div>
                	</div>
					<input type="hidden" name="roleIdAuth"  value="" />
					<div class="text-c">
                		<button class="layui-btn layui-btn-blue" lay-submit lay-filter="saveauth">保存</button>
                		<button type="reset" class="layui-btn layui-btn-primary" id="closeauth">关闭</button>
					</div>
             	</form>     
            </script>
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
        var $ = layui.jquery, table = layui.table, layer = layui.layer, form = layui.form, common = layui.common, etree = layui.eleTree, el;
     	// 表格渲染
        table.render({
            elem: '#datagrid',
            height: 'full',
            cols: [[
	            { field: 'roleName', title: '角色名称', width: 200},
                { field: 'personnelnum', title: '角色人员', width: 200, sort: true, templet: function (d) {
                        return '<a class="pointer" style="color:#177ce3" lay-event="seeRole">' + d.personnelnum + '</a>'
                  },event: 'personnel'
                },
                { field: 'description', title: '角色描述' },
                { field: 'updateTime', title: '更新时间', width: 200, templet: function (d) {
                    	return common.formatDatetimebox(d.updateTime);
                  } 
                },
                { field: 'roleStatus', title: '是否启用', width: 100, templet: function (d) {
	                	if(d.roleType === '1'){
	                        return '是';
	                	}
                        if (d.roleStatus === '0') {
                            return '<input type="checkbox" checked name="open" lay-skin="switch" lay-text="是|否">';
                        } else {
                        	return '<input type="checkbox"  lay-skin="switch" lay-text="是|否">';
                        }
                  },event: 'isstart'
                },
                { field: 'caozuo', title: '操作', width: 240, templet: function (d) {
	                	if(d.roleType === '1'){
	                        return '<a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="authority">设置权限</a><a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>';
	                	}
                    	return '<a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="authority">设置权限</a><a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>'                       
                  }
                }
            ]],
            url:'${ctx}/role/pagination.html',
            method:'post',
            page: true,  //是否显示分页
            limits: [10, 20, 30, 50],
            limit: 10, //每页默认显示的数量
            text: {
                none:'<div class="auto text-c noRegister" style="width:210px;padding:50px 0 80px 0;" >'+
	                 	'<img style="width:140px;" src="${ctx}/static/assets/images/text.png" alt="">'+
	                    '<p class="fw-600 fz-18 cr-5a5a5a  mar-t-10">网站还没有角色数据</p>'+
	            	 '</div>'
            },
        });
	    table.on('tool(datagrid)', function (res) {
	        var data = res.data;
	        if (res.event === 'edit') {//编辑
	        	aeFormLayer('编辑','edit',data);
	        } else if (res.event === 'authority') {//设置权限
	        	setauthority(data)
	        } else if (res.event === 'delete') {//删除
	        	del(data);
	        } else if (res.event === 'isstart') {//是否启用
	        	if(data.roleType != '1')isstart(data);
	        } else if (res.event === 'personnel') {//角色
	        	if(data.personnelnum != '0')personnelLayer(data);
	        }
	    });
	    
	    function where(){
     		return {
     			roleName:$('#roleName').val(),
     			roleStatus:$('#roleStatus').val(),
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
     		aeFormLayer('增加角色','add',null);
        });
        // 弹层和表单验证
        function aeFormLayer(title,type,data) {
            //页面层
            layer.open({
                type: 1,
                title: title,
                area: ['600px', '430px'], //宽高
                content: $("#add-form"), //调到新增页面
                success: function (layero, index) {
                	if(type === 'edit'){
                		$("input[name='roleId']").val(data.roleId);
                		$("input[name='roleName']").val(data.roleName);
                		$("textarea[name='description']").val(data.description);
                		try {
                			$('.text_count').html(data.description.length);
						} catch (e) {
							$('.text_count').html('0');
						}
                	}else{
                		$("input[name='roleId']").val('');
                		$("input[name='roleName']").val('');
                		$("#description").val('');
                		$('.text_count').html('0');
                	}
                },
                yes: function () {
                	
                },
                end: function () {
                	$("#description").val('');
            		$('.text_count').html('0');
                }
            });
        }
		//角色信息保存
        form.on('submit(save)', function (data) {
		    //表单数据formData
		    common.ajax('post','${ctx}/role/submit.html',data.field,function(res){
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
				common.ajax('post','${ctx}/role/del.html?id='+data.roleId,null,function(res){
			    	if(res.success){
			    		//layer.msg('删除成功!', { icon: 6, anim: 6, time: 1000, shade: [0.8, '#393D49'] });
			    		query();
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
			var status = data.roleStatus == '0'?'1':'0';
			common.ajax('post','${ctx}/role/isstart.html',{roleId:data.roleId,roleStatus:status},function(res){
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
		//配置权限页面
		var closeauthindex;
		function setauthority(data){
			layer.open({
                type: 1,
                title: '设置权限',
                area: ['600px', '500px'],
                content: $('#authority').html(), //这里content是一个普通的String
                success: function (layero, index) {
                	closeauthindex =  index;
                	$("input[name='roleIdAuth']").val(data.roleId);
                	//初始化菜单树
                 	common.ajax('post','${ctx}/role/getauthoritys.html',{roleId:data.roleId},function(res){
                  		if(res.success){
	                  		// 树状渲染
	           	   	        el = etree.render({
	           	   	            elem: '#authtree',
	           	   	            data: make_tree(res.data.tdata),
	           	   	            check: 'checkbox', //勾选风格
	           	   	            showCheckbox: true, //是否显示复选框
	           	   	         	highlightCurrent:true,
	           	   	      		defaultCheckedKeys: res.data.cdata,
	           		   	        searchNodeMethod: function(value,data) {
	           		   	          if (!value) return true;
	           		   	          return data.label.indexOf(value) !== -1;
	           		   	      	}
	           	   	        });
		           	   	  	el.expandAll(); //展开所有
		           	   	  	el.unExpandAll();
                  		}
                  	});
                },
                cancel: function (index, layero) {
                    $('#authority').hide();
                    layer.close(index)
                }
            });
		}
		$(document).on('input propertychange','#search',function(val){
      		var d = $('#search').val();
      		if(d != '' && d != null){
      			el.expandAll(); //展开所有
      		}else{
      			//el.unExpandAll() //合并所有
      		}
      		el.search(d);
        });
		//关闭权限配置
		$(document).on('click','#closeauth',function(){
			layer.close(closeauthindex);
        });
		//提交配置的权限菜单
		form.on('submit(saveauth)',function(data){
			var checkd = el.getChecked(false,true);
      		var codes = new Array();
			if(checkd != null && checkd.length>0){
      			for(var i=0;i < checkd.length;i++){
      				codes.push(checkd[i].id);
      			}
      		}
   			common.ajax('post','${ctx}/role/setauthority.html?roleIdAuth='+data.field.roleIdAuth,{codes:codes},function(res){
		    	if(res.success){
		    		layer.close(closeauthindex);
		    	}else{
		    		if(res.message){
		    			layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
		    		}
		    	}
   		    })
   		    return false;
        });
		//获取角色下人员
		function personnelLayer(data){
			layer.open({
				type: 1,
                title: '角色人员',
                area: ['800px', '500px'],
                content: $('#role').html(), //这里content是一个普通的String
                cancel: function (index, layero) {
                    $('#role').hide();
                    layer.close(index)
                },
                success: function (layero, index) {
                	table.render({
        	            elem: '#personnel',
        	            height: 420,
                        style: 'padding:0 20px',
        	            cols: [[ //标题栏
        	                { field: 'userName', title: '姓名'},
        	                { field: 'phone', title: '手机号'},
        	                { field: 'department', title: '所属部门'},
        	                { field: 'position', title: '职务'}
        	            ]],
        	            url:'${ctx}/personnel/pagination.html',
        	            method:'post',
        	            page: true,  //是否显示分页
        	            limits: [10, 20, 30, 50],
        	            limit: 10, //每页默认显示的数量
        	            where:{roleId:data.roleId},
        	            text: {
        	                none:'<div class="auto text-c noRegister" style="width:210px;padding:50px 0 80px 0;" >'+
        		                 	'<img style="width:140px;" src="${ctx}/static/assets/images/text.png" alt="">'+
        		                    '<p class="fw-600 fz-18 cr-5a5a5a  mar-t-10">改角色下暂无人员数据</p>'+
        		            	 '</div>'
        	            },
        	        });
                }
            });
		}
    });
  	//递归成树
  	function make_tree(datas){
		var dataArray = [];		
	    for(var j = 0; j < datas.length; j++) {
	    	var data = datas[j];
	        var parentCode = data.parentCode;
	        if(parentCode == '00' || parentCode == null) {
	            var obj = {
	            		code:data.code,
	            		parentCode:data.parentCode,
	            		label:data.text,//节点标题	String	未命名
	            		id:data.code,//节点唯一索引值，用于对指定节点进行各类操作	String/Number	任意唯一的字符或数字
	            		disabled:data.LAY_CHECKED,
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
		            		label:data.text,//节点标题	String	未命名
		            		id:data.code,//节点唯一索引值，用于对指定节点进行各类操作	String/Number	任意唯一的字符或数字
		            		disabled:data.LAY_CHECKED,
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