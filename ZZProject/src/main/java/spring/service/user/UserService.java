package spring.service.user;

import java.util.List;

import spring.domain.Search;
import spring.domain.User;

/*
 * FileName : UserService.java
 * ㅇ 회원관리 서비스 비지니스 로직을 추상화한 interface
 * ㅇ Component : interface 로 Encapsulation 된 재사용가능 한 lib
 */
public interface UserService {
		
	//==> 회원정보 ::  INSERT ( 회원가입 )
	public int addUser(User user) throws Exception;
	
	//==> 회원정보 ::  SELECT  ( 회원정보 검색 ) 
	public User getUser(String userId) throws Exception;

	//==> 회원정보 ::  UPDATE  ( 회원정보 변경  )
	public int updateUser(User user) throws Exception;
	
	//==> 회원정보 ::  DELETE  ( 회원정보 삭제 )
	public int removeUser(String userId) throws Exception;
	
	//==> 회원정보 ::  SELECT  ( 회원 정보 검색 )
	public List<User> getUserList(Search search) throws Exception;
	
}