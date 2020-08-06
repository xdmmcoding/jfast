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
        <div class="layui-row layui-col-space20">
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">人员列表</div>
                    <div class="layui-card-body">
	                    <form class="tableHeader layui-form">
	                        <div class="fl">
	                            <div class="layui-form-item">
	                                <div class="layui-inline">
	                                    <label class="layui-form-label" style="width:70px">用户名</label>
	                                    <div class="layui-input-inline">
	                                        <input type="text"  style="width: 180px" placeholder="请输入姓名" id="userName" 
	                                            autocomplete="off" class="layui-input">
	                                    </div>
	                                </div>
	                                <div class="layui-inline">
	                                    <label class="layui-form-label" style="width:70px">所属部门</label>
	                                    <div class="layui-input-inline">
	                                        <input type="text" value="" style="width: 180px" placeholder="请输入所属部门" id="department"
	                                            autocomplete="off" class="layui-input">
	                                    </div>
	                                </div>
	                                <div class="layui-inline">
	                                    <label class="layui-form-label" style="width:70px">所属角色</label>
	                                    <div class="layui-input-inline">
	                                        <select style="height:38px;width: 180px" data-url="${ctx}/personnel/selected.html" id="roleId"></select>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="fl" style="padding-right: 15px">
	                            <div class="layui-input-inline">
	                                <button class="layui-btn layui-btn-blue" id="query">查询</button>
	                                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
	                            </div>
	                        </div>
	                    </form>	                    
	                    <div style="clear:both"></div>
	                    <fieldset class="layui-elem-field layui-field-title-zd">
							<legend style="">
								<button type="button" class="layui-btn layui-btn-sm layui-btn-blue" id="add">
									<i class="layui-icon">&#xe654;</i>增加人员
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
                    <label class="layui-form-label" style="width: 100px"><span class="mustFill"></span>用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName"  value="" style="width: 300px"
                            lay-verify="required|username" maxlength="50" placeholder="请输入用户名" lay-reqtext="请输入用户名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px"><span class="mustFill"></span>手机号</label>
                    <div class="layui-input-block">
                        <input type="text" name="phone" style="width: 300px" required lay-verify="required|phone"  lay-reqtext="请输入手机号"
                            placeholder="请输入手机号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="password" style="width: 300px" lay-verify="required|pass"
                            placeholder="请输入密码" lay-reqtext="请输入密码" autocomplete="new-password" class="layui-input">
                    </div>
                </div>
                <!-- <div class="layui-form-item">
                    <label class="layui-form-label" style="width:100px"><span class="mustFill"></span>所属角色</label>
                    <div class="layui-input-inline" style="width: 300px">
                        <select required lay-verify="required" name="role">
                            <option value="">请选择角色</option>
                            <option value="1">系统管理员</option>
                            <option value="2">角色1</option>
                            <option value="3">角色2</option>
                            <option value="4">角色3</option>
                        </select>
                    </div>
                </div> -->
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">所属部门</label>
                    <div class="layui-input-block">
                        <input type="text" name="department" style="width: 300px" placeholder="请输入所属部门" maxlength="50"
                            autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width: 100px">职务</label>
                    <div class="layui-input-block">
                        <input type="text" name="position" style="width: 300px" placeholder="请输入职务" maxlength="50" autocomplete="off"
                            class="layui-input">
                    </div>
                    <input type="hidden" name="id"  value="" />
                </div>
                <div class="layui-form-item width-100" style="position:absolute;bottom:10px;">
                    <div class="layui-input-block text-c" style="margin-left:0">
                        <button class="layui-btn layui-btn-blue" lay-submit lay-filter="save">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
                    </div>
                </div>
            </form>
            <!-- setrole add  弹框 -->
            <form class="layui-form mar-t-20" id="set-role" action="" style="display:none" lay-filter="role-form">                
                <div class="layui-form-item">
                    <label class="layui-form-label" style="width:100px"><span class="mustFill"></span>所属角色</label>
                    <div class="layui-input-inline " style="width: 300px">
                        <select required lay-verify="required" name="roleId" data-url="${ctx}/personnel/selected.html" ></select>
                    </div>
                    <input type="hidden" name="uid"  value="" />
                </div>
                <div class="layui-form-item width-100" style="position:absolute;bottom:10px;">
                    <div class="layui-input-block text-c" style="margin-left:0">
                        <button class="layui-btn layui-btn-blue" lay-submit lay-filter="saverole">保存</button>
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
    </style>
