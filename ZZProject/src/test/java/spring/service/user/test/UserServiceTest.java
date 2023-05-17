package spring.service.user.test;

//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import spring.domain.User;
import spring.service.user.UserDao;
import spring.service.user.UserService;

/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */

//======================  변경된 부분 ====================
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
@SpringBootTest
public class UserServiceTest {

	//==>@RunWith,@ContextConfiguration 로 지정된 구현체가  Meta-data 를 이용 Wiring
	//==> 한 각 객체를 Injection 함
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDAO;

	//==> Test할 코드 작성
	//@Test
	public void testAddUser() throws Exception {
		
		System.out.println("\n===================================");
		User user = new User("user04","주몽","user04",null,0);
		System.out.println("insert 결과 : "+userService.addUser(user));
		System.out.println("delete 결과 : "+ userDAO.removeUser(user.getUserId()));
		
		//====> 변경된 부분 JUnit 5.X 변겨으로 인한 Test API 변경====================//
		//==> API확인 :: 주석을 풀고 확인하면...
		//==> 아래의 1을 0으로 변경 해보면...
		//Assert.assertEquals(1, userService.addUser(user));
		//Assert.assertEquals(1, userDAO.removeUser(user.getUserId()));
		Assertions.assertEquals(1, userService.addUser(user));
		Assertions.assertEquals(1, userDAO.removeUser(user.getUserId()));
		
		System.out.println("===================================\n");

	}
	
	//==> @Test를 주석처리 하고 실행하면....
	//==> 
	@Test
	public void testGetUser() throws Exception {
		
		System.out.println("\n===================================");
		User user = userService.getUser("user01");
		System.out.println(user);
		
		//==> API확인 :: 주석을 풀고 확인하면...
		//Assert.assertEquals("user01",user.getUserId());
		//Assert.assertEquals("user0",user.getUserId());
		//Assert.assertNotNull(userService.getUser("user02"));
		//Assert.assertNotNull(userService.getUser("user05"));
		System.out.println("===================================\n");

	}
	
	//==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetUserList() throws Exception{
	 	//==> Test code 작성
	 }

	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testUpdateUser() throws Exception{
	 	//==> Test code 작성
	 }
}