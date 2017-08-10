<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.4.2.min.js"></script>
<style type="text/css">
	.photo{
		height:60px;
		height:30px;
	}
	#frontPage,#nextPage{
		cursor:pointer;
	}
</style>
</head>
<body>
	<table id="table" border="1">
	</table>
	<a id="frontPage">上一页</a><a id="nextPage">下一页</a>共${sessionScope.totalPage}页
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : 'getMusic.action',
				type : 'get',
				data : {
					"action" : "ajaxDatas"
				},
				dataType : 'json',
				success : function(data) {
					var table_body = each(data);
					console.log(each(data));
					$("#table").html("");
					$("#table").append(table_body);
				},
				error : function() {

				}
			});
			
			function each(json) {
				var table_body;
				$.each(json, function(index, value) {
					table_body += "<tr><td>" + value.id + "</td>" + "<td>"
							+ value.mName + "</td>" + "<td>" + value.mSinger
							+ "</td>" + "<td>" + "<image src=musicPhoto/"+value.mPic+" class='photo'>"
							+ "</td>" + "<td>" + "<audio src=music/"+value.mPath+" controls='controls'>"
							+ "</td></tr>";
			});
				return table_body;
			};
			
			//下一页
			$("#nextPage").click(function(){
				$.ajax({
					url : 'getMusic.action',
					type : 'get',
					data : {
						"action" : "ajaxDatasNext"
					},
					dataType : 'json',
					success : function(data) {
						var table_body = each(data);
						console.log(each(data));
						$("#table").html("");
						$("#table").append(table_body);
					},
					error : function() {

					}
				});
			});
			
			//上一页
			$("#frontPage").click(function(){
				$.ajax({
					url : 'getMusic.action',
					type : 'get',
					data : {
						"action" : "ajaxDatasFront"
					},
					dataType : 'json',
					success : function(data) {
						var table_body = each(data);
						console.log(each(data));
						$("#table").html("");
						$("#table").append(table_body);
					},
					error : function() {

					}
				});
			});

		})
	</script>
</body>
</html>