</body>
<%@ include file="../footer.jsp"%>
<script>
    layui.use(['jquery', 'table', 'form', 'layer','common'], function (obj) {
        var $ = layui.jquery, table = layui.table, layer = layui.layer, form = layui.form,common = layui.common;
        form.verify({
            phone: [/^1[3|4|5|7|8|6]\d{9}$/, "请输入正确的手机号！"],
            username: [/^[A-Za-z0-9]{3,20}$/, "请输入正确的用户名！格式为3-20位字母或数字"],
            pass: [/^[0-9A-Za-z]{6,20}$/, '密码格式为6-20位数字、字母！'],
            content: function (value) {
                layedit.sync(editIndex);
            }
        });
        table.render({
            elem: '#datagrid',
            height: 'full',
            cols: [[ //标题栏
                { field: 'userName', title: '用户名' },
                { field: 'phone', title: '手机号' },
                { field: 'department', title: '所属部门' },
                { field: 'position', title: '职务'},
                { field: 'roleName', title: '所属角色' },
                { field: 'createDate', title: '创建时间',width: 200, templet: function (d) {
                	return common.formatDatetimebox(d.createDate);
                  }  
                },
                { field: 'status', title: '是否启用',  width: 100, templet: function (d) {
                		if (d.type == '1')return '是'
                        if (d.status == '0') {
                            return '<input type="checkbox" checked name="open" lay-skin="switch" lay-text="是|否">'
                        }else {
                        	return '<input type="checkbox"  lay-skin="switch" lay-text="是|否">'
                        }
                  },event: 'isstart'
                },
                { field: 'caozuo', title: '操作', width: 240, templet: function (d) {
                	if(d.type == '1'){
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>';
                	}
                    return '<a class="layui-btn layui-btn-blue layui-btn-xs" lay-event="setrole">设置角色</a><a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>';
                  }
                }
            ]],
            url:'${ctx}/personnel/pagination.html',
            method:'post',
            page: true,  //是否显示分页
            limits: [10, 20, 30, 50],
            limit: 10, //每页默认显示的数量
            text: {
                none:'<div class="auto text-c noRegister" style="width:210px;padding:50px 0 80px 0;" >'+
	                 	'<img style="width:140px;" src="${ctx}/static/assets/images/text.png" alt="">'+
	                    '<p class="fw-600 fz-18 cr-5a5a5a  mar-t-10">网站还没有人员数据</p>'+
	            	 '</div>'
            },
        });
	    table.on('tool(datagrid)', function (res) {
	        var data = res.data;
	        if (res.event === 'edit') {//编辑
	        	formLayer('编辑','edit',data);
	        } else if (res.event === 'setrole') {//设置权限
	        	setrole(data)
	        } else if (res.event === 'delete') {//删除
	        	del(data);
	        } else if (res.event === 'isstart') {//是否启用
	        	if (data.status != '2') isstart(data);
	        }
	    });
	    
	    function where(){
     		return {
     			userName:$('#userName').val(),
     			department:$('#department').val(),
     			roleId:$('#roleId').val(),
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
     	//新增
     	$(document).on('click','#add',function(){
     		formLayer('增加人员','add',null);
        });

        // 弹层和表单验证
        function formLayer(title,type,data) {
            //页面层
            layer.open({
                type: 1,
                title: title,
                area: ['500px', '450px'], //宽高
                content: $("#add-form"), //调到新增页面
                success: function (layero, index) {
                	if(type === 'edit'){
                		$("input[name='id']").val(data.id);
                		$("input[name='userName']").val(data.userName);
                		$("input[name='phone']").val(data.phone);
                		$("input[name='department']").val(data.department);
                		$("input[name='position']").val(data.position);
                		$("input[name='password']").attr("lay-verify", "");
                		$("input[name='password']").val("");
                	}else{
                		$("input[name='id']").val('');
                		$("input[name='userName']").val('');
                		$("input[name='phone']").val('');
                		$("input[name='department']").val('');
                		$("input[name='position']").val('');
                		$("input[name='password']").attr("lay-verify", "required");
                		$("input[name='password']").val('');
                	}
                },
                yes: function () {

                },
                end: function () {

                }
            });
        }
        //人员信息保存
        form.on('submit(save)', function (data) {
        	if(data.field.password != ''){
        		if(!/^[0-9A-Za-z]{6,20}$/.test(data.field.password)){
        			layer.msg('密码格式为6-20位数字、字母！', { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
        			return false;
        		}
        	}
		    //表单数据formData
		    common.ajax('post','${ctx}/personnel/submit.html',data.field,function(res){
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
		//删除
		function del(data){
			layer.confirm('确定删除?', function (index) {
				common.ajax('post','${ctx}/personnel/del.html?id='+data.id,null,function(res){
			    	if(res.success){
			    		//layer.msg('删除成功!', { icon: 6, anim: 6, time: 1000, shade: [0.8, '#393D49'] });
			    		query();
			    	}else{
			    		if(res.message){
			    			layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
			    		}
			    	}
			    })
            });
		}
		//是否启用
		function isstart(data){
			var status = data.status == '0'?'1':'0';
			common.ajax('post','${ctx}/personnel/isstart.html',{id:data.id,status:status},function(res){
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
		//是否启用
		var roleIndex;
		function setrole(data){
			//页面层
            layer.open({
                type: 1,
                title: '设置角色',
                area: ['500px', '350px'], //宽高
                content: $("#set-role"), //调到新增页面
                success: function (layero, index) {
                	roleIndex = index;
                	form.val("role-form",{"roleId":data.roleId});
                	$("input[name='uid']").val(data.uid);
                },
                yes: function () {

                },
                end: function () {

                }
            });
		}
		
		//人员信息保存
        form.on('submit(saverole)', function (data) {
		    //表单数据formData
		    common.ajax('post','${ctx}/personnel/setrole.html',data.field,function(res){
		    	if(res.success){
		    		query();
		    		layer.close(roleIndex);
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