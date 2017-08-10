<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.4.2.min.js"></script>
</head>
<body>

</body>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:'getRssNews.action',
			type:'post',
			dataType:'xml',	
			data:{"url":'http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss'},
			success:function(data){
				alert(data);
			},
			error:function(){
				alert("error");
			}
		});
	});
</script>
</html>