var editor;		//编辑器
var loginFlag=false;	//是否登录
var productId=GetQueryString("productId");	//当前商品id
(function(){
	//初始化=ready()
	function init(){		
		if(productId==null){
			location.href="error.html";
		}
		Login();
		$.getJSON("productdoInfo.action",{pId:productId},function(product){
			if(product=="none"){
				location.href="404.htm";
			}
			
			$("#title_recommend").html(product.pName+"<br /><span>"+
			"货 号：YMMK0000<font id='"+product.pId+"'>"+product.pId+"</font>"+
			"</span>");
			$("#pType").html(product.pType.tName);
			$("#product_Name").html(product.pName);
			$("#product_price").html(product.pPrice);
			$("#product_desc").html(product.pDesc);
			$("#big_img").css("background","url("+product.imgUrl+".340x340.jpg) no-repeat center center");
			$("#small_img").css("background","url("+product.imgUrl+".50x50.jpg) no-repeat center center");
		});
		$("#evaluate").hover(function(){
			$("#evaluate").addClass("activeComment");
			$("#product_details").removeClass("activeComment");
			$("#product_details").css("color","#1B1B1B");
		});
		$("#evaluate").click(function(){
			loadInfo(productId);
		});
		$("#product_details").hover(function(){
			$("#evaluate").removeClass("activeComment");
			$("#product_details").addClass("activeComment");
			$("#product_details").css("color","#FFFFFF");
		});
		
	}
	
	$("#product_details").click(function() {
		$("#gooda_evaluate").css("hidden", "hidden");
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
		calcshopping();
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
		calcshopping();
	});
	 //修改数量
	$(".goods_num").blur(function() {
		var value = $(".goods_num").val();
		if ((value == "") || !(/^[0-9]*[1-9][0-9]*$/.test(value))) {
			alert("数量不能为空，且只能为正整数");
			$(this).val(1);
		}
		calcshopping();
	});
	
			
	window.onload = function() { init() };
})();
function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
//加载评价
	function loadInfo(productId){
		
		$("#product_details").removeClass("activeComment");
		$("#product_desc").css("border","0px");
		$("#evaluate").addClass("activeComment");
		//评论
		var str="<p style='color:#c0c0c0;'>评论空空如也，快来添加吧！</p>";
		//添加回复编辑框与按钮
		var editor="<div class='col-md-12 container' id='recomments'></div><button class='btn-info btn btnSend' onclick='commitNew()'>发表评论</button>";
		$.getJSON("commentdoInfo.action",{pId:productId},function(comments){
			if(comments!="[]" && comments!="none"){						
				comments=eval(comments);
				$("#evaluate").html("商品评价("+comments.length+")");
				str="";
				var imgstr="";
				for(var i=0;i<comments.length;i++){	
					if(comments[i].owner.headerImg!=null){
						imgstr="<img src='"+comments[i].owner.headerImg+"' width='30px' height='30px' alt='"+comments[i].owner.userName+"'/>"
					}else{
						imgstr="<img src='Public/header/default.jpg' width='30px' height='30px'/>";
					}
					str+="<div  class='doComent'><a href='javascript:void(0)' class='comment_owner'>"
					+"<div style='width:30px; height:30px; border-radius:50%; overflow:hidden;display: inline-block;'>"
					+imgstr
					+"</div>"+comments[i].owner.userName+"</a><span class='comment_date'>"+comments[i].cDate+"</span>"
					+"<span class='comment_content' onclick='newReEditor(this)'>"+comments[i].cContent+"</span></div>"
								
				}
				
				$("#product_desc").html(str);
			}
			if(loginFlag){				
				str+=editor;		
			}
			$("#product_desc").html(str);
			newEditor();
		});
	}
	function newEditor(){
		editor = new wangEditor('recomments');
        // 上传图片
        editor.config.uploadImgUrl = '/UACshopping/getFile/UploadImgServiet';
	  	editor.config.uploadImgFns.onload = function (resultText, xhr) {
	        // resultText 服务器端返回的text
	        // xhr 是 xmlHttpRequest 对象，IE8、9中不支持
	
	        // 如果 resultText 是图片的url地址，可以这样插入图片：
	        //editor.command(null, 'insertHtml', '<img src="' + resultText + '" style="max-width:100%;"/>');
	        // 如果不想要 img 的 max-width 样式，也可以这样插入：
	        editor.command(null, 'InsertImage', resultText);
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

        editor.create();	
	}
	function newReEditor($obj){
		alert("评论回复功能暂缓未完成！");
	}
function commitNew(){
				// 获取编辑器区域完整html代码
	            var html = editor.$txt.html();
	        	$.post("commentsaveNewComment.action",{context:html},function(data){
	        		if(data="success"){
	        			alert("发表成功！");
	        			 loadInfo(productId);
	        		}
	        	},"text");
			}
function Login(){
	$.ajax({
			url:"userdoGetUserObj.action",
			type:"POST",
			dataType:"json",
			success:function(user){
				if(user!=0){
					if(user.headerImg!="" && user.headerImg!=null){
						var s="<div style='width:35px; height:35px; border-radius:50%; overflow:hidden;'>"
							+"<img src='"+user.headerImg+"' alt='"+user.userName+"' id='userImage' width='35px' height='35px'>"
				   			+"</div>";
				   			$("#user_name").html(s);
							$("#user_name").attr("href","");
							$("#user_name").css("padding","5px 0px 0px 0px");
							$("#user_name").css("margin","0px");
							$("#user_register").remove();
					}else{
							$("#user_name").html(user.userName);
					}
					//改变登录状态
					loginFlag=true;
				}
			},
			error:function(){
				alert("检测用户登录异常！");
			}
		});
}
