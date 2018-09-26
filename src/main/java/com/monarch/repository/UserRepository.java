package com.monarch.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.monarch.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("FROM User AS user WHERE user.username =  ?1")
	 public User getUser(String username);
	
	
	@Query("FROM User AS user WHERE user.fullname =  ?1")
	  public User getUserFromFullName(String fullname);
	
	
	@Query("from User  AS user WHERE user.id = ?1")
	 public User readinfo(Long id);
	
	
	@Query("from User As user WHERE user.username = ?1")
	 public User getuserid(String uname);
	
	
	@Query("FROM User AS user WHERE user.shifttype = :status AND user.userStatus = 'Activate'")
	public List<User> getShiftUserList(@Param("status") User.Status status);
	
	
	@Query("FROM User AS user WHERE user.userStatus = :status")
	 public List<User> getActiveUserList(@Param("status") String status);
	
	
	
	 @Query("FROM User AS user WHERE user.userStatus =  :status ")
	 public Page<User> getActiveUsers(Pageable p,@Param("status") String status);
	
	 
	 @Query("select user from User user WHERE user.userStatus =  :status  AND("
				+ "user.employeeid LIKE CONCAT('%',:searchterm,'%') OR "
				+ "user.fullname LIKE CONCAT('%',:searchterm,'%') OR "
				+ "user.leavedetail.totalleave LIKE CONCAT('%',:searchterm,'%'))")
		public Page<User> getActiveUsers(@Param("status") String status, Pageable pageable,@Param("searchterm") String searchterm);

	 
	 
	 @Query("FROM User AS user")
	 public Page<User> getUsers(Pageable p);
	
	 
	 @Query("select user from User user WHERE ("
				+ "user.employeeid LIKE CONCAT('%',:searchterm,'%') OR "
				+ "user.fullname LIKE CONCAT('%',:searchterm,'%') OR "
				+ "user.username LIKE CONCAT('%',:searchterm,'%'))")
		public Page<User> getUsers(Pageable pageable,@Param("searchterm") String searchterm);
	 
	 @Query("FROM User AS user WHERE user.employeeid =  ?1")
	 public User getUserByEmpId(String employeeid);
	
}
