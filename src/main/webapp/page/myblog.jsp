<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="dist/css/wangEditor.min.css">
<title>${blog.blogName }</title>
 <style type="text/css">
        #editor-trigger {
            
        }
        .container {
            width: 65%;
            margin: 50px auto;
            position: relative;
        }
        .rightSpan{
        	float: right;
        }
    </style>
</head>

<body>
<div class="container">
<div id="editor-trigger">
	${blog.blogContext }
	
	<span class="rightSpan">By ${blog.user.userName } in ${blog.date }</span>
</div>
</div>
<script type="text/javascript" src="dist/js/lib/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="dist/js/wangEditor.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	var objP=$("img");
    	for(var i=0;i<objP.length;i++){
    		var $obj=objP.eq(i);
    		if($obj.attr("src").substring(0,1)=="."){
    			var temp=$obj.attr("src").substring(3);
    			
    		}else if($obj.attr("src").substring(0,1)=="h"){
    			var num=$obj.att("src").indexof("http://localhost:8080/Upload/");
    			var temp=$obj.attr("src").substring(num);
    		}else{
    			
    			alert($obj.attr("src"));
    		}
    		$obj.attr("src",temp);
    	}
    });
    </script>
</body>
</html>