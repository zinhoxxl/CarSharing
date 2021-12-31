<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<script>
function selectDomain(obj){
	document.newMember.mail2.value=obj.value;
	if(obj.value=="") document.newMember.mail2.focus();
}
</script> 
<script>
function checkForm(){
 if(document.newMember.password.value!=document.newMember.password_confirm.value){
	 alert("비밀번호와 비밀번호확인 값이 서로 다릅니다!");
	 document.newMember.password.value="";
	 document.newMember.password_confirm.value="";
	 document.newMember.password.focus();
	 return false;
 }	
 
//validation 체크
	var regExpId = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var regExpName = /^[가-힣]*$/;
	var regExpPassword =/^[0-9]*$/;
	var regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
	var regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //akim
	//form명
	var form = document.newMember;
	
	var id = form.id.value;
	var name = form.name.value;
	var passwd = form.password.value;
	var phone = form.phone1.value +"-" + form.phone2.value +"-" + form.phone3.value;
	var email = form.mail1.value +"@"+form.mail2.value;
	
	if(!regExpId.test(id)){
		alert("아이디는 문자로 시작해주세요");
		form.id.focus();
		form.id.value="";
		return false;
	}
	
	if(!regExpName.test(name)){
		alert("이름은 한글만 입력해주세요!");
		form.name.focus();
		form.name.value='';
		return false;
	}
	
	if(!regExpPassword.test(passwd)){
		alert("비밀번호는 숫자만 입력해주세요");
		form.password.focus();
		form.password.value='';
		form.password_confirm.value='';
		return false;
	}
	
	if(!regExpPhone.test(phone)){
		alert("연락처 입력을 확인 해주세요");
		form.phone2.focus();
		form.phone2.value='';
		form.phone3.value='';
		return false;	
	}
	if(!regExpEmail.test(email)){
		alert("이메일 입력을 확인 해주세요");
	    form.email1.focus();
		form.email1.value='';
		form.email2.value='';
		return false;	
	}
	return true;
}
</script>
<script>
function idChk(){
 var id=document.newMember.id.value;
 if(id.length==0){
	 alert("아이디를 입력하세요");
	 document.newMember.id.focus();
	 return;
 }else{
  /* 팝업창 열기 window.open(페이지); <-현재페이지는 opener임. */
  window.open('./view/member/idCheck.jsp?memberId='+id);
 }
}
</script>

<script>
/* 글로벌 변수 */
var isConfirm=false;

function confirm(){
	var cert1 = document.getElementById("cert").value;
	var cert2= document.getElementById("cert_confirm").value;
	if(cert1.length==0 || cert2.length==0){
		alert("인증확인요망");
	}else if(cert1!=cert2){
		alert("인증확인요망");
	}else{
		alert("인증이 완료되었습니다.");
		isConfirm=true;
	}
}
</script>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  
<%-- style --%>  
<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gothic+A1&family=Nanum+Pen+Script&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Gothic+A1&family=Nanum+Pen+Script&family=Public+Sans:ital,wght@1,900&display=swap');
    div.jumbotron {
        font-family: 'Do Hyeon', sans-serif;
    }
    h5 {
        font-family: 'Do Hyeon', sans-serif;
    }
</style>


