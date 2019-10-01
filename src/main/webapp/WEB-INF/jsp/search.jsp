<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 70%;
	align: center;
	margin: auto;
	}
td {
    border: 1px solid #dddddd;
	text-align: center;
	vertical-align: middle;
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
<html>
<script type="text/javascript">

$(document).ready(function () {

    var formObj = $("form[role='form']");
    console.log(formObj);
    
    $(".delBtn").on("click", function () {
    	alert('삭제 되었습니다');

       formObj.attr("action", "Member_delete");
       formObj.attr("method", "post");
       formObj.submit();
    });
   
    $(".listBtn").on("click", function () {
       self.location = "0"
    });
});
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
<title>Member</title>
</head>
<body>
	<h2 align=center>검색 리스트</h2>

	<table>
		<tr>
			<th>회원 이름</th>
			<th>회원 나이</th>
			<th>회원 이메일</th>
			<th>회원 등록일</th>
		</tr>
		<tbody>
		<c:forEach items="${members}" var="members">
			<tr>
				<td><a href='view?id=${members.id}'>${members.name}</a></td>
				<td>${members.age}세</td>
				 <td>${members.email}</td>
				 <td><fmt:formatDate value="${members.regidate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<div class="pull-rifht">
				<form role="form" method="post">
					<input type="hidden" name="id" value="${members.id}"> 
					<input type="hidden" name="pid" value="${phone.id}">
				</form>
			</div>
		</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<div class="pull-rifht" align=center>
		<button type="submit" class="btn btn-primary listBtn">
			<i class="fa fa-list"></i> 목록
		</button>
		<button type="submit" class="btn btn-danger delBtn">
			<i class="fa fa-trash"></i> 전체삭제
		</button>
	</div>
</body>
</html>
