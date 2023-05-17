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

<!-- 	#. 비 로그인한 회원 -->
<%	User sessionUser = (User)session.getAttribute("sessionUser");	%>

<%//if ( user == null  ||  ! user.isActive() )  { %>
		<%--<jsp:forward page="/logon.do" />--%>
<%// } %>

<!-- 	#. 로그인한 회원이면 -->
<html>
	
	<head><title>Home Page</title></head>
	
	<body>
	
		[ * info ] :: ${requestScope.message }<br/><br/>
		
		<p>Simple Model2 Examples</p>
		<p> 환영합니다.  : <%= sessionUser.getUserId()%>님</p>

		<br/><br/>
		<a href = "/user/logout">logout</a>
	</body>
	
</html>