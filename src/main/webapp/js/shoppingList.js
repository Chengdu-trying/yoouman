$(document).ready(function() {
			$.ajax({
	 			url:"shoppingCargetList.action",
	 			type:"POST",
	 			dataType:"json",
	 			success:function(data){
	 				var list=eval(data.products_all);
	 				var str_new="";
	 				for(var i=0;i<list.length;i++){
	 					str_new+="<tr><td width='10'>"
	 								+"<input type='checkbox' name='subBox' value="+list[i].pId+" checked='checked' class='check' onchange='checkProduct(this,"+list[i].pId+")'/></td>"
	 							+"<td width='80'><a href='javascript:void(0);' target='_blank'>"
	 								+"<div class='baobei' style='background:url("+list[i].imgUrl+".50x50.jpg); cursor:pointer'></div></a></td>"
	 							+"<td width='353' id='goods_introduce'>"
	 								+"<em><a  href='particular.html?productId="+list[i].pId+"' target='_blank'>"+list[i].pName+"<span class='reachName'></span>"
	 								+"<br><span style='color:#cccccc'>款式 :普通</span></a></em></td>"
	 							+"<td width='144'>"
	 								+"<p id='shopping_price'>￥<span id='shop_price_437134' class='goodsprice'>"+list[i].pPrice+"</span></p></td>"
	 							+"<td width='111'><div class='tb'>"
	 								+"<a href='javascript:cut("+i+");' class='diminished'><img src='img/buy_20.jpg' width='24' height='24' /></a>"
	 								+"<input type='text' id='"+list[i].pId+"' value='"+list[i].pBuyCount+"' class='goods_num' onchange='checkNumberIsRight($(this),this.value)' />"
	 								+"<a href='javascript:add("+i+");' class='augmented'><img src='img/buy_22.jpg' width='24' height='24' /></a></div></td>"
	 							+"<td width='112' id='googs_price'>￥ <span id='subtotal_"+list[i].pId+"' class='subtotal' data="+list[i].pId+"></span></td>"
	 							+"<td width='57' id='shanchu'>"
	 								+"<a href='shoppingCardelete.action?productId="+list[i].pId+"' class='delete' data='"+list[i].pId+"'>删除</a>"
	 							+"</td></tr>";
	 				}
	 				$("#cartList").append(str_new);
	 				calcshopping();
	 			},
	 			error: function() {
					alert("获取出错！");
				}
	 		});
			
			loadHot();
		});
		function checkProduct(obj,pId){
			if(!$(obj).prop("checked")){
				$.getJSON("shoppingCarcheck.action",{"productId":pId},function(data){
					console.log("勾选是否成功：",data);
				});
			}else{
				$.getJSON("shoppingCarcheckadd.action",{"productId":pId},function(data){
					console.log("勾选是否成功：",data);
				});
			}
			calcshopping();
		}
		function submitOrder(){
			var totoalPrice=$("#totalPrices_hidden").val();
			var orders={"totalPrice":totoalPrice,"products":[]};
			$("#cartList").find("tr").each(function(i, ele) {
					if($(ele).find(".check").prop("checked")){
						var num = $(ele).find(".goods_num").val();
						var pId= $(ele).find("input[name='subBox']:checked").val();
						var product={"pId":pId,"count":num};
						orders.products.push(product);
					}
			});
			console.log("提交数据：order",orders);
			
			
			$.post("ordersaveNewOrder.action",{"order":JSON.stringify(orders)},function(data){
				console.log("提交订单，返回消息：",data);
				if(parseInt(data)>0){
					alert("订单提交成功！");
					location.href="orderdoLoadOrderList.action";
				}else{
					alert("订单提交失败！请刷新页面。");
				}
			},"text");
		}
		$("#submit_button").click(function() {
				//$("#myform").submit();
				submitOrder();
			});
		$(function() {
           $("#checkAll").click(function() {
                $('input[name="subBox"]').prop("checked",this.checked); 
                calcshopping();
            });
            var $subBox = $("input[name='subBox']");
            $subBox.click(function(){
                $("#checkAll").prop("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
            });
        });
			//商品计算函数
			function calcshopping() {
				var shoppingTotal = 0.0;
				$("#cartList").find("tr").each(function(i, ele) {
					//商品数量
					var num = $(ele).find(".goods_num").val();
					var priceSigle=$(ele).find(".goodsprice").html();
					var price_sigle = parseFloat(priceSigle) * num;
					//商品的价格累加
					if($(ele).find(".check").prop("checked")){
//						$.getJSON("shoppingCarnum.action",{"productId":$(ele).find(".check").val(),"num":num},function(data){
//							alert(data);
//						});
						shoppingTotal += price_sigle;
					}	
					//将值显示到页面上
					$(ele).find(".subtotal").text(returnDoubleNum(price_sigle));
				});
				var totalPriceAddYun=0.0;
				if(shoppingTotal>0){
					totalPriceAddYun=shoppingTotal + 8;			
				}		
				$("#totalPrice").text(returnDoubleNum(shoppingTotal));
				$("#totalPrices").text(returnDoubleNum(totalPriceAddYun));
				
				$("#totalPrices_hidden").val(shoppingTotal + 8);
			};
			
			calcshopping();
			
			//修改数量
			$(".goods_num").blur(function() {
				var value = $(".goods_num").val();
				if ((value == "") || !(/^[0-9]*[1-9][0-9]*$/.test(value))) {
					alert("数量不能为空，且只能为正整数");
					$(this).val(1);
				}
				calcshopping();
			});
			function add(obj) {
				var $obj=$($(".goods_num").get(obj));
				var num = $obj.val();
				num++;
				checkNumberIsRight($obj, num);
				if($($(".check").get(obj)).prop("checked")){
					$.post("shoppingCarnum.action",{"productId":$($(".check").get(obj)).val(),"num":num},function(data){
						console.log("增加数量是否成功：",data);
					},"json");
				}	
			};
			function cut(obj) {
				var $obj=$($(".goods_num").get(obj));
				var num = $obj.val();
				num--;
				checkNumberIsRight($obj, num);
				if($($(".check").get(obj)).prop("checked")){
					$.post("shoppingCarnum.action",{"productId":$($(".check").get(obj)).val(),"num":num},function(data){
						console.log("增加数量是否成功：",data);
					},"json");
				}
			};
			/*
			 *检查数量是否正确，obj:检查对象（input标签）num：需要检查的数字
			 *如果数字符合整数，则为该obj置入该值，否则将数量修改为1
			 */
			function checkNumberIsRight(obj,num){
				if ((num == "") || !(/^[0-9]*[1-9][0-9]*$/.test(num))) {
					alert("数量不能为空，且只能为正整数");
					obj.val(1);					
				}else{
					obj.val(num);
				}
				calcshopping();
			}
			
			/*
			 *给一个number返回其double取值的字符串
			 */
			function returnDoubleNum(num){
				var numStr=num+"";
				var n1 = numStr.indexOf(".");
				var numberStr;
				if (n1!=-1) {
					if (numStr.substr(n1 + 1, 2).length<2) {
						numberStr=numStr+"0";
					}
					if (numStr.substr(n1 + 1, 2).length>2) {
						numberStr=numStr + numStr.substr(n1 + 1, 2);
					}
				}else{
					numberStr=numStr+".00";
				}
				return numberStr;
			}
			function loadHot(){
				
				$.getJSON("productdoHotInfo.action",function(data){
					var	list=eval(data);
					var str="<ul class='selling'>";
					for (var i = 0; list!=null && i < list.length; i++) {
						str+="<li>"
								+"<a class='productImg-wrap' style='background:url("+list[i].imgUrl+".150x150.jpg)' title='"+list[i].pName+"' href='particular.html?productId="+list[i].pId+"'></a>"
								+"<h3 class='wenzhi'><a href='particular.html?productId="+list[i].pId+"' title='"+list[i].pName+"'>"+returnLengthSixteen(list[i].pName)+"</a></h3>"
								+"<p>￥"+list[i].pPrice+"</p>"
							+"</li>";
					}
					str+="</ul>";
					$("#hot").html(str);
				});
				
			}
			function returnLengthSixteen (string) {
				if(string.length>14) return	string=string.substr(0,10)+"..."; return string;
			}