<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSONP‹•</title>
<script type="text/javascript" src="http://manage.mall.com/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		console.log("jsonp")
		$.ajax({
			url:"http://manage.mall.com/testJsonp",
			type:"get",
			dataType:"jsonp",
			jsonp: "callback",    
			jsonpCallback: "hello",  
			success:function (data){   
				alert(data.id);
				alert(data.name);
				
			}	
		});	
	})
</script>
</head>
<body>
	<h1>aaaaaaaaaaaaa</h1>
</body>
</html>