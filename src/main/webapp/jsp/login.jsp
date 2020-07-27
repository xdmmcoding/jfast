<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${ctx}/static/assets/css/layui.css">
    <link rel="stylesheet" href="${ctx}/static/assets/css/login.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/css/global.css">
    <link rel="icon" href="${ctx}/static/assets/images/favicon.ico" />
    <title>${pro_name}</title>
</head>

<body class="login-wrap bgImg">
    <div class="login-container">
    	<!-- <img src="" alt="" style="width: 150px;display:block;margin:10px auto"> -->
        <p class="system-title">${pro_name}</p>
        <form class="layui-form login-form" >
            <p class="login-title">
                <span class="active">账号登录</span>
                <span>短信登录</span>
            </p>
            <div class="layui-form-item">
                <input type="text" name="username" id="username" required value="" lay-verify="required" placeholder="请输入手机号" lay-reqtext="请输入手机号"
                    autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item password">
                <input type="password" name="password" id="password" value="" lay-verify="pass" maxlength="20" placeholder="输入6-20位密码" lay-reqtext="请输入密码"
                    autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item verfiy" style="position:relative;display:none;height:50px">
                <input type="text" name="verfiy" id="verfiy" style="width:195px;position:absolute" value="" placeholder="请输入验证码" lay-reqtext="请输入验证码"
                    autocomplete="off" class="layui-input">
                <button type="button" class="layui-btn login-btn login-btn fr sendCode"
                    style=" width:133px;position: absolute;right: 0;" id="smsButtom">发送验证码</button>
            </div>
            <%-- <p class="text-r fz-16 pointer forgetPass" style="margin:-10px 0 15px 0"><a href="${ctx}/repassword.html"
                    style="color: #999999">忘记密码</a>
            </p> --%>
            <button type="button" class="layui-btn login-btn" lay-submit lay-filter="save" id="logins"
                style="width:100%;height:49px;font-size: 21px">登录</button>
            <%-- <div class="text-c fz-18" style="margin-top: 25px">
                <span style="color:#999999;">没有账号？</span><span><a href="${ctx}/register.html "
                        style="color:#1395fa;">立即注册</a></span>
            </div> --%>
            <input type="hidden" id="type" name="type" value="2">
            
        </form>
    </div>
</body>
<%@ include file="footer.jsp"%>
<script>
    layui.use(['form', 'jquery','common'], function () {
        var $ = layui.jquery, form = layui.form , common = layui.common;
        $('.login-title span').on('click', function () {
            $(this).siblings().removeClass('active');
            $(this).addClass('active')
            if ($(this).index() == 1) {
                $('.verfiy').show();
                $('.password, .forgetPass').hide()
                $('#username').attr("lay-verify", "required|phone");
                $('.verfiy input').attr("lay-verify", "required|verfiy");
                $('#password').attr("lay-verify", "");
                $("#type").val(1);
            } else {
                $('.verfiy').hide();
                $('.password, .forgetPass').show()
                $('.verfiy input').attr("lay-verify", "");
                $('#username').attr("lay-verify", "required");
                $('#password').attr("lay-verify", "required|pass");
                $("#type").val(2);
            }
        })

        form.verify({
            phone: [/^1[3|4|5|7|8|6]\d{9}$/, "请输入正确的手机号！"]
        })
        //提交表单数据
        form.on('submit(save)', function (data) {
        	if($("#type").val()==2){
        		var password=$('#password').val();
            	var regexp=/^[0-9A-Za-z]{6,20}$/;
            	if(password == null || password == 'undefined' || password ==''|| !regexp.test(password)){
            		layer.msg("密码格式为6-20位数字、字母！", { icon: 0, anim: 6, time: 1000, shade: [0.8, '#393D49'] });
    				return false;
            	}
        	}else{
        		var verfiy=$('#verfiy').val();
                 if (verfiy.length != 6) {
                	 layer.msg("请输入6位正确的验证码！", { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
                     return false;
                 }
        	}
            //表单数据formData
            common.ajax('post','${ctx}/login/login.html',data.field,function(res){
            	if(res.success){
            		window.location.href = '${ctx}/index.html';
            	}else{
            		layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
            	}
            })
        });
        //回车提交
        document.onkeydown = function(e){
		    var theEvent = window.event || e;
		    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		    if (code == 13) {
		    	$("#logins").click();
		    }
		}
        
        $('.sendCode').on('click', function () {
        	var phone=$('#phone').val();
        	var regexp=/^1[3|4|5|7|8|6]\d{9}$/;
        	if(phone == null || phone == 'undefined' || phone ==''|| !regexp.test(phone)){
        		layer.msg("请填写正确的手机号", { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
				return false;
        	}
        	try {
				common.ajax('post','${ctx}/login/sendSms.html',{phone:phone,tranType:2},function(res){
				   	if(res.success){
				   		var count = 60;
				    		var countDown = setInterval(function(){
				       	 	if (count === 0) {
				                $('.sendCode').text('发送验证码').removeAttr('disabled');
				                $('.sendCode').addClass('login-btn').removeClass('resend')
				                clearInterval(countDown);
				            } else {
				                $('.sendCode').attr('disabled', true);
				                $('.sendCode').addClass('resend').removeClass('login-btn')
				                $('.sendCode').text(count + '秒后重新发送');
				            }
				            count--;
				        },1000);
				   		layer.msg('发送成功!', { icon: 6, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
				   	}else{
				   		if(res.message)layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
				   	}
				})				
			} catch (e) {
				console.log('验证码获取失败');
				console.log(e);
			}
      	});
    })
</script>

</html>