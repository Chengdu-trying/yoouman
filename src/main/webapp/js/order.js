$(document).ready(function(){
	loadHot();
	
});




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