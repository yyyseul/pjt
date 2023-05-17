package spring.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.domain.User;
import spring.service.user.UserService;

/*
 *  FileName : UserController.java
 *  - Controller Coding Policy 
 *  	: return type : String 적용 
 *  	: Method parameter  
 *  		- Client Data : @ModelAttribute / @RequestParam 적절이 사용
 *  	    - 필요시 : Servlet API 사용
 *  	: 화면에 전달할 Model 은 org.springframework.ui.Model 사용
 *      
 *  - 핸들러 매핑전략 : Controller 까지 Mapping를 하고,  Method 이름이 URI와 적용시킴
 *      예) http://ip:port/Spring13/app/user/logonView
 *           ㅇ Spring13  webApp 에...
 *           ㅇ /app/         로 시작하는 ==> web.xml 에 등록됨
 *           ㅇ /user/        로 @RequestMapping("/user/*") 로 Mapping 한 Controller
 *           ㅇ /logonView    logonView() Method 호출                              
 */                                       
@Controller
@RequestMapping("/user")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	///Constructor
	public UserController(){
		System.out.println("==> UserController default Constructor call.............");
	}

	
	//==> 단순 navigation
	@RequestMapping("/logon")
	public String logon(Model model){
		
		System.out.println("[ logon() start........]");//<== 디버깅용
		
		//==> Client 에 전달할  Message 생성 
		String message = "[ logon() ] 아이디,패스워드 3자이상 입력하세요.";
		
		//==> Model (data) 
		model.addAttribute("message", message);
		
		System.out.println("[ logon()  end........]\n");
		
		
		//============= Transaction Test 위해 추가함====================//
//		try {
//			userService.addUser(new User("user04","주몽","user04",null,0));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//==========================================================//
		
		return "/user/logon.jsp";
	}

	
	//==> 단순 navigation
	@RequestMapping("/home")
	public String home( Model model ){
		
		System.out.println("[ home() start........]");//<== 디버깅용
		
		String message = "[ home()  ] WELCOME";
		
		//==> Model (data) 
		model.addAttribute("message", message);
		
		System.out.println("[ home()  end........]\n");
		
		return  "/user/home.jsp" ;
	}

	
	// Business Logic 수행 / Navigation  
	//============  수정된 부분 =============================/
	// Get 방식도 Interceptor 에서 처리함.
	//@RequestMapping( value="logonAction", method=RequestMethod.POST )
	@RequestMapping( value="logonAction")
	public String  logonAction(	@ModelAttribute("user") User user,
													Model model , HttpSession session ) throws Exception{

		System.out.println("[ logonAction() method=RequestMethod.POST start........]");
		System.out.println("controller입니다");

		String viewName = "/user/logon.jsp";
		
		User returnUser =  userService.getUser(user.getUserId());
		if( returnUser.getPassword().equals(user.getPassword())){
			returnUser.setActive(true);
			user = returnUser;
		}
		
		if( user.isActive() ){
			viewName = "/user/home.jsp";
			session.setAttribute("sessionUser", user);
		}
			
		System.out.println("[ action : "+viewName+ "]");		//<== 디버깅용
		
		//==> Client 에 전달할  Message 생성 
		String message = null;
		if( viewName.equals("/user/home.jsp")){
			message = "[ LogonAction() ] WELCOME";
		}else{
			message = "[ LogonAction() ] 아이디,패스워드 3자이상 입력하세요.";
		}
		
		//==> Model (data) 
		model.addAttribute("message", message);
		
		System.out.println("[ logonAction() method=RequestMethod.POST end........]\n");
		
		return viewName;
	}
	
	
	 // 권한(logon 정보삭제) 확인 / navigation
	@RequestMapping("/logout")
	public String logout( Model model , HttpSession session){
		
		System.out.println("[ Logout() start........]");//<== 디버깅용

		//==> logon 정보 삭제
		session.removeAttribute("sessionUser");

		//==> Client 에 전달할  Message 생성 
		String message = "[Logout()] 아이디,패스워드 3자이상 입력하세요.";
		
		//==> Model (data) 
		model.addAttribute("message", message);
		
		System.out.println("[ Logout() end........]\n");
		
		return "/user/logon.jsp"  ;
	}
	
}