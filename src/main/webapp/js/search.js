var kw="";
$(document).ready(function(){
	
		login();
		//设置搜索框值
		if($("#search_keywords").val()!="" || $("#search_keywords").val()!=null){
			$("#search_keywords").val(decodeURI(GetQueryString("kw")));
			if($("#search_keywords").val()=="null"){
				$("#search_keywords").val("");
			}
			kw=$("#search_keywords").val();
		}
		getMaxCount(kw);
});
function getMaxCount(keywords){
	kw=keywords;
	$.post("../productdoSearchByKeyWordsForPage.action",{keywords:keywords,pageIndex:1},function(page){
		var count = page.maxPage;
		var list=eval(page.list);
		if(list.length<1){
			alert("没有结果");
		}
		var str="";
		for(var i=0;i<list.length;i++){
					
				str+="<li class='goods_choose'><div class='nav_big'><p>"+list[i].pName+"</p>"
				+"<a href='../particular.html?productId="+list[i].pId+"' class='tupian_big' style='background:#f9f5f4 url(../"+list[i].imgUrl+".150x150.jpg)'></a>"
          		+"<div class='jiage_big'><a href='#' class='jiage'>￥"+list[i].pPrice+"</a><a href='#' class='goumai'>立即购买</a></div></div></li>";
		}
		$("#results").html(str);
		cutPage(count);
	},"json");
}
//搜索
function search(keywords,indexNum){
	$.ajax({
 			url:"../productdoSearchByKeyWordsForPage.action",
 			type:"POST",
 			data:{keywords:keywords,pageIndex:indexNum},
 			dataType:"json",
 			success:function(data){ 
 				var list=eval(data.list);
 				var str="";
 				for(var i=0;i<list.length;i++){
 							
	 					str+="<li class='goods_choose'><div class='nav_big'><p>"+list[i].pName+"</p>"
	 					+"<a href='../particular.html?productId="+list[i].pId+"' class='tupian_big' style='background:#f9f5f4 url(../"+list[i].imgUrl+".150x150.jpg)'></a>"
	              		+"<div class='jiage_big'><a href='#' class='jiage'>￥"+list[i].pPrice+"</a><a href='#' class='goumai'>立即购买</a></div></div></li>";
 				}
 				$("#results").html(str);
 				
 			},
 		});
}
//登录检测
function login(){
	$.ajax({
			url:"../userdoGetUserObj.action",
			type:"POST",
			dataType:"json",
			data:{"check":"check"},
			success:function(user){
				if(user!=0){
					if(user.headerImg!="" && user.headerImg!=null){
						var s="<div style='width:35px; height:35px; border-radius:50%; overflow:hidden;'>"
							+"<img src='../"+user.headerImg+"' title='"+user.userName+"' id='userImage' width='35px' height='35px'>"
				   			+"</div>";
				   			$("#user_name").html(s);
							$("#user_name").attr("href","");
							$("#user_name").css("padding","5px 0px 0px 0px");
							$("#user_name").css("margin","0px");
					}else{
							var s="<div style='width:35px; height:35px; border-radius:50%; overflow:hidden;'>"
							+"<img src='../Public/header/defaultHeader.gif' title='"+user.userName+"' id='userImage' width='35px' height='35px'>"
				   			+"</div>";
				   			$("#user_name").html(s);
				   			$("#user_name").attr("href","");
							$("#user_name").css("padding","5px 0px 0px 0px");
							$("#user_name").css("margin","0px");
					}
				}
			},
			error:function(){
				alert("检测用户登录异常！");
			}
		});
}
/**
 * 分页	调用layPage.js
 * @param {Number} maxPage
 */
function cutPage(maxPage){
	
	laypage({
	    cont: $('#turnPage'), //容器。值支持id名、原生dom对象，jquery对象,
	    pages: maxPage, //总页数
	    skip: true, //是否开启跳页
	    skin: 'molv',
	    groups: 5,//连续显示分页数
	    jump: function(obj,first){
	    	if(!first){
	    		console.log("分页",obj);
       			search(kw,obj.curr);
       			
	    	}
    	}
	});
}
function doSearch(obj){
	location.href="search.html?kw="+$(obj).text();
}
/**
 * 获取地址栏参数
 * @param {String} name 参数名
 * return 参数值value
 */
function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = decodeURI(window.location.search.substr(1)).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}