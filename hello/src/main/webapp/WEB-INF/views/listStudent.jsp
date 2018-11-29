<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>age</th>
            <th>sex</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${students}" var="s" varStatus="st">
         <tr>
             <td><span>${s.id}</span></td>
             <td><span>${s.name}</span><input id="name${s.id}" style="display: none;width: 50px;" type="text" value="${s.name}" /></td>
             <td><span>${s.age}</span><input id="age${s.id}" style="display: none;width: 50px;" type="text" value="${s.age}" /></td>
             <td><span>${s.sex}</span><input id="sex${s.id}" style="display: none;width: 50px;" type="text" value="${s.sex}" /></td>
             <td><input id="update${s.id}" style="display: none;" onclick="update(${s.id})" type="button" value="提交" /><input id="alterSty${s.id}"  onclick="alterSty(${s.id})"  type="button" value="修改" /><input id="delete${s.id}" onclick="del(${s.id})" type="button" value="删除" /></td>
         </tr>
        </c:forEach>
    </table>
    <div>
    	name：<input id="name" style="width: 50px;" type="text" />age：<input id="age" style="width: 50px;" type="text" />sex：<input id="sex" style="width: 50px;" type="text" /><input id="add" onclick="add()" type="button" value="新增" />
    </div>
</body>
<script type="text/javascript">
function alterSty(id){
	var name = document.getElementById('name' + id);
	var age = document.getElementById('age' + id);
	var sex = document.getElementById('sex' + id);
	var alterSty = document.getElementById('alterSty' + id);
	var deleteB = document.getElementById('delete' + id);
	var update = document.getElementById('update' + id);
	name.style.display="inline";
	age.style.display="inline";
	sex.style.display="inline";
	alterSty.style.display="none";
	deleteB.style.display="none";
	update.style.display="inline";
}
	function update(id){
		/* $.ajax({
			url: 'localhost:8080/updata',
			type:'post',
			headers: {
				authorization : "5465454sadsda"
			}
		}) */
		var name = document.getElementById('name' + id);
		var age = document.getElementById('age' + id);
		var sex = document.getElementById('sex' + id);
		window.location.href="update?id=" + id + "&name=" + name.value + "&age=" + age.value +"&sex=" + sex.value;
	}
	function add(){
		var name = document.getElementById('name');
		var age = document.getElementById('age');
		var sex = document.getElementById('sex');
		window.location.href="add?name=" + name.value + "&age=" + age.value +"&sex=" + sex.value;
	}
	function del(id){
		window.location.href="delete?id=" + id;
	}
</script>
</html>