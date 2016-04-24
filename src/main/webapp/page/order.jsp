<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>我的订单</title>
		<link rel="stylesheet" href="../css/bootstrap.css" />
		<link rel="stylesheet" href="../css/user_order.css" />
		<link rel="stylesheet" href="../css/default.css">
		<link rel="stylesheet" href="../css/user_common.css" />
		<link rel="stylesheet" href="../css/my_personal.css" />
		<link rel="stylesheet" href="../css/hslider_style1.css" />
		<link rel="stylesheet" href="../css/index.css" />
		<link rel="stylesheet" href="../css/new_shopping.css" />
		<link rel="stylesheet" href="../css/hot.css" />

		<style>
			.other a {
				height: 24px;
				border: 1px solid #CE4A00;
				background: #FFEDE3;
				padding: 0px;
				color: #CC001C;
				line-height: 24px;
				width: 40px
			}
			body {
				background: #FFFFFF
			}
			#nav_{
				padding-left:45%;
			}
			#banner{
				text-align:left;
			}
			a{
				    color: #337ab7;
    text-decoration: none;
			}
			a:hover{
				    color: #23527c;
    text-decoration: underline;
			}
		</style>
	</head>

	<body class="container">
	
			<nav class="navbar navbar-default" id="nav_">
			<div class="row">
				<div>
				  <ul class="nav navbar-nav ">
					
					 <li class="dropdown">
						<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
							我的账户
						   <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
						   <li><a href="#">待付款订单</a></li>
						   <li><a href="#">已付款订单</a></li>
						   <li><a href="#">短消息</a></li>
						</ul>
					 </li>
					 <li><s></s><a href="../shoppingList.jsp"><span class="gouwu"></span>购物车<span class="naber" id="cart_number">0</span>件</a>
					 </li>
					  <li class="fore"><s></s><a href="javascript:void(0)">收藏夹</a></li>
					  <li><a href="../Login.html">请登录 </a></li>					
					<li><a href="../Register.html" id="user_register">免费注册</a></li>
					 <li><a href="javascript:void(0);">收藏本店</a></li>
				  </ul>
			   </div>				  	
			  </div>				  
			</nav>
			<div class="row" id="banner">
				<img src="../images/logo.jpg.jpg" />
				 
			</div>
			<!--搜索-->
			<div class="row" style="padding-bottom:10px;">
			 	<div class="col-md-6 text-left">
			  		<div style="padding-top:6px;"> 热门搜索：<a href="#" class="label label-info">T恤</a>&nbsp;<a 	href="#" class="label label-info">帆布包</a>&nbsp;<a href="#" class="label label-info">公仔</a>&nbsp;<a href="#" class="label label-info">扇子</a>&nbsp;<a href="#" class="label label-info">卡贴</a>&nbsp;<a href="#" class="label label-info">手办</a>&nbsp;<a href="#" class="label label-info">海报</a>&nbsp;<a href="#" class="label label-info">同学录</a>
	    			</div> 			
				 </div>		 	
				 <div class="col-md-6">
				  		<div class="row" >				  				
				  				<table align="right" border="0px" cellspacing="0px" cellpadding="0px" width="80%">				  					
				  					<tr>
				  						<td>
				  							<span class="glyphicon glyphicon-search"></span>
				  						</td>
				  						<td>
				  							<input type="text" class="form-control " placeholder="请输入关键词搜索">
				  						</td>
				  						
				  						<td>
				  							<button type="button" class="btn btn-info">搜索</button>				  							
				  						</td>
				  					</tr>
				  				</table>				  			
				  		</div>
				 </div>			  	 
				</div>
		<ol class="breadcrumb text-left">
		  <li><a href="../index.html">首页</a></li>
		  <li><a href="../user.html">个人中心</a></li>
		  <li>订单管理</li>
		</ol>
		<div class="nav_big" style="width: 95%;">
			<!-- 新品上架 -->
			<div class="container" id="new_shopping">
				<div class="container" id="new_shopping_div"></div>
				<ul>
					<li>
						<div class="new_img">
							<a href="javascript:void(0)">
								<div class="container" style="background: url(../img/56931b2497ff3.jpg.110x110.jpg) repeat scroll 0% 0%;">
								</div>
							</a>
						</div>
						<div class="new_shopping_font">
							<a href="javascript:void(0)">MK十周年典藏...</a>
						</div>
						<div class="new_shopping_price" style="font-size: 12px;text-align: center;font-family: Arial, Helvetica, sans-serif, " 宋体 ", "黑体 ";">
							商城价
							<font>￥58.00</font>
						</div>
					</li>
					<li>
						<div class="new_img">
							<a href="javascript:void(0)">
								<div class="container" style="background: url(../img/56931b2497ff3.jpg.110x110.jpg) repeat scroll 0% 0%;">
								</div>
							</a>
						</div>
						<div class="new_shopping_font">
							<a href="javascript:void(0)">MK十周年典藏...</a>
						</div>
						<div class="new_shopping_price">
							商城价
							<font>￥58.00</font>
						</div>
					</li>
					<li>
						<div class="new_img">
							<a href="javascript:void(0)">
								<div class="container" style="background: url(../img/56931b2497ff3.jpg.110x110.jpg) repeat scroll 0% 0%;">
								</div>
							</a>
						</div>
						<div class="new_shopping_font">
							<a href="javascript:alert(${orderList});">MK十周年典藏...</a>
						</div>
						<div class="new_shopping_price">
							商城价
							<font>￥58.00</font>
						</div>
					</li>
				</ul>
			</div>

			<!--==============================================个人中心right==================================-->
			<div class="Personal_center_right_big" style="max-width: 80%;padding-left: 4%; width: 100%;">
				<!--位置-->
				<div class="position_common">
					
				</div>
				<!--订单，评论，确认-->
				
				<div class="clear"></div>
				<!--订单-->
				<div class="center_table_big">
					<table cellspacing="0" cellpadding="0" width="100%">
						<thead>
							<tr class="col-name">
								<th width="320">宝贝</th>
								<th width="80" cclass="price">单价(元)</th>
								<th width="60" class="quantity">数量</th>
								<!--<th class="after-service">售后</th>-->
								<th class="amount" width="90">实付款(元)</th>
								<th class="other" width="120">操作</th>
							</tr>
						</thead>
						<c:forEach var="order" items="${ordersList}" varStatus="vs">
							<tbody>
								<tr class="sep-row">
									<td colspan="5"></td>
								</tr>
								<tr class="toolbar">
									<td colspan="5">
										订单号：<a href="#" target="_blank">${order.orderNum }</a> <span>下单时间：${order.createDate }</span></td>
								</tr>
								<tr>
									<td colspan="3">
										<c:forEach items="${order.products}" var="product" varStatus="vs">
											<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin:0px; padding:0px;">
												<tbody>
													<tr>
														<td class="baobei borde_right" style="border-bottom:none;">
															<a href="#" class="tuzhan" style="background:url(http://localhost:8080/UACshopping/${product.imgUrl }.50x50.jpg);"></a>
															<div class="wenzhan"><a href="../particular.html?productId=${product.pId }" class="baobei-name">${product.pName }</a>
																<!-- <div class="spec"><span>第一删</span><span>第一删</span></div>-->
															</div>
														</td>
														<td class="price borde_right" style="border-bottom:none;">${product.pPrice }</td>
														<td class="quantity borde_right" style="border-bottom:none;">${product.pBuyCount }</td>
														<!-- <td class="after-service borde_right"><a href="#">申请退款</a></td>-->
													</tr>
												</tbody>
											</table>
										</c:forEach>
									</td>
									<td width="605" colspan="2">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
											<tbody>
												<tr>
													<td rowspan="3" class="amount borde_right" style="border:none;">
														<c:choose>
															<c:when test="${order.orderStatus!='0'}">
																<strong>${order.totalPrice} </strong>
															</c:when>
															<c:otherwise>
																0.0
															</c:otherwise>
														</c:choose>

													</td>
													<td rowspan="3" class="trade-status borde_right" style="border:none;">
														<c:choose>
															<c:when test="${order.orderStatus=='0' }">
																<p class="co7">等待买家付款</p>
															</c:when>
															<c:when test="${order.orderStatus=='1' }">
																<p class="co7">等待收货</p>
															</c:when>
															<c:otherwise>
																<p class="co7">订单已完成</p>
															</c:otherwise>
														</c:choose>
														<a href="../particular.html?productId=${order.products.get(0).pId}" target="_blank">订单详情</a>
													</td>
													<td rowspan="3" class="other" style="border:none;">
													<c:choose>
															<c:when test="${order.orderStatus=='0' }">	
																<a href="javascript:;" class="verifystatus fukuan">立即付款</a></br>
																&nbsp;&nbsp;<a href="javascript:void(0)" class="verifystatus">取消订单</a>
															</c:when>
															<c:when test="${order.orderStatus=='1' }">
																<a href="javascript:fukuan(${order.orderId })" class="verifystatus">完成收货</a></br>
															</c:when>
															<c:otherwise>
																<a href="javascript:fukuan(${order.orderId })" class="verifystatus">立即评价</a></br>
															</c:otherwise>
														</c:choose>
														
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
					<div class="theme-popover">
		<div class="theme-poptit">
		<a href="javascript:void(0);" title="关闭" class="close">×</a>
		<h3>登录 是一种态度</h3>
		</div>
		<div class="theme-popbod dform">
		<form class="theme-signin" name="loginform" action="" method="post">
		<ol>
		<li><h4>你必须先登录！</h4></li>
		<li><strong>用户名：</strong><input class="ipt" type="text" name="log" value="lanrenzhijia" size="20" /></li>
		<li><strong>密码：</strong><input class="ipt" type="password" name="pwd" value="***" size="20" /></li>
		<li><input class="btn btn-primary" type="submit" name="submit" value=" 登 录 " /></li>
		</ol>
		</form>
		</div>
		</div>
		<div class="theme-popover-mask"></div>
					<!--页码-->
					<div id="pages"></div>
					<div class="clear"></div>
				</div>

				<!--热销产品-->
				<div class="selling_big hsliber">
					<div class="selling_big_biao">热销商品</div>
					<s class="slide_control_l" style="display:none;"></s>
					<div id="hot">
						<ul class="selling">
							<li>
								<a class="productImg-wrap" style="background:url(../img/55249faf6f934.jpg.150x150.jpg)" title="MK大海报·学院Pie" href="javascript:void(0);"></a>
								<h3 class="wenzhi"><a href="javascript:void(0);" title="MK大海报·学院Pie">MK大海报·学院Pie...</a></h3>
								<p>￥8.00</p>
							</li>
							<li>
								<a class="productImg-wrap" style="background:url(../img/54ee974f129d6.jpg.150x150.jpg)" title="斗破苍穹手办[送独家心意卡]" href="javascript:void(0);"></a>
								<h3 class="wenzhi"><a href="javascript:void(0);" title="斗破苍穹手办[送独家心意卡]">斗破苍穹手办[送独家心...</a></h3>
								<p>￥39.00</p>
							</li>
							<li>
								<a class="productImg-wrap" style="background:url(../img/54a212c181386.jpg.150x150.jpg)" title="烛天暖萌抱枕（可插手）" href="javascript:void(0);"></a>
								<h3 class="wenzhi"><a href="javascript:void(0);" title="烛天暖萌抱枕（可插手）">烛天暖萌抱枕（可插手...</a></h3>
								<p>￥68.00</p>
							</li>
							<li>
								<a class="productImg-wrap" style="background:url(../img/541a4de0be294.jpg.150x150.jpg)" title="萌趣帆布包" href="javascript:void(0);"></a>
								<h3 class="wenzhi"><a href="javascript:void(0);" title="萌趣帆布包">萌趣帆布包...</a></h3>
								<p>￥88.00</p>
							</li>
							<li>
								<a class="productImg-wrap" style="background:url(../img/53d613bc45dd7.jpg.150x150.jpg)" title="MK暴走邻家烛天毛绒公仔 特价" href="javascript:void(0);"></a>
								<h3 class="wenzhi"><a href="javascript:void(0);" title="MK暴走邻家烛天毛绒公仔 特价">MK暴走邻家烛天毛绒公...</a></h3>
								<p>￥60.00</p>
							</li>
							<li>
								<a class="productImg-wrap" style="background:url(../img/537090cc25895.jpg.150x150.jpg)" title="MK8周年漫画短篇集" href="javascript:void(0);"></a>
								<h3 class="wenzhi"><a href="javascript:void(0);" title="MK8周年漫画短篇集">MK8周年漫画短篇集...</a></h3>
								<p>￥12.00</p>
							</li>
						</ul>
					</div>
					<b class="slide_control_r" style="display:none"></b>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<!-- 底部 --->
		<div class="bottom_big" style="width: 100%;padding-left: 10%;">
			<div class="help_big" style="width: 100%;max-width: 100%;">
				<div class="help">
					<div class="biao_help"><span class="biao_help_1"></span>新手指南</div>
					<ul>
						<li>
							<a href="javascript:void(0)">个人中心功能介绍</a>
						</li>
						<li>
							<a href="javascript:void(0)">新用户注册</a>
						</li>
						<li>
							<a href="javascript:void(0)">找回密码</a>
						</li>
						<li>
							<a href="javascript:void(0)">怎样下订单</a>
						</li>
					</ul>
				</div>
				<div class="help">
					<div class="biao_help"><span class="biao_help_2"></span>付款|退款方式</div>
					<ul>
						<li>
							<a href="javascript:void(0)">支付方式</a>
						</li>
						<li>
							<a href="javascript:void(0)">如何退款</a>
						</li>
						<li>
							<a href="javascript:void(0)">支付常见问题</a>
						</li>
					</ul>
				</div>
				<div class="help">
					<div class="biao_help"><span class="biao_help_3"></span>配送方式</div>
					<ul>
						<li>
							<a href="javascript:void(0)">发货时间及方式</a>
						</li>
						<li>
							<a href="javascript:void(0)">配送范围 </a>
						</li>
						<li>
							<a href="javascript:void(0)">配送常见问题</a>
						</li>
						<li>
							<a href="javascript:void(0)">商品验货与签收</a>
						</li>
					</ul>
				</div>
				<div class="help">
					<div class="biao_help"><span class="biao_help_4"></span>售后服务</div>
					<ul>
						<li>
							<a href="javascript:void(0)">退换货原则</a>
						</li>
						<li>
							<a href="javascript:void(0)">退换货流程</a>
						</li>
						<li>
							<a href="javascript:void(0)">补发货流程</a>
						</li>
					</ul>
				</div>
				<div class="help">
					<div class="biao_help"><span class="biao_help_5"></span>帮助中心</div>
					<ul>
						<li>
							<a href="javascript:void(0)">新手上路</a>
						</li>
						<li>
							<a href="javascript:void(0)">联系我们</a>
						</li>
					</ul>
				</div>
				<div class="clear">
					<input id="requestflag" type="hidden" value="" />
				</div>
			</div>
		</div>
		
		
		<iframe NAME="content_frame" width=100% frameborder=0 scrolling="no" height=200 marginwidth=0 marginheight=0 SRC="../bottom.html"></iframe>

		<script src="../js/jquery-1.9.1.js" type="text/javascript"></script>
		<script src="../js/bootstrap.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			loadOrders();
			$('.fukuan').click(function(){
				$('.theme-popover-mask').fadeIn(100);
				$('.theme-popover').slideDown(200);
			});
			$('.theme-poptit .close').click(function(){
					$('.theme-popover-mask').fadeOut(100);
					$('.theme-popover').slideUp(200);
			});
		});
	function loadOrders(){
		$.ajax({
			url:"../userdoGetUserObj.action",
			type:"POST",
			dataType:"json",
			data:{"check":"check"},
			success:function(user){
				if(user!=0){
					if(user.headerImg!="" && user.headerImg!=null){
						var s="<div style='width:35px; height:35px; border-radius:50%; overflow:hidden;'>"
							+"<img src='../"+user.headerImg+"' alt='"+user.userName+"' id='userImage' width='35px' height='35px'>"
				   			+"</div>";
				   			$("#user_name").html(s);
							$("#user_name").attr("href","");
							$("#user_name").css("padding","5px 0px 0px 0px");
							$("#user_name").css("margin","0px");
							$("#user_register").remove();
					}else{
							var s="<div style='width:35px; height:35px; border-radius:50%; overflow:hidden;'>"
							+"<img src='../Public/header/defaultHeader.gif' alt='"+user.userName+"' id='userImage' width='35px' height='35px'>"
				   			+"</div>";
				   			$("#user_name").html(s);
					}
				}
			},
			error:function(){
				alert("检测用户登录异常！");
			}
		});
	}
		</script>
	</body>

</html>