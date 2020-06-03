<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<% String id = (String)request.getSession().getAttribute("id");%>    

<head>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/Test/css/bootstrap.css">
        <link rel="stylesheet" href="/Testcss/custom.css">

        <title>BBS Test</title>

</head>

	<nav class="navbar navbar-default" id="nav">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="##">MIN and PARK</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=request.getContextPath()%>/index.board" style="margin-right: 10px;">메인</a></li>
                <li><a href="<%=request.getContextPath()%>/bbs.board">게시판</a></li>
            </ul>
            
            <c:choose>
            	<c:when test="${empty sessionScope.id }">
            	<ul class="nav navbar-nav navbar-right">
               		 <li class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
                    	<ul class="dropdown-menu">
                        	<li><a href="<%=request.getContextPath()%>/login.user">로그인</a></li>
                        	<li><a href="<%=request.getContextPath()%>/join.user">회원가입</a></li>
                    	</ul>
                	</li>
            	</ul>
            	</c:when>

            	<c:when test="${not empty sessionScope.id }">
            	<ul class="nav navbar-nav navbar-right">
               		 <li class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
                    	<ul class="dropdown-menu">
                        	<li><a href="<%=request.getContextPath()%>/logout.user">로그아웃</a></li>
                       	    <li><a href="<%=request.getContextPath()%>/mypage.user">Mypage</a></li>
                    	</ul>
                	</li>
            	</ul>
            	</c:when>
            </c:choose>
       
        </div>
    </nav>  


