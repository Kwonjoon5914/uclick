<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="//code.jquery.com/jquery.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
            <div class="modal fade" id="defaultModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">알림</h4>
                        </div>
                        <div class="modal-body">
                            <p class="modal-contents"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            
	<form id="writeForm" name="writeForm" role="form" method="post" class="form-horizontal">
		<h2 align=center>회원 정보 입력</h2>
		<div class="container">
				<div class="form-group" id="divName">
					<label for="input" class="col-lg-2 control-label">이름</label>
	                <div class="col-lg-10">
					<input class="form-control onlyHangul" type="text" id="name" name="name" placeholder="한글만 입력가능합니다"
					 data-rule-required="true" maxlength="10"/>
				 	</div>
				</div>
				<div class="form-group" id="divTongsinsa">
					<label class="col-lg-2 control-label">통신사</label>
		            <div class="col-lg-10">
					<select name="tongsinsa" style="width:100px;height:40px;font-size:20px;">
						<option value="SKT">SKT</option>
						<option value="KT">KT</option>
						<option value="LG">LG</option>
					</select>
					</div>
				</div> 
				<div class="form-group" id="divNumber">
					<label for="number" class="col-lg-2 control-label">번호</label>
		            <div class="col-lg-10">
					<input class="form-control onlyNumber" type="text" id="number" name="number" placeholder="11자리 숫자만 입력해주세요"
					 data-rule-required="true" maxlength="11"/>
					 </div>
				</div>
				<div class="form-group" id="divAge">
					<label for="input" class="col-lg-2 control-label">나이</label>
		            <div class="col-lg-10">
					<input class="form-control onlyNumber" type="text" id="age" name="age" placeholder="숫자만 입력해주세요(최대 3자리)"
					 data-rule-required="true" maxlength="3"/>
					 </div>
				</div>
				<div class="form-group" id="divEmail">
					<label for="input" class="col-lg-2 control-label">이메일</label>
		            <div class="col-lg-10">
					<input class="form-control onlyAlphabetAndNumber" type="text" id="email" name="email" placeholder="e-mail주소를 입력해주세요"
					 data-rule-required="true" maxlength="30"/>
					 </div>
				 </div>
				<div class="form-group">
					<label for="input" class="col-lg-2 control-label">등록일</label>
			        <div class="col-lg-10">
					<input type="hidden" name="date" value="${members.date}">자동 생성
					</div>
				</div>
		</div>
		<div class="pull-rifht" align=center>

			<input type="submit" class="btn btn-warning" onclick='add()' value="회원 등록">
			
			<!--<button type="submit" class="btn btn-warning" onClick="javascript:add();">회원 등록</button>  -->

			<button type="button" class="btn btn-primary" onClick="location.href = 0">목록</button>

		</div>
	</form>
<script>
	$(function(){
    //모달을 전역변수로 선언
    var modalContents = $(".modal-contents");
    var modal = $("#defaultModal");

    $('.onlyAlphabetAndNumber').keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val($(this).val().replace(/[^_a-z0-9@.]/gi,'')); //_(underscore), 영어, 숫자만 가능
        }
    });

    $(".onlyHangul").keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[a-z0-9]/gi,''));//한글만가능
        }
    });
 
    $(".onlyNumber").keyup(function(event){
        if (!(event.keyCode >=37 && event.keyCode<=40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^0-9]/gi,''));//번호만가능
        }
    });
    //------- 검사하여 상태를 class에 적용
     
    $('#name').keyup(function(event){
         
        var divName = $('#divName');
         
        if($.trim($('#name').val())==""){
            divName.removeClass("has-success");
            divName.addClass("has-error");
        }else{
            divName.removeClass("has-error");
            divName.addClass("has-success");
        }
    });   

    $('#number').keyup(function(event){
        
        var divNumber = $('#divNumber');
         
        if($.trim($('#number').val())=="" || ($.trim($('#number').val())).length!=11){
        	divNumber.removeClass("has-success");
        	divNumber.addClass("has-error");
        }else{
        	divNumber.removeClass("has-error");
        	divNumber.addClass("has-success");
        }
    });
    
    $('#age').keyup(function(event){
        
        var divAge = $('#divAge');
         
        if($.trim($('#age').val())==""){
        	divAge.removeClass("has-success");
        	divAge.addClass("has-error");
        }else{
        	divAge.removeClass("has-error");
        	divAge.addClass("has-success");
        }
    });
    
    $('#email').keyup(function(event){
        
        var divEmail = $('#divEmail');
         
        if($.trim($('#email').val())==""){
        	divEmail.removeClass("has-success");
        	divEmail.addClass("has-error");
        }else{
        	divEmail.removeClass("has-error");
        	divEmail.addClass("has-success");
        }
    });

    //------- validation 검사
    $('form').on('submit', function(event) {
         
        var divName = $('#divName');
        var divNumber = $('#divNumber');
        var divAge = $('#divAge');
        var divEmail = $('#divEmail');
        //이름 검사
        if($('#name').val()==""){
            modalContents.text("이름을 입력하여 주시기 바랍니다.");
            modal.modal('show');
             
            divName.removeClass("has-success");
            divName.addClass("has-error");
            $('#name').focus();
            return false;
        }else{
            divName.removeClass("has-error");
            divName.addClass("has-success");
        }
        //전화번호 검사
        if($('#number').val()=="" || ($.trim($('#number').val())).length!=11){
        	modalContents.text("전화 번호 11자리를 정확히 입력하여 주시기 바랍니다.");
	        modal.modal('show');
	         
	        divNumber.removeClass("has-success");
	        divNumber.addClass("has-error");
	        $('#number').focus();
	        return false;
	    }else{
	    	divNumber.removeClass("has-error");
	    	divNumber.addClass("has-success");
	    }
        //나이
        if($('#age').val()==""){
            modalContents.text("나이를 입력하여 주시기 바랍니다.");
            modal.modal('show');
             
            divAge.removeClass("has-success");
            divAge.addClass("has-error");
            $('#age').focus();
            return false;
        }else{
        	divAge.removeClass("has-error");
        	divAge.addClass("has-success");
        }
        //이메일
        if($('#email').val()==""){
            modalContents.text("이메일을 입력하여 주시기 바랍니다.");
            modal.modal('show');
             
            divEmail.removeClass("has-success");
            divEmail.addClass("has-error");
            $('#email').focus();
            return false;
        }else{
        	divEmail.removeClass("has-error");
        	divEmail.addClass("has-success");
        }
    	writeForm.action = "<c:url value='save'/>";
      	location.href = "0";

        
	});
    
});
</script>
<script>
function add(){
	  
  	//var form2 = document.getElementById("writeForm");
	;
}
</script>
</body>
</html>
