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
    <title></title>
</head>

<body class="attestationInfo layui-view-body">
    <div class="layui-content">
        <div class="layui-row">
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body">                    
                    	<div class="h-title">菜单列表</div>
	                    <fieldset class="layui-elem-field layui-field-title-zd" style="margin-top: 10px;">
								<legend style="">
									<button type="button" class="layui-btn layui-btn-sm layui-btn-black" id="add">
										<i class="layui-icon">&#xe654;</i>增加菜单
									</button>
								</legend>
						</fieldset>
                        <table class="layui-table" id="datagrid" lay-filter="datagrid"></table>
                    </div>
                </div>
            </div>
            <!-- edit add  弹框 -->
            <form class="layui-form text-c" id="add-form" action="" style="display:none">
                <div class="layui-form-item" style="margin-top: 10px;" >
                    <label class="layui-form-label" ><span class="mustFill"></span>系统分类</label>
                    <div class="layui-input-block">
                        <select type="text" name="uType" value="" data-url="${ctx}/dict/selected?type=utype" lay-verify="required" maxlength="50" placeholder="请输入系统分类" lay-reqtext="请输入系统分类" autocomplete="off" ></select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" ><span class="mustFill"></span>菜单类型</label>
                    <div class="layui-input-block">
                        <select type="text" name="mType" value="" data-url="${ctx}/dict/selected?type=mtype" lay-verify="required" maxlength="50" placeholder="请输入菜单类型" lay-reqtext="请输入菜单类型" autocomplete="off" ></select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" ><span class="mustFill"></span>菜单名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="text" value="" lay-verify="required" maxlength="50" placeholder="请输入菜单名称" lay-reqtext="请输入菜单名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" ><span class="mustFill"></span>菜单编码</label>
                    <div class="layui-input-block">
                        <input type="text" name="code" value="" lay-verify="required" maxlength="50" placeholder="请输入菜单编码" lay-reqtext="请输入菜单编码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" >菜单地址</label>
                    <div class="layui-input-block">
                        <input type="text" name="url" value=""  maxlength="50" placeholder="请输入菜单地址"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" >菜单图标</label>
                    <div class="layui-input-block">
                        <input type="text" name="icon" value=""  maxlength="50" placeholder="请输入菜单图标"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" >权限标识</label>
                    <div class="layui-input-block">
                        <input type="text" name="permissions" value=""  maxlength="50" placeholder="请输入菜单图标"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" >排序</label>
                    <div class="layui-input-block">
                        <input type="text" name="sort" value=""  maxlength="50" placeholder="请输入排序" lay-reqtext="请输入排序" autocomplete="off" class="layui-input">
                    </div>
                    <input type="hidden" name="id"/>
                    <input type="hidden" name="parentCode"/>
                </div>
                <button class="layui-btn layui-btn-blue" lay-submit lay-filter="save">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
            </form>
        </div>
    </div>
    <style>
        .cardButton {
            position: absolute;
            right: 15px;
            top: 6px;
        }
        .h-title {
            position: absolute;
            left: 17px;
            top: 6px;
            font-size: 15px;
    		font-weight: 600;
    		color: #666;
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
    layui.use(['jquery', 'table', 'form', 'layer','common','treeTable'], function (obj) {
        var $ = layui.jquery, table = layui.table, layer = layui.layer, form = layui.form, common = layui.common,treetable = layui.treeTable;   	
     	treetable.render({
            elem: '#datagrid',
            id:'datagrid',
            url:'${ctx}/menu/get/all.html',
            method:'post',
            height: 'full',
            tree: {
                iconIndex: 0,
                isPidData: true,
                idName: 'code',
                pidName: 'parentCode'
            },
            cols: [[
				//{type:'checkbox'},
                { field:'text',title: '菜单名称',align:'left'},
                { field:'uTypeName',title: '系统分类'},
                { field:'mTypeName',title: '菜单类型'},
                { field:'code',title: '菜单编码'},
                { field:'parentCode',title: '父菜单编码'},
                //{ field: 'url', title: '菜单地址' },
                { field: 'permissions', title: '权限标识' },
                { field: 'updateTime', title: '更新时间', width: 200, templet: function (d) {
                    	return common.formatDatetimebox(d.updateTime);
                  } 
                },
                { field: 'state', title: '是否启用', width: 100, templet: function (d) {
                        if (d.state === '0') {
                            return '<a lay-event="isstart"><input type="checkbox" checked name="open" lay-skin="switch" lay-text="是|否"></a>';
                        } else if(d.state === '1'){
                        	return '是';
                        } else {
                        	return '<a lay-event="isstart"><input type="checkbox"  lay-skin="switch" lay-text="是|否"></a>';
                        }
                  },event:'isstart'
                },
                { field: 'caozuo', title: '操作', width: 240, templet: function (d) {
	                	if (d.state === '1') {
	                		return '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>'
	                    }
                    	return '<a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="add">添加</a><a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>'                       
                  }
                }
            ]]
        });
     	treetable.on('tool(datagrid)', function (res) {
	        var data = res.data;
	        if (res.event === 'edit') {//编辑
	        	aeFormLayer('编辑','edit',data);
	        } else if (res.event === 'add') {//添加
	        	aeFormLayer('添加','add',data);
	        } else if (res.event === 'delete') {//删除
	        	del(data);
	        } else if (res.event === 'isstart') {//是否启用
	        	if(data.roleType != '1')isstart(data);
	        }
	    });
     	//新增
     	$(document).on('click','#add',function(){
     		aeFormLayer('增加','add',{code:'00',uType:'',mType:''});
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
			common.ajaxn('post','${ctx}/menu/isstart.html',{id:data.id,state:state},function(res){
		    	if(res.success){
		    		treetable.reload('datagrid',{})
		    	}else{
		    		if(res.message){
		    			layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
		    		}
		    	}
		    })
		}
		var aeIndex;
        // 弹层和表单验证
        function aeFormLayer(title,type,data) {
            //页面层
            layer.open({
                type: 1,
                title: title,
                area: ['550px', '550px'], //宽高
                content: $("#add-form"), //调到新增页面
                success: function (layero, index) {
                	aeIndex = index;
                	if(type === 'edit'){
                		$("input[name='text']").val(data.text);
                		$("input[name='code']").val(data.code);
                		$("input[name='url']").val(data.url);
                		$("input[name='icon']").val(data.icon);
                		$("input[name='permissions']").val(data.permissions);
                		$("input[name='parentCode']").val(data.parentCode);
                		$("input[name='id']").val(data.id);
                		$("select[name='uType']").val(data.uType);
                		$("select[name='mType']").val(data.mType);
                		form.render('select');
                	}else{
                		$("input[name='text']").val('');
                		$("input[name='code']").val('');
                		$("input[name='url']").val('');
                		$("input[name='icon']").val('');
                		$("input[name='permissions']").val('');
                		$("input[name='parentCode']").val(data.code);
                		$("input[name='id']").val('');
                		$("select[name='uType']").val(data.uType);
                		$("select[name='mType']").val(data.mType);
                		form.render('select');
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
    });
</script>
</body>

</html>