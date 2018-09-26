package com.monarch.service;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.User;
import com.monarch.repository.UserRepository;
import com.monarch.utils.HashUtil;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userre;
	
	
	
	
		@Override
	    public User getUser(String username)
		{
		 
		 	
			if (!username.toLowerCase().endsWith("@monarch.com")) 
			{
	            username += "@monarch.com";
	        }
 	    	User user1=userre.getUser(username);
	    	
	        return user1;
	    }
		
		@Override
		public List<User> readAllUsers()
		{
			List<User> listu=userre.findAll();
			return listu;
		}
	 	
		@Override
	    public User getUserFromFullName(String fullname)
		{

	       
	        User user1 = userre.getUserFromFullName(fullname);
	        
	        return user1;

	    }
		
		@Override
	    public User readinfo(Long id)
		{
	      
	        User user = userre.findOne(id);
	        // interview1.getActivitydetail();
	        //  t.commit();
	        //  session.close();
	        return user;

	    }
		 @Override
		 public User getuserid(String uname) 
		 {
		    	
		      User ivdata = userre.getuserid(uname);
		      return ivdata;

		 }
		 @Override
			public User save(User user) {
				// TODO Auto-generated method stub
				User us=userre.save(user);
				return us;
			}

		    
		 
		 @Override
		 public List<User> getShiftUserList(User.Status status) 
		 {
		       
			  List<User> ulist=userre.getShiftUserList(status);
			  return ulist;
		 }

		 @Override
		 public List<User> getActiveUserList(String status) 
		 {
		    	List<User> ulist=userre.getActiveUserList(status);
		        return ulist;
		 }
		 
		 @Override
		 public boolean userExists(String username) 
		 {
		        User user1 = this.getUser(username);
		        if (user1 == null) {
		            return false;
		        }
		        else
		        {
		        return true;
		        }
		 }
		    
		 @Override
		 public void changePassword(User user, String newPass) 
		 {
		        
		    	 user.setPassword(newPass);
		 }
		    
		 @Override	
		 public boolean isValidUserDB(String username, String pass)
		 {
			
	        
		        User user1 = this.getUser(username);
	
		        boolean result = HashUtil.checkpw(pass, user1.getPassword());
	
		        return result;
		 }
		 
		 @Override
		 public User updateLDAPAttributes(User user, String username, String pass)
		 {

		    	 Hashtable env = new Hashtable();
		         env.put(Context.INITIAL_CONTEXT_FACTORY,
		                 "com.sun.jndi.ldap.LdapCtxFactory");
		         env.put(Context.PROVIDER_URL, "ldap://192.168.200.201:389");
	
		         // Authenticate as S. User and password "mysecret"
		         env.put(Context.SECURITY_AUTHENTICATION, "simple");
		         env.put(Context.SECURITY_PRINCIPAL, username);
		         env.put(Context.SECURITY_CREDENTIALS, pass);

		         try {
		        	 
		             DirContext ctx = new InitialDirContext(env);
		             username = username.replace("@monarch.com", "");
	//	             String filter = "(&(sn=Dodiya))";
		             String filter = "(&(sAMAccountName=" + username + "))";
	
		             String[] attrIDs = {"sn", "userPrincipalName", "name", "sAMAccountName", "employeeID", "userAccountControl"};
		             SearchControls ctls = new SearchControls();
		             ctls.setReturningAttributes(attrIDs);
	
		             NamingEnumeration e = ctx.search("OU=IT Developer,OU=Domain Controllers,DC=MONARCH,DC=COM", filter, ctls);
	
		             boolean gotResults = false;
		             while (e.hasMore()) {
		                 gotResults = true;
		                 SearchResult entry = (SearchResult) e.next();
		                 Attributes attrs = entry.getAttributes();
		                 for (NamingEnumeration ne = attrs.getAll(); ne.hasMore();) {
		                     Attribute attr = (Attribute) ne.next();
		                     //System.out.println(attr.get().toString());
		                     String attrName = attr.getID();
		                     String attrValue = attr.get().toString();
	
	//	                     if (attrName.equals("userAccountControl")) {
	//	                         String value1 = attrValue;
	//	                         if (value1.equals(514)) {
	//	                             user.setUserStatus("inActive");
	//	                         } else {
	//	                             user.setUserStatus("Active");
	//	                         }
	//	                     }
		                     if (attrName.equals("name")) {
		                        
		                         user.setFullname(attrValue);
		                         
		                     } else if (attrName.equals("employeeID")) {
		                         user.setEmployeeid(attrValue);
		                     }
		                 }
		             }
	
		             if (!gotResults) {
		                 e = ctx.search("OU=Engineering,OU=Domain Controllers,DC=MONARCH,DC=COM", filter, ctls);
		                 // (!(useraccountcontrol:1.2.840.113556.1.4.803:=2)
		                 gotResults = true;
		                 SearchResult entry = (SearchResult) e.next();
		                 Attributes attrs = entry.getAttributes();
		                 for (NamingEnumeration ne = attrs.getAll(); ne.hasMore();) {
		                     Attribute attr = (Attribute) ne.next();
		                     //System.out.println(attr.get().toString());
		                     String attrName = attr.getID();
		                     String attrValue = attr.get().toString();
	
		                     if (attrName.equals("name")) {
		                         user.setFullname(attrValue);
		                         
		                     } else if (attrName.equals("employeeID")) {
		                         user.setEmployeeid(attrValue);
		                     }
		                 }
		             }
	
		             ctx.close();
	
		             //update(user);
		             return user;

	         } 
		         
		         
		     catch (NamingException e) 
		     {

	         } 
		     finally 
		     {

	         }
	         return null;


	    }

		@Override
	    public boolean isValidUser(String username, String pass) 
		{
	      
	        User user1 = userre.getUser(username);
	            if (user1 != null) {
	                if (user1.getUserStatus().equals("Deactivate")) {
	                   return false;
	                  // return ErrorStatus.FAIL;
	                }
	            }

	        if (pass.trim().equals("")) {
	            return false;
	             //return ErrorStatus.FAIL;
	        }

	        if (!username.toLowerCase().endsWith("@monarch.com")) {
	            username += "@monarch.com";
	        }

	        boolean result = false;
	        Hashtable env = new Hashtable();
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        env.put(Context.PROVIDER_URL, "ldap://192.168.200.201:389");
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");
	        env.put(Context.SECURITY_PRINCIPAL, username);
	        env.put(Context.SECURITY_CREDENTIALS, pass);
	        
	        //(userAccountControl:1.2.840.113556.1.4.803:=2)
	        // User user = getUser(username);
	        try {
	            DirContext ctx = null;
	            try {
	                ctx = new InitialDirContext(env);
	                ctx.close();
	                  
	                syncDB_To_LDAP(username, pass);
	                
	                result = true;
	               
	            }
	            
	           catch (NamingException e1)
	            {
	            	
	        	   
	        	   result = isValidUserDB(username, pass);
	        	   
//	           System.out.println(e1.getMessage());
//	           if(e1.getMessage().equals("[LDAP: error code 49 - 80090308: LdapErr: DSID-0C0903D9, comment: AcceptSecurityContext error, data 52e, v2580
	
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            result = false;
	        }
	        
	        return result;
	        
	       }
		   @Override
		   public void syncDB_To_LDAP(String username, String pass) 
		   {
	        //ErrorStatus status = null;
		        User user =this.getUser(username);
		        if (user == null) 
		        {
		            user = new User();
		            user.setUsername(username);
		            user.setPassword(HashUtil.hashString(pass));
		            user.setUserStatus("Activate");
		            user = updateLDAPAttributes(user, username, pass);
		            if(user != null)
		            {
		             this.save(user);
		             //status = ErrorStatus.SUCCESS;
		            }
		            //else{
	//	            status = ErrorStatus.ERROR;
	//	            }
		           //update(user)
		            //create(user);
		        } 
		        else if (!HashUtil.checkpw(pass, user.getPassword())) 
		        {
		            updateLDAPAttributes(user, username, pass);
		        }
	        //return status;
	    }

		
	    
		   
			// pagination related
			@Override
			public Pageable createPageRequest(int pageno, int perpage, String sort, String title)
			{
			
			
				Pageable p = new PageRequest(pageno - 1, perpage);

				System.out.println(p);

				return p;
			}

			@Override
			public Page<User> getActiveUsers(int pageno,int perpage,String sort, String title,String generalSearch)
			{

				Pageable p = createPageRequest(pageno, perpage, sort, title);

				String status="Activate";

				
				Page<User> pd = null;
				
				if(generalSearch!=null) 
				{

					pd =userre .getActiveUsers(status,p, generalSearch);
								
				}
				
				else 
				{
					pd=userre.getActiveUsers(p,status);
				}
				return pd;

			}

			@Override
			public Page<User> getUsers(int pageno, int perpage, String sort, String title, String generalSearch) {
				Pageable p = createPageRequest(pageno, perpage, sort, title);

				Page<User> pd = null;
				
				if(generalSearch!=null) 
				{

					pd =userre.getUsers(p, generalSearch);
								
				}
				
				else 
				{
					pd=userre.getUsers(p);
				}
				return pd;


			}

			@Override
			public Pageable createPageRequestUsers(int pageno, int perpage, String sort, String title) {
				String sort1 = "asc";
				String title1 = "fullname";

				if (sort == null || title == null) 
				{
					sort = sort1;
					title = title1;
				}
				Pageable p = new PageRequest(pageno - 1, perpage,Sort.Direction.fromString(sort),title);

				System.out.println(p);
				
				return p;
			}

			@Override
			public User getUserByEmpId(String empid) {
				
				User u=userre.getUserByEmpId(empid);
				
				// TODO Auto-generated method stub
				return u;
			}

	    
	   
	    
}

	    
