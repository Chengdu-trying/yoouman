var editor;		//编辑器
var loginFlag=false;	//是否登录
var productId=GetQueryString("productId");	//当前商品id
var commentsStr="<div  class='doComent'  style='height: 80px;'><p style='color:#c0c0c0;'>评论空空如也，快来添加吧！</p></div>";
var pageIndex=1;
var totalCount=1;
var pageFlag=false;
/**
 * user	coocik
 */
var obj;
var product;
(function(){
	//初始化=ready()
	function init(){		
		var $doc=$(window.frames["iFrame1"].document).find("#titile");
		$doc.append("<li><a  onclick='doSearch(this);' style='cursor: pointer;'>全部商品</a></li>")
				
		if(productId==null){
			//location.href="error.html";
		}
		//Login();
		$.post("productdoInfo.action",{pId:productId},function(data){
			if(data=="none"){
				//location.href="404.htm";
			}
			product=data;
			$doc.append("<li><a  onclick='doSearch(this);' style='cursor: pointer;'>"+product.pType.tName+"</a></li>")
			$doc.append("<li><a  onclick='doSearch(this);' style='cursor: pointer;'>"+product.pName+"</a></li>")
			loadProductInfo();
		},"json");
		loadComment(productId,1);
		
	}
	$("#evaluate").hover(function(){
		$("#evaluate").removeClass("evaluate_cl1");
		$("#evaluate").addClass("evaluate_cl2");
		$("#product_details").removeClass("product_cl1");
		$("#product_details").addClass("product_cl2");
	});
	$("#product_details").hover(function(){
		$("#evaluate").removeClass("evaluate_cl2");
		$("#evaluate").addClass("evaluate_cl1");
		$("#product_details").addClass("product_cl1");
		$("#product_details").removeClass("product_cl2");
	});
	$("#evaluate").click(function(){
		
		loadInfo(productId,pageIndex);
		console.log("编辑器检测：",{str:commentsStr});
		
		if (!pageFlag) {
			$("#product_desc").after("<div class='col-md-8 cutpage'><div id='turnPage'></div><div id='showPosition'></div></div>");
			pageFlag=true;
		}
		
		turnPage(totalCount);
	});
	
	$("#product_details").click(function() {
		loadProductInfo();
	});
	$(".augmented").click(function() {
		var num = $(".goods_num").val();
		num++;
		if ((num == "") || !(/^[0-9]*[1-9][0-9]*$/.test(num))) {
			alert("数量不能为空，且只能为正整数");
			$(".goods_num").val(1);
			return;
		}
		$(".goods_num").val(num);
	});
	 //- 减少购买数量
	$(".diminished").click(function() {
		var num = $(".goods_num").val();
		num--;
		if ((num == "") || !(/^[0-9]*[1-9][0-9]*$/.test(num))) {
			alert("数量不能为空，且只能为正整数");
			$(".goods_num").val(1);
			return;
		}
		$(".goods_num").val(num);
	});
	 //修改数量
	$(".goods_num").blur(function() {
		var value = $(".goods_num").val();
		if ((value == "") || !(/^[0-9]*[1-9][0-9]*$/.test(value))) {
			alert("数量不能为空，且只能为正整数");
			$(this).val(1);
		}
	});
			
	window.onload = function() { init() };
})();
//获取地址栏参数
function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	//加载商品介绍
	function loadProductInfo() {
		
		$("#title_recommend").html(product.pName+"<br /><span>"+
				"货 号：YM0000<font id='"+product.pId+"'>"+product.pId+"</font>"+
				"</span>");
				
				$("#product_price").html(product.pPrice);
				$("#product_desc").html(product.pDesc);
				$("#big_img").css("background","url("+product.imgUrl+".340x340.jpg) no-repeat center center");
				$("#small_img").css("background","url("+product.imgUrl+".50x50.jpg) no-repeat center center");
				$("#shopping_img").html("<a href='shoppingCaraddProduct.action'><div class='img_buy'></div></a><a href='javascript:addToShoppingCar("+product.pId+");'><div class='img_shopping_char'></div></a>");
	}
	//加入购物车
	function addToShoppingCar(pId){
		var count=$("#num").val();
		$.post("shoppingCaraddProduct.action",{productId:pId,pBuyCount:count},function(data){
			if(data=="repeat"){
				alert("该商品已在购物车中！");
			}else if(data="success"){
				alert("添加成功！");
			}
		},"text");
	}
	function loadComment(){
		$.post("commentdoInfo.action",{pId:productId,pageIndex:1},function(data){
			totalCount=data.maxPage || 1;
			$("#evaluate").html("商品评价("+data.count+")");
		},"json");
	}
	//加载评价
	function loadInfo(productId,index){
		pageIndex=index;
		var comments;
		$.post("commentdoInfo.action",{pId:productId,pageIndex:index},function(data){
			totalCount=data.maxPage || 1;
			console.log("in loadInfo",totalCount);
			comments=eval(data.list);
			$("#evaluate").html("商品评价("+data.count+")");
			appendCommentStr(comments);
		},"json");
	}
	function appendCommentStr(comments){
		//评论	
		//添加回复编辑框与按钮
		console.log("拼接评论字符串",comments);
		var editorStr="<div class='col-md-12 container' id='recomments'></div><button class='btn-info btn btnSend' onclick='commitNew()'>发表评论</button>";
		if(comments.length>0){								
			commentsStr="";
			var imgstr="";
			for(var i=0;i<comments.length;i++){	
				if(comments[i].owner.headerImg!=null){
					imgstr="<img src='"+comments[i].owner.headerImg+"' width='30px' height='30px' title='"+comments[i].owner.userName+"'/>"
				}else{
					imgstr="<img src='Public/header/defaultHeader.gif' width='30px' height='30px'  title='"+comments[i].owner.userName+"'/>";
				}
				commentsStr+="<div  class='doComent'><a href='javascript:void(0)' class='comment_owner'>"
				+"<div style='width:30px; height:30px; border-radius:50%; overflow:hidden;display: inline-block;'>"
				+imgstr
				+"</div>"+comments[i].owner.userName+"</a><span class='comment_date'>"+comments[i].cDate+"</span>"
				+comments[i].cContent+"<span class='comment_content' onclick='newReEditor(this)'>回复该评论</span></div>";
				
							
			}
			commentsStr+="<button class='btn-info btn ' id='showEditor' onclick='fabiao(this)'>发表评价</button>";
		}else{
			commentsStr+="<button class='btn-info btn ' style='position: static;' id='showEditor' onclick='fabiao(this)'>发表评价</button>";
		}
		
		$("#product_desc").html(commentsStr);
	}
	function newEditor(){
		var editorStr="<div class='col-md-12 container' id='recomments'></div><button class='btn-info btn btnSend' onclick='commitNew()'>发表</button>";
		$("#product_desc").append(editorStr);
		console.log("实例化编辑器");
		editor = new wangEditor('recomments');
        // 上传图片
        editor.config.uploadImgUrl = 'http://localhost:8080/UACshopping/UploadImgServiet';
	  	editor.config.uploadImgFns.onload = function (resultText, xhr) {
	        // resultText 服务器端返回的text
	        // xhr 是 xmlHttpRequest 对象，IE8、9中不支持
	
	        // 如果 resultText 是图片的url地址，可以这样插入图片：
	        editor.command(null, 'insertHtml', '<img src="' + resultText + '" style="max-width:100%;"/>');
	        // 如果不想要 img 的 max-width 样式，也可以这样插入：
	       // editor.command(null, 'InsertImage', resultText);
	    };
	    editor.config.menus = [
		        'bold',        
		        'underline',
		        'forecolor',
		        'bgcolor',
		        'link',
		        'unlink',
		        'emotion',
		         '|',     // '|' 是菜单组的分割线
		        'img'
		];
		editor.config.emotions = {
        'default': {
            title: '默认',
            size: 18,
            imgs: [
                'static/emotions/default/1.gif',
                'static/emotions/default/2.gif',
                'static/emotions/default/3.gif',
                'static/emotions/default/4.gif',
                'static/emotions/default/5.gif',
                'static/emotions/default/6.gif',
                'static/emotions/default/7.gif',
                'static/emotions/default/8.gif',
                'static/emotions/default/9.gif',
                'static/emotions/default/10.gif',
                'static/emotions/default/11.gif',
                'static/emotions/default/12.gif',
                'static/emotions/default/13.gif',
                'static/emotions/default/14.gif',
                'static/emotions/default/15.gif',
                'static/emotions/default/16.gif',
                'static/emotions/default/17.gif',
                'static/emotions/default/18.gif',
                'static/emotions/default/19.gif',
                'static/emotions/default/20.gif',
                'static/emotions/default/21.gif',
                'static/emotions/default/22.gif',
                'static/emotions/default/23.gif',
                'static/emotions/default/24.gif',
                'static/emotions/default/25.gif',
                'static/emotions/default/26.gif',
                'static/emotions/default/27.gif',
                'static/emotions/default/28.gif',
                'static/emotions/default/29.gif',
                'static/emotions/default/30.gif',
                'static/emotions/default/31.gif',
                'static/emotions/default/32.gif',
                'static/emotions/default/33.gif',
                'static/emotions/default/34.gif',
                'static/emotions/default/35.gif',
                'static/emotions/default/36.gif',
                'static/emotions/default/37.gif',
                'static/emotions/default/38.gif',
                'static/emotions/default/39.gif',
                'static/emotions/default/40.gif',
                'static/emotions/default/41.gif',
                'static/emotions/default/42.gif',
                'static/emotions/default/43.gif',
                'static/emotions/default/44.gif',
                'static/emotions/default/45.gif',
                'static/emotions/default/46.gif',
                'static/emotions/default/47.gif',
                'static/emotions/default/48.gif',
                'static/emotions/default/49.gif',
                'static/emotions/default/50.gif'
            ]
        },
        'jinxing': {
            title: '金星',
            size: 50,
            imgs: [
                'static/emotions/jinxing/1.gif',
                'static/emotions/jinxing/2.gif',
                'static/emotions/jinxing/3.gif',
                'static/emotions/jinxing/4.gif',
                'static/emotions/jinxing/5.gif',
                'static/emotions/jinxing/6.gif'
            ]
        }
    };
        editor.create();	
	}
	function newReEditor($obj){
		alert("评论回复功能暂缓未完成！");
	}
	function fabiao(obj){
		$(obj).remove();
		console.log("销毁成功！");
		newEditor();
	}
	
