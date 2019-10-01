<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>회원정보수정</title>
</head>
<body>
	<h2 align=center>회원정보수정</h2>
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
<script>
$(function(){

    $('.onlyAlphabetAndNumber').keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val($(this).val().replace(/[^_a-z0-9@.]/gi,'')); //_(underscore), 영어, 숫자,@,.만 가능
        }
    });
     
    $(".onlyHangul").keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
        }
    });
 
    $(".onlyNumber").keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^0-9]/gi,''));
        }
    });
});
</script>
<SCRIPT>
	$(document).ready(function() {
		
		var formObj = $("form[role='form']");
		console.log(formObj);
		$(".modBtn").on("click", function() {
			
			alert('수정 되었습니다');
			
			formObj.attr("action", "Member_updating");
			formObj.submit();
		});

		$(".delBtn").on("click", function() {
			alert('삭제 되었습니다');
			
			formObj.attr("action", "Member_delete");
			formObj.attr("method", "post");
			formObj.submit();
		});

		$(".listBtn").on("click", function() {
			self.location = "0"
			
		});
	});
</SCRIPT>
	<form role="form" method="post">
	<table>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>이메일</th>
		</tr>
			<tbody>
				<tr>
					<td><input class="form-control onlyHangul" data-rule-required="true" type=text
					 id=name name=name maxlength=10 value='${member.name}' required></td>
					<td><input class="form-control onlyNumber" data-rule-required="true"
					 type=text id=age name=age maxlength=3 value='${member.age}' required></td>
					<td><input class="form-control onlyAlphabetAndNumber" data-rule-required="true"
					 type=text id=email name=email maxlength=20 value='${member.email}' required></td>
				</tr>
			</tbody>
	</table>
		<input type="hidden" name=phoneid value="${phone.id}">
		<input type="hidden" name=id value="${member.id}">
		<input type="hidden" name=email value="${member.email}">
	</form>
	<div class="pull-rifht" align=center>
		<button type="submit" class="btn btn-primary listBtn">
			<i class="fa fa-list"></i> 목록
		</button>
		<button type="submit" class="btn btn-warning modBtn">
			<i class="fa fa-edit"></i> 수정
		</button>
		<button type="submit" class="btn btn-danger delBtn">
			<i class="fa fa-trash"></i> 삭제
		</button>
	</div>

</body>
</html>