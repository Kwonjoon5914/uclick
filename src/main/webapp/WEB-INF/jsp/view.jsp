<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>
body{
	margin: 0 auto;
}
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 75%;
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
::placeholder {
	color: black;
	font-size: 0.8em;
}
</style>

<html>
<script type="text/javascript">
$(function(){
	$(".onlyNumber").keyup(function(event){
	    if (!(event.keyCode >=37 && event.keyCode<=40)) {
	        var inputVal = $(this).val();
	        $(this).val(inputVal.replace(/[^0-9]/gi,''));
	    }
	});
});
	
	$(document).ready(function() {

		var mode;
		var formObj = $("form[role='form']");

		$(".modBtn").on("click", function() {
			formObj.attr("action", "editForm");
			formObj.submit();
		});

		$(".delBtn").on("click", function() {
			alert('삭제 되었습니다');

			formObj.attr("action", "Member_delete");
			formObj.attr("method", "post");
			//formObj.submit();
		});
		
		$(".phonedelBtn").on("click", function() {
			alert('핸드폰 삭제 되었습니다');

			formObj.attr("action", "Phone_delete");
			formObj.attr("method", "post");
			//formObj.submit();
		});
		
		$(".listBtn").on("click", function() {
			formObj.attr("action", "0");
		});
		
		$(".phonemodBtn").on("click", function() {

			formObj.attr("action", "updateForm");
			formObj.attr("method", "post");
			formObj.submit();
		});
		
		$(".phonenumberaddBtn").on("click", function() {
			
			var addphone = $.trim($('#addphone').val())
			
			if($.trim($('#addphone').val()).length!=11){
				alert(' - 를 제외한 핸드폰 번호 11자리를 정확히 입력해주세요 :(');

				return false;

			}else{

				formObj.attr("action", "Phone_add");
				formObj.attr("method", "post");				
			}
			
		});

	});
</script>

<head>
<title>회원 상세정보</title>
</head>
<body>
	<h2 align=center><B>회원 상세정보</B></h2>
	<br>
	<form role="form" method="post">
		<table>
			<tr>
				<th>이름</th>
				<td>${member.name}</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>${member.age}세</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.email}</td>
			</tr>
			<tr>
				<th>핸드폰 번호</th>
				<td>
				<select name="tongsinsa">
						<option value="SKT">SKT</option>
						<option value="KT">KT</option>
						<option value="LG">LG</option>
				</select>
				<input class="onlyNumber" type="text" name="addphone" id="addphone" minlength=11 maxlength=11 placeholder="-를 제외하고 숫자만 입력하세요.">
					<button type="submit" class="btn btn-primary phonenumberaddBtn">
						<i class="fa fa-trash"></i> 핸드폰 추가
					</button>
					<input type="hidden" name="id" value="${member.id}"></td>
				</td>
			</tr>

			<tr>
				</form>
				<c:forEach items="${member.phones}" var="phone">

					<form role="form" method="post">

						<th>핸드폰 번호</th>
						<td>${phone.tongsinsa} ${phone.number}
							<button type="submit" class="btn btn-danger phonedelBtn">
								<i class="fa fa-trash"></i> 핸드폰 삭제
							</button>
							<button type="submit" class="btn btn-warning phonemodBtn">
								<i class="fa fa-trash"></i> 핸드폰 수정
							</button></td>
							<input type="hidden" name="pid" value="${phone.id}" maxlength=11>
							<input type="hidden" name="id" value="${member.id}" maxlength=11>
			</tr>
			</form>
			</c:forEach>
			<tr>
				<th>회원 가입 날짜</th>
				<td><fmt:formatDate value="${member.regidate}" pattern="yyyy-MM-dd" /></td>
			</tr>
		</table>
		<br>
		<br>
		<div class="pull-rifht" align=center>
			<button type="submit" class="btn btn-primary listBtn">
				<i class="fa fa-list"></i> 목록
			</button>
			<button type="submit" class="btn btn-warning modBtn">
				<i class="fa fa-edit"></i> 회원 정보 수정
			</button>
			<button type="submit" class="btn btn-danger delBtn">
				<i class="fa fa-trash"></i> 회원 삭제
			</button>
		</div>
	</div>
</body>
</html>
