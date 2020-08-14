layui.define(['element', 'layer','common','element'], function (exports) {
    var $ = layui.$, $body = $('body'),element = layui.element,ayer = layui.layer;
	//=======================================================================================================================
	var $jquery = layui.jquery, common = layui.common;
	//获取菜单
	common.ajax('post',webRoot+'/permission/menu.html',null,function(res){
		if(res.success){
			var m = menu_tree_d(res.data);
			if(m != null && m.length>0){
				$jquery('.layui-nav-tree').html("")
				for(var t=0;t<m.length;t++){
					var li = $jquery('<li></li>').addClass('layui-nav-item');
					var url = m[t].url;
					if(url!=undefined && url!=null && url!=""){
						url = webRoot+m[t].url;
					}
					var a = $jquery('<a></a>').attr('href',url);
					/*var i = $jquery('<img ></img>').addClass('layui-icon')
					var icon = m[t].icon;
					if(icon!=undefined && icon!=null && icon!=""){
						i.attr('src',webRoot + icon);
					}
					a.append(i);*/
					var icon = m[t].icon;
					if(icon!=undefined && icon!=null && icon!=""){
						a.append('<svg class="layui-icon-ali-ic" aria-hidden="true"><use xlink:href="#'+icon+'"></use></svg>&nbsp;&nbsp;');
					}else{				
						a.append('<svg class="layui-icon-ali-ic" aria-hidden="true"><use xlink:href=""></use></svg>&nbsp;&nbsp;');
					}
					a.append('<em>'+m[t].text+'</em>');
					li.append(a);
					//判断是否有儿子节点
					if(m[t].children != null && m[t].children.length>0){
						var dl = $jquery('<dl></dl>').addClass('layui-nav-child');
						for(var j=0;j<m[t].children.length;j++){
							var curl = m[t].children[j].url;
							if(curl!=undefined && curl!=null && curl!=""){
								curl = webRoot + m[t].children[j].url;
							}
							var iicon = m[t].children[j].icon;
							if(iicon!=undefined && iicon!=null && iicon!=""){
								dl.append('<dd><a href="'+curl+'"><svg class="layui-icon-ali-ic" aria-hidden="true"><use xlink:href="#'+iicon+'"></use></svg>&nbsp;&nbsp;'+m[t].children[j].text+'</a></dd>');
							}else{				
								dl.append('<dd><a href="'+curl+'">'+m[t].children[j].text+'</a></dd>');
							}
						}
						li.append(dl);
					}
					$jquery('.layui-nav-tree').append(li);
					element.init();
				}
			}
		}
	},false)
	
	//==========================================================================================================

	var screen_size = {
        pc: [991, -1],
        pad: [768, 990],
        mobile: [0, 767]
    }

    var getDevice = function () {
        var width = $(window).width();
        for (var i in screen_size) {
            var sizes = screen_size[i],
                min = sizes[0],
                max = sizes[1];
            if (max == -1) max = width;
            if (min <= width && max >= width) {
                return i;
            }
        }
        return null;
    }

    var isDevice = function (label) {
        return getDevice() == label;
    }

    var isMobile = function () {
        return isDevice('mobile');
    }

    var Tab = function (el) {
        this.el = el;
        this.urls = [];
    }

    Tab.prototype.content = function (src) {
        var iframe = document.createElement("iframe");
        iframe.setAttribute("frameborder", "0");
        iframe.setAttribute("src", src);
        iframe.setAttribute("data-id", this.urls.length);
        return iframe.outerHTML;
    };

    Tab.prototype.is = function (url) {
        return (this.urls.indexOf(url) !== -1)
    };

    Tab.prototype.add = function (title, url) {
        if (this.is(url)) return false;
        this.urls.push(url);
        element.tabAdd(this.el, {
            title: title,
            content: this.content(url),
            id: url
        });

        this.change(url);
    };
    Tab.prototype.change = function (url) {
        element.tabChange(this.el, url);
        if (navigator.userAgent.indexOf("Firefox") > -1) {
            $('.layui-show')[0].children[0].attributes[0].value = url + '?time=' + new Date() + ''
        } else {
            $('.layui-show')[0].children[0].attributes[1].value = url + '?time=' + new Date() + ''
        }
    };

    Tab.prototype.deletes = function (url) {
        element.tabDelete(this.el, url);
    };

    Tab.prototype.onChange = function (callback) {
        element.on('tab(' + this.el + ')', callback);
    };

    Tab.prototype.onDelete = function (callback) {
        var self = this;
        element.on('tabDelete(' + this.el + ')', function (data) {
            console.log(data)
            var i = data.index;
            self.urls.splice(i, 1);
            callback && callback(data);
        });
    };

    var tabUrl = ''
    var Home = function () {
        var tabs = new Tab('tabs'), navItems = [];
        // this.tabs = tabs;
        // window.tabs = tabs;
        $('#Nav a').on('click', function (event) {
            event.preventDefault();
            var $this = $(this),
                url = $this.attr('href'),
                title = $.trim($this.text());
            tabUrl = url
            if (url && url !== 'javascript:;') {
                if (tabs.is(url)) {
                    // 左侧菜单不变
                    tabs.change(url);
                } else {
                    // 左侧菜单变化
                    navItems.push($this);
                    tabs.add(title, url);
                }
            }


            // 点击左侧菜单 如果缩起来 点击左右展开
            if ($('.slide-sidebar').find('i').hasClass('ai-menuunfold')) {
                $('.slide-sidebar').find('i').removeClass('ai-menuunfold').addClass('ai-menufold');
                $('.layui-layout-body').find('.layui-layout-admin').removeClass('fold-side-bar');
                if (isMobile()) $('.mobile-mask').show();
            }

            // 点击菜单 可以显示和隐藏当前二级菜单
            if (!$this.closest('li.layui-nav-item').hasClass('layui-nav-itemed')) {
                $this.closest('li.layui-nav-item').removeClass('layui-nav-itemed')
            } else {
                $this.closest('li.layui-nav-item')
                    .addClass('layui-nav-itemed')
                    .siblings()
                    .removeClass('layui-nav-itemed');
            }

        });

        // 默认触发第一个子菜单的点击事件
        // $('#Nav li.layui-nav-item:eq(0) > dl.layui-nav-child > dd > a:eq(0)').trigger('click');
        $('#Nav li.layui-nav-item:eq(0) > a:eq(0)').trigger('click');
        tabs.onChange(function (data) {
            var i = data.index, $this = navItems[i];
            if ($this && typeof $this === 'object') {
                $('#Nav dd').removeClass('layui-this');
                $this.parent('dd').addClass('layui-this');
                $this.closest('li.layui-nav-item').addClass('layui-nav-itemed').siblings().removeClass('layui-nav-itemed');
            }
            tabs.onDelete(function (data) {
                var i = data.index;
                navItems.splice(i, 1);
            });
        });



        this.slideSideBar();
        //处理页面按钮点击跳转页面方法
        window.tabChange = function (url, flag) {
            if (flag) {
                element.tabDelete('tabs', tabUrl)
            }
            var items = $('#Nav a');
            for (var i = 0; i < items.length; i++) {
                var item = $(items[i]);
                if (item.attr('href') == url) {
                    item.trigger('click')
                    return;
                }
            }
        };
    }

    Home.prototype.slideSideBar = function () {
        var $slideSidebar = $('.slide-sidebar'),
            $pageContainer = $('.layui-body'),
            $mobileMask = $('.mobile-mask');

        $slideSidebar.click(function (e) {
            e.preventDefault();
            var $this = $(this), $icon = $this.find('i'),
                $admin = $body.find('.layui-layout-admin');
            var toggleClass = isMobile() ? 'fold-side-bar-xs' : 'fold-side-bar';
            if ($icon.hasClass('ai-menufold')) {
                $icon.removeClass('ai-menufold').addClass('ai-menuunfold');
                $admin.addClass(toggleClass);
                if (isMobile()) $mobileMask.show();
            } else {
            	$slideSidebar.removeClass('layui-this');
                $icon.removeClass('ai-menuunfold').addClass('ai-menufold');
                $admin.removeClass(toggleClass);
                if (isMobile()) $mobileMask.hide();
            }
        });

        var tipIndex;
        // 菜单收起后的模块信息小提示
        $('#Nav li > a').hover(function () {
            var $this = $(this);
            if ($('.slide-sidebar').find('i').hasClass('ai-menuunfold')) {
                tipIndex = layer.tips($this.find('em').text(), $this);
            }
        }, function () {
            if ($('.slide-sidebar').find('i').hasClass('ai-menuunfold') && tipIndex) {
                layer.close(tipIndex);
                tipIndex = null
            }
        })

        if (isMobile()) {
            $mobileMask.click(function () {
                $slideSidebar.trigger('click');
            });
        }
    }
    exports('home', new Home);
});

