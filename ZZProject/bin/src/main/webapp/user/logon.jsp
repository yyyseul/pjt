<%@ page contentType="text/html;charset=euc-kr" %>

<%@ page import="spring.domain.User" %>

<!-- 
	1. 로그인 유무확인 :: Work Flow Control
		ㅇ 비로그인 인경우  : 로그인 화면 display 
	    ㅇ 로그인한 경우 : 이미 로그인 한 회원임을 display
	2. 로그인 확인은...
		ㅇ 로그인한 회원은 session ObjectScope에 User객체를 갖고, active 는 true
		ㅇ User객체의 유무 및 User의 active 값을 true / false 값으로 판단
====================================================
==> logon.jsp는 view  역할 및  Work Flow Control (방어적 코딩)를 수행
====================================================	
-->

<%
	String userId = "아이디입력";
	String password = "패스워등입력";
	User user = (User)session.getAttribute("user");
%>

<html>
	<head><title>Logon Page</title></head>
	<body>
	
	[ * info ] :: ${requestScope.message }<br/><br/>

	<% //if(user == null ||  user.isActive() != true ) {%>
		<form  method="post" action="/user/logonAction">

			아  이  디 : <input type="text" name="userId" value="<%= userId %>"><br/><br/>
			패스워드 : <input type="text" name="password" value="<%= password %>"><br/><br/>
			<input type="submit" name="submit" value="Enter"/>

		</form>
	 <%//}else{ %>
			<%-- <%= user.getUserId() 님은 이미 로그인 하셨습니다.--%>
	<% //} %>

	</body>
</html>