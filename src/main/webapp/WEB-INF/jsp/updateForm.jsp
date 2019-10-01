<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<html>
<head>
<title>핸드폰번호 수정</title>
</head>
<body>
	<h2 align=center>핸드폰번호 수정</h2>
<style>

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 90%;
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
	<SCRIPT LANGUAGE="JavaScript">

		$(document).ready(function() {

			var formObj = $("form[role='form']");

			$(".modBtn").on("click", function() {
				
				var number = $.trim($('#number').val())
				
				if($.trim($('#number').val()).length!=11){
					alert(' - 를 제외한 핸드폰 번호 11자리를 정확히 입력해주세요 :(');
					
					return false;
					
				}else{				

					formObj.attr("action", "Phone_updating");
					formObj.attr("method", "post");
					formObj.submit();
				}

			});

			$(".listBtn").on("click", function() {
				self.location = "0"
			});
		});
	</SCRIPT>
	<form role="form" method="post">
	<table>
		<tr>
			<th>통신사</th>
			<th>번호</th>
		</tr>
			<tbody>
				<tr>
					<td><input type=text name="tongsinsa" id="tongsinsa" minlength=2 maxlength=3 value=${phone.tongsinsa}></td>
					<td><input type=text name="number" id="number" minlength=11 maxlength=11 value=${phone.number}></td>
				</tr>
			</tbody>
	</table>
	<input type="hidden" name=phoneid value="${phone.id}">
	<input type="hidden" name=tongsinsa value="${phone.tongsinsa}">
	<input type="hidden" name=id value="${memberid}">
	</form>


	<div class="pull-rifht" align=center>
		<button type="submit" class="btn btn-primary listBtn">
			<i class="fa fa-list"></i> 목록
		</button>
		<button type="submit" class="btn btn-warning modBtn">
			<i class="fa fa-edit"></i> 수정
		</button>
	</div>
</body>
</html>