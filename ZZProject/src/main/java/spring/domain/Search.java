package spring.domain;

import java.util.ArrayList;

/*
 * FileName : Search.java
 *   ㅇ Dynamic SQL 구성시  <foreach> elements 를 이용 반복적 구문생성시 전달되는 
 *       Collection List , Array 갖는 ValueObeject  
  */
public class Search {
	///Field
	private String[] userName;
	private ArrayList<String> userId;
	private String searchCondition;
	
	///Constructor
	public Search() {
	}
	
	///Method
	public String[] getUserName() {
		return userName;
	}
	public void setUserName(String[] userName) {
		this.userName = userName;
	}
	
	public ArrayList<String> getUserId() {
		return userId;
	}
	public void setUserId(ArrayList<String> userId) {
		this.userId = userId;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
}//end of class