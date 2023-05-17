package spring.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import spring.domain.Search;
import spring.domain.User;
import spring.service.user.UserDao;
import spring.service.user.UserService;

/*
 * FileName : UserServiceImpl13.java
 * ㅇ 회원관리 서비스 비지니스 로직을 추상화한 interface 구현
 * ㅇ Component : interface 로 Encapsulation 된 재사용가능 한 library
 */
//@Service("userService")
public class UserServiceImpl_Transction처리변경됨 implements UserService {

	///Field
	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		System.out.println("::"+getClass()+".setUserDao  Call.....");
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl_Transction처리변경됨() {
		System.out.println("::"+getClass()+" default Constructor Call.....");
	}

	///Method
	//==> 회원정보 ::  INSERT ( 회원가입 )
	public int addUser(User user) throws Exception {
		////////////////////////////////////////////////////////////////////////////////////////////////////
		///// AOP를 이용한 Transaction 처리르르 위해 수정한부분 ///////
		return userDao.addUser(user);
		// Test 후 아래 주석처리하고 위 부분 주석 풀어 원상복귀 하세요.
		////////////////////////////////////////////////////////////////////////////////////////////////////
//		int result = 0;
//		System.out.println(">>>>> 1 번쩨 insert =====================");
//		result =  userDao.addUser(user);
//		System.out.println(">>>>> 1 번쩨 insert 결과 : "+result);
//		System.out.println(">>>>> 2 번쩨 insert =====================");
//		result = userDao.addUser(user);
//		System.out.println(">>>>> 2 번쩨 insert 결과 : "+result);
//		System.out.println(">>>>> 결과는 ????  =====================");
//		
//		return 0;
		////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	//==> 회원정보 ::  SELECT  ( 회원정보를 검색 ) 
	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
	//==> 회원정보 ::  UPDATE  ( 회원정보 변경  )
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	//==> 회원정보 ::  DELETE  ( 회원정보 삭제 )
	public int removeUser(String userId) throws Exception{
		return userDao.removeUser(userId);
	}
		
	//==> 회원정보 ::  SELECT  ( 모든 회원 정보 검색 )	
	public List<User> getUserList(Search search) throws Exception {
		return userDao.getUserList(search);
	}
}