<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
    
    <style type="text/css">

    </style>
    
    
</head>
<body>
    <%@ include file="../include/header.jsp" %>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER                   
                    </div>
                    <div>
                        <p>${sessionScope.id }</p>
                    </div>
                    <div>
                        <button type="button" class="btn btn-primary" onclick="location.href='modify.user'">회원정보변경</button>
                        <button type="button" class="btn btn-primary" id="delCheck">회원 탈퇴</button>
                        
                    </div>
                    <div class="delete-hidden">
                        <form action="delete.user" method="post" name="delForm">
                        <input type="password" class="form-control" name = "pw" placeholder="비밀번호를 입력하세요">
                        <button type="button" class="btn btn-primary" onclick="del()">확인</button>
                        </form>
                    </div>
                    
                    <br>
                    <div>
                        <p>${sessionScope.id }님의 작성 게시물</p>
                        <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="text-align: center;">번호</th>
                            <th style="text-align: center;">제목</th>
                            <th style="text-align: center;">작성자</th>
                            <th style="text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    <c:forEach var="vo" items="${list }">
                    <tbody>
                    	<tr>
							<td>${vo.bno }</td>
							<td>${vo.title }</td>
							<td>
								<a href="content.board?bno=${vo.bno }">${vo.title }</a>
							</td>
							<td>
								${vo.regdate }	
							 </td>
						</tr>
                        
                    </tbody>
                    </c:forEach>
                </table>
                    </div>
                    
                    
                </div>


            </div>

        </div>

    </section>
    
    
    <%@ include file="../include/footer.jsp" %>
    
    
    <script>
        function del(){
        	document.delForm.submit();
        }
    </script>
    
    <script>
        //탈퇴버튼 디스플레이 처리
        var delCheck = document.getElementById("delCheck");
        delCheck.onclick = function() {
            var del  = document.querySelector(".delete-hidden");
            if(del.style.display == "none" || del.style.display == "") {
                del.style.display = "inline";
            } else {
                del.style.display = "none";
            }
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>