<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
	<script>
function add(){
	alert("이미 등록된 번호입니다! 확인해주세요 :(");
	location.href = "javascript:history.back();";
}
	</script>
</head>
<body onload="add()">
</body>
</html>