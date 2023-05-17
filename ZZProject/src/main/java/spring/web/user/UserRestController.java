package spring.web.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.domain.User;

@RestController
@RequestMapping("/user/*")
public class UserRestController {
	///Field
	///Constructor
	public UserRestController(){
		System.out.println(":: UserRestController default Contructor call");
	}

	// 1.1 Client 로 Domain Object + 기타 Data 를 JSON 으로 전송할 경우.
	//요청 미디어타입 : application/json ==> 즉 데이터교환 json 
	//http://IP:8000/user/json/user01?name=홍길동&age=17
	@RequestMapping(value="json/{value}" , method=RequestMethod.GET )
	public Map getUser	(	@PathVariable String value,
											@RequestParam("name") String name,
											@RequestParam("age") int age) throws Exception{
		System.out.println();
		System.out.println(value);
		System.out.println(name);
		System.out.println(age);
		
		User user = new User();
		user.setUserId("AAA");
		user.setUserName("홍길동");
		user.setAge(100);
		System.out.println(user);
		
		Map map = new HashMap();
		map.put("user",user);
		//==> 단순히 name=value 의 Data 를 저장할 경우는 ????
		map.put("message","ok");

		return map;
	}

	// 1.2 Client 로 Domain Object만 전송할 경우.
	// 요청 미디어타입 : application/json ==> 즉 데이터교환 json 
	//http://IP:8000/user/json/user01
//	@RequestMapping(value="json/{value}" , method=RequestMethod.GET )
//	public User getUser	(	@PathVariable String value,
//											@RequestParam("name") String name,
//											@RequestParam("age") int age) throws Exception{
//	
//		System.out.println();
//		System.out.println(value);
//		System.out.println(name);
//		System.out.println(age);
//		
//		User user = new User();
//		user.setUserId("AAA");
//		user.setUserName("BBB");
//		user.setAge(100);
//		System.out.println(user);
//		
//		return user;
//	}
	
	
	
	// 2.1 Client 로 Domain Object + 기타 Data 를 JSON 으로 전송할 경우.
	//http://ip:8000/user/json/getUser/user01     {"userId":"test","userName":"홍길동" }
//	@RequestMapping(value="json/getUser/{value}" , method=RequestMethod.POST )
//	public Map getUser	(  	@PathVariable String value, 
//											@RequestBody User user ) throws Exception{	
//		System.out.println();
//		System.out.println("[ From Client Data ]");
//		System.out.println(value);
//		System.out.println("1 : "+user);
//		
//		System.out.println("[To Client Data]");
//		User returnUser = new User();
//		returnUser.setUserId("AAA");
//		returnUser.setUserName("BBB");
//		System.out.println("2 : "+returnUser);
//		
//		Map map = new HashMap();
//		map.put("user",user);
//		//==> 단순히 name=value 의 Data 를 저장할 경우는 ????
//		map.put("message","ok");
//
//		return map;
//	}
	
	// 2.2 Client 로 Domain Object만 전송할 경우.
	//http://ip:8000/user/json/getUser/user01   {"userId":"test","userName":"홍길동" }
	@RequestMapping(value="json/getUser/{value}" , method=RequestMethod.POST )
	public User getUser	(  	@PathVariable String value, 
											@RequestBody User user ) throws Exception{
		System.out.println();
		System.out.println("[ From Client Data ]");
		System.out.println(value);
		System.out.println("1 : "+user);
		
		System.out.println("[To Client Data]");
		User returnUser = new User();
		returnUser.setUserId("AAA");
		returnUser.setUserName("홍길동");
		System.out.println("2 : "+returnUser);
		
		return returnUser;
	}
	
}