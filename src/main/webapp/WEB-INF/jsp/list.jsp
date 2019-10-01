<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>

<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<html>
<style>
body{
	margin: 0 auto;
}
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 60%;
	align: center;
	margin: 0 auto;
	}

td {
    border: 1px solid #dddddd;
	text-align: center;
	vertical-align: middle;
	padding : 8px;
	}
	
th {
    padding: 10px;
    vertical-align: top;
    font-weight: bold;
    background-color: #EDDC90;
    text-align: center;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    }
</style>

<script type="text/javascript">

	$(document).ready(function() {

		var formObj = $("form[role='form']");
		console.log(formObj);

		$(".insertBtn").on("click", function() {
			self.location = "insertForm"
		});
	});

	/* document.getElementByName("keyfield").value
	document.getElementByName("keyword").text */
/*
	var formObj = $("form[role='form']");        //폼 가저오기
	 
	$(document).ready(function(){
	    $(".deleteBtn").on("click", function(){
	        formObj.attr("action", "/board/delete");    ///board/delete/로
	        formObj.submit();                            //이동 (전송)
	    });

	    $(".modifyBtn").on("click", function(){
	        formObj.attr("action", "/board/modify");    ///board/modify/로
	        formObj.attr("method", "get");            //get방식으로 바꿔서
	        formObj.submit();                            //이동 (전송)
	    });
	});
*/
</script>
<head>
	<title>리스트</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<form action="search" method="post">
					<select name="keyfield">
						<option value="searchname">이름검색</option>
						<option value="searchphone">번호검색</option>
						<option value="searchtongsinsa">통신사검색</option>
					</select>
					<input type="text" name="keyword" placeholder="검색어를 입력해주세요" minlength="1" maxlength="12" required/>
					<input type="submit" value="검색" />
				</form>
			</td>
		</tr>	
	</table>
	<h2 align=center><B>회원 목록</B></h2>
	<table>
		<tr>
			<th>회원 이름</th>
			<th>회원 나이</th>
			<th>회원 이메일</th>
			<th>회원 등록일</th>
		</tr>
		<c:forEach items="${memberlist.content}" var="member">
		<tr>
			 <td><a href="view?id=${member.id}">${member.name}</a></td>
			 <td>${member.age}세</td>
			 <td>${member.email}</td>
			 <td><fmt:formatDate value="${member.regidate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<div align=center>
	<c:forEach begin="${startRange}" end="${endRange}" var="q">
		<button type="button" class="btn22" onClick="location.href='${q}'">${q+1}</button>
	</c:forEach>
	</div>
	<br>
	<div class="pull-rifht" align=center>
		<button type="submit" class="btn btn-primary insertBtn">
			<i class="fa fa-list"></i>회원 생성
		</button>
	</div>
</body>
</html>