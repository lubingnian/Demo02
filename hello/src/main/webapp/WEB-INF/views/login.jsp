<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	
%>
<meta charset="UTF-8">
<title>登录</title>
</head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<body>
	<div>
		<p>
			账号：<input type="text" id="name" />
		</p>
		<p>
			密码：<input type="text" id="age" />
		</p>
		<p>
			<input type="button" onclick="login()" value="登录" />
		</p>
	</div>
</body>
<script type="text/javascript">
	function login() {
		$.ajax({
			type : "post",
			url : 'http://localhost:8080/user/loginCheck',
			data : {
				name : $("#name").val(),
				age : $("#age").val()
			},
			dataType : "text",
			success : function(data) {
				$.ajax({
					type : "post",
					url : 'http://localhost:8080/student/list',
					/* data : {
						id : "1",
						name : "张三",
						age : "22",
						sex : "男",
						city_id : "1"
					}, */
					headers : {
						'authorization' : data
					},
					dataType : "text",
					success : function(data) {
						alert(1);
					}
				});
			}
		})
	}
</script>
</html>