function menu_tree_d(datas){
	var dataArray = [];
    for(var j = 0; j < datas.length; j++) {
    	var data = datas[j];
        var parentCode = data.parentCode;
        if(parentCode == '' || parentCode == 'none' || parentCode == '-1' || parentCode == '00') {
            var obj = {
            		id:data.id,
            		code:data.code,
            		text:data.text,
            		parentCode:data.parentCode,
            		icon:data.icon,
            		url:data.url,
            		description:data.description,
        			children:[]	
            }
            dataArray.push(obj);
        }
    }
    return menu_tree(datas, dataArray);
}
function menu_tree(datas, dataArray) {
    for(var j = 0; j < dataArray.length; j++) {
        var dataArrayIndex = dataArray[j];
        var childrenArray = [];
        var catl_idp = dataArrayIndex.code;
        for(var i = 0; i < datas.length; i++) {
            var data = datas[i];
            var parentCode = data.parentCode;
            if(parentCode == catl_idp) {//判断是否为儿子节点
            	var obj = {
            			id:data.id,
	            		code:data.code,
                		text:data.text,
                		parentCode:data.parentCode,
                		icon:data.icon,
                		url:data.url,
                		description:data.description,
            			children:[]	
                }
                childrenArray.push(obj);
            }

        }
        dataArrayIndex.children = childrenArray;
        if(childrenArray.length > 0) {//有儿子节点则递归
        	menu_tree(datas, childrenArray);
        }

    }
    return dataArray;
}