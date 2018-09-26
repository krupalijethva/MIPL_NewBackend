package com.monarch.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.User;



public interface UserService
{
	
	
	    void changePassword(User user, String newPass);
	   
	    boolean isValidUserDB(String username, String pass);

	    User updateLDAPAttributes(User user, String username, String pass);

	    public void syncDB_To_LDAP(String username, String pass);

	    boolean isValidUser(String username, String pass);

	    boolean userExists(String username);
	    
	    User getUser(String username);
	
	    User getUserFromFullName(String fullname);
	    
	    User readinfo(Long id);

	    User getuserid(String uname);
	    
	    List<User> getShiftUserList(User.Status status);
	    
	    public List<User> getActiveUserList(String status);
	    
	    public List<User> readAllUsers();
	    
	    public User save(User user);
	    
	    public Page<User> getActiveUsers(int pageno, int perpage, String sort, String title,String generalSearch);
	    
	    public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	    
	    public Page<User> getUsers(int pageno, int perpage, String sort, String title,String generalSearch);
	    
	    public Pageable createPageRequestUsers(int pageno, int perpage, String sort, String title);
	
	    User getUserByEmpId(String empid);

	    
	    
}