/**
 * 提交新评价
 */
function commitNew(){
	console.log("登录检测",loginFlag);
				// 获取编辑器区域完整html代码
				if(loginFlag){
		            var html = editor.$txt.html();
		            editor.destroy();
		        	$.post("commentsaveNewComment.action",{context:html},function(data){
		        		if(data!=""){
		        			alert("发表成功！");
		        			window.location.hash = "#describe";
		        			$("#recomments").remove();
		        			loadInfo(productId,1);
		        			console.log("发布后：",{comment:commentsStr})
		        			commentsStr+="<button class='btn-info btn' id='showEditor' onclick='fabiao(this)'>发表评价</button>";
							$("#product_desc").html(commentsStr);
		        			/*var tempstr="";
		        			var user=obj;
		        			console.log("发表！",{loginFlag,user});
		        			if(user.headerImg!=null){
		    					imgstr="<img src='"+user.headerImg+"' width='30px' height='30px' title='"+user.userName+"'/>"
		    				}else{
		    					imgstr="<img src='Public/header/defaultHeader.gif' width='30px' height='30px' title='"+user.userName+"'/>";
		    				}
		        			tempstr+="<div  class='doComent'><a href='javascript:void(0)' class='comment_owner'>"
		    				+"<div style='width:30px; height:30px; border-radius:50%; overflow:hidden;display: inline-block;'>"
		    				+imgstr
		    				+"</div>"+user.userName+"</a><span class='comment_date'>"+data.cDate+"</span>"
		    				+html+"<span class='comment_content' onclick='newReEditor(this)'>回复该评论</span></div>"
		    				$("#recomments").before(tempstr);
		    				$("#recomments").remove();
		    				$(".btnSend").remove();
		    				newEditor();*/
		    				
		        		}
		        	},"json");
		        }else{
		        	alert("您还没有登录！");
		        	
		        }
}
function Login(){
	
	$.ajax({
			url:"userdoGetUserObj.action",
			type:"POST",
			dataType:"json",
			data:{"check":"check"},
			success:function(user){
				if(user!=0){
					obj=user;
					loginFlag=true;
					console.log("登录成功！",{loginflag:loginFlag,user:obj});
				}
			}
		});
}

