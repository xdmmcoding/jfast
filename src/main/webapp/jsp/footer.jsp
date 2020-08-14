<%@ page language="java"  pageEncoding="utf-8"%>
<script src="${ctx}/static/assets/layui.js" ></script>
<script src="${ctx}/static/assets/font/ali/iconfont.js" ></script>
<script src="${ctx}/static/assets/micon/iconfont.js" ></script>
<script type="text/javascript" >
	//js中使用项目根路径
	var webRoot = '${ctx}';
	//配置layui模块加载路径
	layui.config({
	    base: '${ctx}/static/assets/lay/modules/'
	}).extend({
		common:'common',
		eleTree:'eleTree',
		treeTable:'treeTable',
		iconHhysFa:'iconHhysFa',
		home:'{/}'+'${ctx}/static/js/home'
	})
	//数字美元表示法
	window.formatdollar = function (str) {
    	return str.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')
	}
	//初始化所有的select
	layui.use(['jquery','form','common','layer'], function (obj) {
        var $ = layui.jquery, form = layui.form,common = layui.common,layer = layui.layer;
        //处理全局异常
        $.ajaxSetup({
        	complete:function(xhr,status){
        		var forward = xhr.getResponseHeader("forward");
        		var code = xhr.getResponseHeader("code");
        		if('true' == forward){
        			if(code != null && code != '' && code != 'undefined'){
        				window.location.href = '${ctx}/page/error.html?code='+code;
        			}
        		}
            }
        });
        //搜索所有的select
	    $('select').each(function() {
			var $this = $(this);
			var url = $this.attr("data-url");
			var value =  $this.attr("value");
		    if(url != null && url != '' && url != 'undefined') {
		    	common.ajaxn('post',url,{},function (res) {
		    		if(res.success){
		    			var items = res.data;
		    			if(items != null && items != '' && items != 'undefined'){
		    				$this.append('<option value="">全部</option>');
			         		for(var i = 0; i < items.length; i++){
				         		var option = ('<option value="'+items[i].code+'">'+items[i].name+'</option>');
				         		if(value==items[i].code){
				         			option = ('<option value="'+items[i].code+'" selected>'+items[i].name+'</option>');
				         		}
								$this.append(option);
			         		}
			         		form.render('select');
		    			}
		    		}
		 	    });
		    }
		});
	 	// 监测输入框字符长度
        window.wordLeg = function (obj) {
            var currleg = $(obj).val().length;
            var length = $(obj).attr('maxlength');
            $('.text_count').text(currleg);
        }
	});
</script>