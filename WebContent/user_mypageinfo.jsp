<%@page import="com.test.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

   <!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
    
</head>
<body>
    <%@ include file="../include/header.jsp" %>
    
<section>
		<form action="modifyForm.user" method="post" name="regForm">
        <div class="container">
            <div class="row join-wrap">
                <!--join-form을 적용한다 float해제 margin:0 auto-->
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <p>*표시는 필수 입력 표시입니다</p>
                    <table class="table">
                        <tbody class="m-control">
                            <tr>
                                <td class="m-title">*ID</td>
                                <td><input class="form-control input-sm" value="${vo.id} " name="id" readonly></td>
                            </tr>
                            <tr>
                                <td class="m-title">*이름</td>
                                <td><input class="form-control input-sm" value="${vo.name} " name="name"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호</td>
                                <td><input type="password" class="form-control input-sm" id="pw" name="pw"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호확인</td>
                                <td><input type="password" class="form-control input-sm"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*E-mail</td>
                                <td>
                                    <input class="form-control input-sm" value="${vo.email1}" name="email1">@
                                    <select class="form-control input-sm sel" name="email2">
                                        <option value="naver.com" ${vo.email2 == 'naver.com' ? 'selected' : '' }>naver.com</option>
                                        <option value="gmail.com" ${vo.email2 == 'gmail.com' ? 'selected' : '' }>gmail.com</option>
                                        <option value="daum.net" ${vo.email2 == 'daum.net' ? 'selected' : '' }>daum.net</option>
                                    </select>
                                    <button class="btn btn-info">중복확인</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*휴대폰</td>
                                <td>
                                    <input class="form-control input-sm sel" value="${vo.phone1}" name="phone1"> -
                                    <input class="form-control input-sm sel" value="${vo.phone2}" name="phone2"> -
                                    <input class="form-control input-sm sel" value="${vo.phone3}" name="phone3">
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*주소</td>
                                <td><input class="form-control input-sm add" value="${vo.address}" name="address1"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*상세주소</td>
                                <td><input class="form-control input-sm add" value="${vo.address2}" name="address2"></td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <div class="titlefoot">
                        <button class="btn" onclick="check()">수정</button>
                        <button class="btn">목록</button>
                    </div>
                    
                </div>


            </div>

        </div>
		</form>
    </section>
    
    <script>
    	function check(){
    		if(document.regForm.pw.value.length < 4){
    			alert("비밀번호는 4글자 이상입니다");
    			location.href="modify.user";
    		} else if(confirm("수정하시겠습니까?")){

        		document.regForm.submit();
    		}
    	}
    </script>
    
    
    <%@ include file="../include/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>