<title>회원 가입</title>
</head>
<body>
 <jsp:include page="../main/menu.jsp"/>
  <div class="jumbotron" style="background-color: #e6ffff; padding-left: 240px;">
      <div class="container">
      	<h1 class="display-3">회원가입</h1><h4>CarSharing에 오신걸 환영해요</h4>
      </div>
  </div>
  
  <div class="container">
     <h5 style="padding-left: 100px;">※회원정보를 기입해주세요</h5>
  </div>
  <br>
  <br>
  <div class="container">
    <form name="newMember" class="form-horizontal" action="./MemberRegisterProcess.car" 
                method="post" onsubmit="return checkForm()"> 
        <div class="form-group row">
              <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:20px;">아이디</h5>
              <div class="col-sm-3">
                   <input name="id" type="text" class="form-control" placeholder="id" required>
                    <input type="button" value="아이디 중복검사"  class="btn btn-info" onclick="idChk()">
              </div>
        </div>
        <hr class="container">
        <div class="form-group row">
              <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">비밀번호</h5>
              <div class="col-sm-3">
                   <input name="password" type="password" class="form-control" placeholder="password" required>
              </div>
        </div>
        <hr class="container">
        <div class="form-group row">
              <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">비밀번호확인</h5>
              <div class="col-sm-3">
                   <input name="password_confirm" type="password" class="form-control" placeholder="password" required>
              </div>
        </div>
        <hr class="container">
        <div class="form-group row">
              <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">성명</h5>
              <div class="col-sm-3">
                   <input name="name" type="text" class="form-control" placeholder="name" required>
              </div>
        </div>
        
        <hr class="container">
        <div class="form-group row">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">성별</h5>
              <div class="form-check form-check-inline" style="margin-left: 20px; font-family: 'Do Hyeon', sans-serif;">
                <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="남" checked="checked">
                <label class="form-check-label" for="inlineRadio1">남자</label>
              </div>
              <div class="form-check form-check-inline" style="font-family: 'Do Hyeon', sans-serif;">
              <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="여">
              <label class="form-check-label" for="inlineRadio2">여자</label>
              </div>
        </div>
        <hr class="container">
          <div class="form-group row">
         <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:14px;">전화번호</h5>
         <div class="col-sm-5" style="margin-top:10px;">
               <select name="phone1" required>
		              <option value="010" selected>010</option>
		              <option value="011">011</option>
		              <option value="016">016</option>
		              <option value="017">017</option>
		              <option value="019">019</option>
		           </select>
				- <input maxlength="4" size="4" name="phone2" required> -
				<input maxlength="4" size="4" name="phone3" required>
         </div>
       </div>
       <hr class="container">
        <div class="form-group row">
              <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:5px;">생일</h5>
              <div class="col-sm-4">
                   <input type="text" name="birthyy" maxlength="4" placeholder="년(4자)" size="6" required>
                   <select name="birthmm" required>
                   	<option value="">월</option>
                   	<option value="01">1</option>
                   	<option value="02">2</option>
                   	<option value="03">3</option>
                   	<option value="04">4</option>
                   	<option value="05">5</option>
                   	<option value="06">6</option>
                   	<option value="07">7</option>
                   	<option value="08">8</option>
                   	<option value="09">9</option>
                   	<option value="10">10</option>
                   	<option value="11">11</option>
                   	<option value="12">12</option>
                   </select>
                   <input type="text" name="birthdd" maxlength="2" placeholder="일" size="4" required>
              </div>
        </div>
        <hr class="container">
       <div class="form-group row">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:5px;">이메일</h5>
             <div class="col-sm-10" style="margin-top:3px;">
                <input type="text" name="mail1" maxlength="50" required>@
                <input type="text" name="mail2" maxlength="50" required>
                 <select name="mail2_select" onchange="selectDomain(this)" 
                         style="font-family: 'Do Hyeon', sans-serif;">
                    <option disabled="disabled" selected="selected">선택</option>
                    <option>naver.com</option>
                    <option>daum.net</option>
                    <option>gmail.com</option>
                    <option>nate.com</option>
                    <option value="">직접입력</option>
                </select>
             </div>
       </div>
       
        
  <div class="form-group row">
  <hr class="container">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">우편번호</h5>
             <div class="col-sm-3">
                 <input name="zipcode" id="zipcode" type="text" class="form-control" placeholder="우편번호" required>
                 <input type="button" onclick="Postcode()" value="우편번호 찾기"><br>
             </div>
         </div>
         <hr class="container">
          <div class="form-group row">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">도로명주소</h5>
             <div class="col-sm-3">
                 <input name="roadAddress" id="roadAddress"  type="text" class="form-control" placeholder="도로명주소" required>
             </div>
         </div>
         <hr class="container">
         <div class="form-group row">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">지번주소</h5>
             <div class="col-sm-3">
                 <input name="jibunAddress" id="jibunAddress"  type="text" class="form-control" placeholder="지번주소" required>
             </div>
         </div>
         <hr class="container">
         <span id="guide" style="color:#999;display:none"></span>
         <div class="form-group row">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">상세주소</h5>
             <div class="col-sm-3">
                 <input name="detailAddress"  id="detailAddress" type="text" class="form-control" placeholder="상세주소" required>
             </div>
         </div>
         <hr class="container">
         <div class="form-group row">
             <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:8px;">추가주소</h5>
             <div class="col-sm-3">
                 <input name="extraAddress"id="extraAddress" type="text" class="form-control" placeholder="참고항목" required>
             </div>
         </div>
       

       <div class="form-gorup row">
          <div class="col-sm-offset-2 col-sm-10">
               <input type="reset"  class="btn btn-secondary" value="취소" onclick="reset()">
               <input type="submit" class="btn btn-success" value="등록">
          </div>
       </div>
    </form>
  
    
  </div>
  <hr class="container">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function Postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
<jsp:include page="../main/footer.jsp"/> 
</body>
</html>