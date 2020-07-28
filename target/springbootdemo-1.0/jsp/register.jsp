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
	<link rel="icon" href="${ctx}/static/assets/images/logo.png">
    <title>后台管理系统</title>
</head>

<body class="login-wrap bgImg">
    <div class="login-container">
        <form class="layui-form login-form">
            <p class="cr-333 fz-20 text-c" style="margin-bottom:25px">注册账号</p>
            <div class="layui-form-item">
                <input type="text" name="phone" id="phone" required value="" lay-verify="required|phone" placeholder="手机号" lay-reqtext="请输入手机号"
                    autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item" style="position:relative;height:50px">
                <input type="text" name="verfiy" required style="width:195px;position:absolute" value=""
                    lay-verify="required|verfiy" placeholder="请输入验证码" lay-reqtext="请输入验证码" autocomplete="off" class="layui-input">
                <button type="button" class="layui-btn login-btn login-btn fr sendCode"
                    style=" width:133px;position: absolute;right: 0;" id="smsButtom">发送验证码</button>
            </div>
            <div class="layui-form-item password">
                <input type="password" name="password" required value="" lay-verify="required|pass" maxlength="20" placeholder="输入6-20位密码" lay-reqtext="请输入6-20位密码"
                    autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item password">
                <input type="password" name="repassword" required value="" lay-verify="required|pass" placeholder="再次输入密码" lay-reqtext="请再次输入密码"
                    autocomplete="off" class="layui-input">
            </div>
            <button type="button" class="layui-btn login-btn" lay-submit lay-filter="save"
                style="width:100%;height:49px;font-size: 21px">注册</button>
            <div class="text-c fz-18" style="margin-top: 25px">
                <span style="color:#999999;">已有账号？</span><span><a href="${ctx}/login.html"
                        style="color:#1395fa;">立即登录</a></span>
            </div>
        </form>
    </div>
</body>
<%@ include file="footer.jsp"%>
<script>
    layui.use(['form', 'jquery','common'], function () {
        var $ = layui.jquery, form = layui.form,common = layui.common;
        form.verify({
            phone: [/^1[3|4|5|7|8|6]\d{9}$/, "请输入正确的手机号"],
            pass: [/^[0-9A-Za-z]{6,20}$/, '密码格式为6-20位数字、字母'],
            verfiy: function (val) {
                if (val.length != 6) {
                    return '请输入6位正确的验证码！'
                }
            }
        });
      	//提交表单数据
        form.on('submit(save)', function (data) {
        	if(data.field.password != data.field.repassword){
        		layer.msg('两次录入密码不一致！', { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
        		return false;
        	}
            //表单数据formData
            common.ajax('post','${ctx}/login/register.html',data.field,function(res){
            	if(res.success){
            		layer.msg('注册成功!', { icon: 6, anim: 6, time: 1000,  shade: [0.8, '#393D49'] },function(){
            			window.location.reload();
            		});
            	}else{
            		layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
            	}
            })
            return false;
        });
        $(document).on('click','#smsButtom',function(){
        	var phone=$('#phone').val();
        	var regexp=/^1[3|4|5|7|8|6]\d{9}$/;
        	if(phone == null || phone == 'undefined' || phone ==''|| !regexp.test(phone)){
        		layer.msg("请填写正确的手机号", { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
				return false;
        	}
        	
        	 common.ajax('post','${ctx}/login/sendSms.html',{phone:phone,tranType:1},function(res){
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
             		layer.msg(res.message, { icon: 0, anim: 6, time: 1000,  shade: [0.8, '#393D49'] });
             	}
             })
      	});
    })
</script>

</html>