function turnPage(count){
	console.log("in turnPage",count);
	laypage({
	     cont: $('#turnPage'), //容器。值支持id名、原生dom对象，jquery对象,
	    pages: count, //总页数
	    groups: 0,
	    first: false,
	    last: false,
	    jump: function(obj,first){
	    	console.log("jump",first);
	    	if(!first){
		    	loadInfo(productId,obj.curr);
		    	
		        $("#showPosition").html('目前正在第'+ obj.curr +'页，一共有：'+ obj.pages +'页');
	      }
	    }
	});
}
/**
 * 回到页面顶部
 * @param acceleration 加速度
 * @param time 时间间隔 (毫秒)
 **/
function goTop(acceleration, time) {
	acceleration = acceleration || 0.1;
	time = time || 16;
 
	var x1 = 0;
	var y1 = 0;
	var x2 = 0;
	var y2 = 0;
	var x3 = 0;
	var y3 = 0;
 
	if (document.documentElement) {
		x1 = document.documentElement.scrollLeft || 0;
		y1 = document.documentElement.scrollTop || 0;
	}
	if (document.body) {
		x2 = document.body.scrollLeft || 0;
		y2 = document.body.scrollTop || 0;
	}
	var x3 = window.scrollX || 0;
	var y3 = window.scrollY || 0;
 
	// 滚动条到页面顶部的水平距离
	var x = Math.max(x1, Math.max(x2, x3));
	// 滚动条到页面顶部的垂直距离
	var y = Math.max(y1, Math.max(y2, y3));
 
	// 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
	var speed = 1 + acceleration;
	window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
 
	// 如果距离不为零, 继续调用迭代本函数
	if(x > 0 || y > 0) {
		var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
		window.setTimeout(invokeFunction, time);
	}
}
/**
 * 导向搜索页面
 */
function search(){
	location.href="page/search.html?kw="+$("#search_keywords").val();
}

function doSearch(obj){
	location.href="page/search.html?kw="+$(obj).text();
}
/**
 * 按比例缩放
 * @param {Object} element
 * @param {Object} maxWidth
 * @param {Object} maxHeight
 */
function resize(element, maxWidth, maxHeight){
	if(element.width > maxWidth || element.height > maxHeight){
		if(element.width / element.height > maxWidth / maxHeight){
			element.width = maxWidth;
		}else{
			element.height = maxHeight;
		}
	}
}
