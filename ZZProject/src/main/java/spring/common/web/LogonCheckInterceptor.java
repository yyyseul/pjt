package spring.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.domain.User;

/*
 * FileName : LogonCheckInterceptor.java
 *  ㅇ Controller 호출전 interceptor 를 통해 선처리/후처리/완료처리를 수행
 *  	- preHandle() : Controller 호출전 선처리   
 * 			(true return ==> Controller 호출 / false return ==> Controller 미호출 ) 
 *  	- postHandle() : Controller 호출 후 후처리
 *    	- afterCompletion() : view 생성후 처리
 *    
 *    ==> 로그인한 회원이면 Controller 호출 : true return
 *    ==> 비 로그인한 회원이면 Controller 미 호출 : false return
 */
//====================== 추가된 부분. ===========================//
@Component
public class LogonCheckInterceptor extends HandlerInterceptorAdapter {

	///Field
	
	///Constructor
	public LogonCheckInterceptor(){
		System.out.println("==> LogonCheckInterceptor() default Constructor call.............");		
	}
	
	///Method
	public boolean preHandle(	HttpServletRequest request,
														HttpServletResponse response, 
														Object handler) throws Exception {
		
		System.out.println("\n[ LogonCheckInterceptor start........]");
		
		//==> 로그인 유무확인
		HttpSession session = request.getSession(true);
		User sessionUser =null;
		if(  ( sessionUser = (User)session.getAttribute("sessionUser")  ) == null ){
			sessionUser = new User();
		}		

		//==> 로그인한 회원이라면...
		if(   sessionUser.isActive()   )  {
			
			//==> 로그인 상태에서 로그인 시도 중.....
			String uri = request.getRequestURI();
			
			if( uri.indexOf("logonAction") != -1 || uri.indexOf("logon") != -1){
				request.getRequestDispatcher("/user/home.jsp").forward(request, response);
				System.out.println("[ 로그인 상태.. 로그인 후 불필요 한 요구.... ]");
				System.out.println("[ LogonCheckInterceptor end........]\n");
				return false;
			}
			
			System.out.println("[ 로그인 상태 ... ]");
			System.out.println("[ LogonCheckInterceptor end........]\n");
			return true;
		
		}else{ //==> 미 로그인한 화원이라면...

			//==> 로그인 시도 중.....
			String uri = request.getRequestURI();
			
			//////////////////////////////////////////////////////////////////////////////////////////////
			//===> 추가됨.
			//==> logonAction 이며 Get 인것 잡기
			/////////////////////////////////////////////////////////////////////////////////////////////
			if( uri.indexOf("logonAction") != -1 &&  request.getMethod().equals("GET")){
				System.out.println("[ logonAction GET 접근시도 상태 .... ]");
				request.getRequestDispatcher("/user/logon.jsp").forward(request, response);
				System.out.println("[ LogonCheckInterceptor end........]\n");
				return false;
			}
			
			if( uri.indexOf("logonAction") != -1 || uri.indexOf("logon") != -1){
				System.out.println("[ 로그 시도 상태 .... ]");
				System.out.println("[ LogonCheckInterceptor end........]\n");
				return true;
			}
			
			request.getRequestDispatcher("/user/logon.jsp").forward(request, response);
			System.out.println("[ 로그인 이전 ... ]");
			System.out.println("[ LogonCheckInterceptor end........]\n");
			return false;
			
		}
		
	}
}//end of class