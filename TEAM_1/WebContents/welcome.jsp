<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">

<style>
    div.jumbotron {
        font-family: 'Do Hyeon', sans-serif;
    }
    h3 {
        font-family: 'Do Hyeon', sans-serif;
    }
    
    /* 캐러셀(이미지슬라이드) 이미지 크기변경 */
    header.carousel .fill {

	width: 100%;

	height: 100%;

	background-position: center;

	background-size: auto 100%;

   }

</style>

<script> 
    $('.carousel').carousel({ interval: 2000 }) //기본 5초
</script>


<title>중앙렌트카</title> 
</head>
<body>
<jsp:include page="./view/main/menu.jsp"/>
<div class="jumbotron" style="background-color: #ffffff; ">
  <div class="container">
  	<h1 class="display-3">중앙렌트카</h1>
  </div>
</div>  
<div class="container">
  <div class="text-center">
     <h3>중앙렌트카 홈페이지에 방문해주셔서 감사드립니다.</h3>
    </div>
  <div class="text-center">
 	<br>
  </div>
  
  <!-- 메인 페이지 이미지 삽입 영역 -->
<!--Carousel Wrapper-->
<div id="carousel-example-2" class="carousel slide carousel-fade" data-ride="carousel">
  <!--Indicators-->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-2" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-2" data-slide-to="1"></li>
    <li data-target="#carousel-example-2" data-slide-to="2"></li>
  </ol>
  <!--/.Indicators-->
  <!--Slides-->
  <div class="carousel-inner" role="listbox">
    <div class="carousel-item active">
      <div class="view">
        <img class="d-block w-100" src="<c:url value="/resources/images/car01.jpg"/>"
          alt="First slide" >
        <div class="mask rgba-black-light"></div>
      </div>
      <div class="carousel-caption">
        <h3 class="h3-responsive">CarSharing을 방문해주셔서 감사합니다</h3>
        <p>여행에 함께 할 차를 구경해보세요</p>
      </div>
    </div>
    <div class="carousel-item">
      <!--Mask color-->
      <div class="view">
        <img class="d-block w-100" src="<c:url value="/resources/images/car02.jpg"/>"
          alt="First slide" >
        <div class="mask rgba-black-strong"></div>
      </div>
      <div class="carousel-caption">
        <h3 class="h3-responsive">편안한여행에 함께합니다</h3>
        <p>CarSharing과 함께 가족,친구,연인과 함께 좋은 추억을 만들어 주세요</p>
      </div>
    </div>
    <div class="carousel-item">
      <!--Mask color-->
      <div class="view">
        <img class="d-block w-100" src="<c:url value="/resources/images/car03.jpg"/>"
          alt="First slide" >
        <div class="mask rgba-black-slight"></div>
      </div>
      <div class="carousel-caption">
        <h3 class="h3-responsive">어디든지</h3>
        <p>CarSharing은 어디든지 여러분과 함께합니다</p>
      </div>
    </div>
  </div>
  <!--/.Slides-->
  <!--Controls-->
  <a class="carousel-control-prev" href="#carousel-example-2" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carousel-example-2" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
  <!--/.Controls-->
</div>
<!--/.Carousel Wrapper-->
<hr>
</div>
<jsp:include page="./view/main/footer.jsp"/>

</body>
</html>
<!-- web.xml에 welcome-file-list에 페이지를 등록하면 
    welcome페이지로 사용가능 
http://localhost:8080/WebMarket/welcome.jsp
-->