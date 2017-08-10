<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/sweetalert-dev.js"></script>
<script src="js/sweetalert.js"></script>
<script src="js/tuling.js"></script>
<script>
$(function(){
	$("#joke").click(function(){
		$.ajax({
			url:'http://www.tuling123.com/openapi/api',
			data:{'key':'c8701f2ee2634996ab5bdb004cb44538','info':'讲一个笑话'},
			success:function(data){
				alert(data);
			},
			error:function(){
				alert("error");
			}
		});
	})
})
</script>
</head>
<body>
<input type="button" id="joke" value="讲一个笑话">
</body>
</html>