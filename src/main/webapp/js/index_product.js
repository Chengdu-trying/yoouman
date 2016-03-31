$.ajax({
 			url:"productgetList",
 			type:"POST",
 			dataType:"json",
 			success:function(data){ 
 				var list=eval(data);
 				goodsList=list;
 				//var num=eval(data.num);
 				var str_new="";
 				var str_dan="";
 				for(var i=0;i<list.length;i++){
 					if(parseInt(list[i].pId)<17 && parseInt(list[i].pId)>15){	//热卖商品
 						alert(list[i].imgUrl);
	 					str_new+="<div class='col-md-2 goods'><div class='goods_title'><p>"+list[i].pName+"</p>"
	 					+"<a href='particular.html?productId="+list[i].pId+"' class='goods_img'><img src='"+list[i].imgUrl+".150x150.jpg' alt='"+list[i].pName+"'></a>"
	              		+"<div class='bottom_goods'><a href='#' class='price'>￥"+list[i].pPrice+"</a><a href='javascript:void(0);' class='buy'>立即购买</a></div></div></div>";
              		}else if(parseInt(list[i].pId)<0){
              			str_dan+="<li class='goods_choose'><div class='nav_big'><p>"+list[i].pName+"</p>"
	 					+"<a href='particular.html?productId="+list[i].pId+"' class='tupian_big' style='background:#f9f5f4 url("+list[i].imgUrl+".150x150.jpg)'></a>"
	              		+"<div class='jiage_big'><a href='#' class='jiage'>￥"+list[i].pPrice+"</a><a href='#' class='goumai'>立即购买</a></div></div></li>";
              		}
 				}
 				$("#goods_list_new").append(str_new);
 				$("#goods_list_dan").append(str_dan);
			 	
 			},
 			error:function(){
 				alert("出错啦！");
 			}
 		});