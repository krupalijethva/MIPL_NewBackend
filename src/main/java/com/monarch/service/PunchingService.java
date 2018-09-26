/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.service;

import com.monarch.domain.OfficialLeaveDetail;
import com.monarch.domain.PunchingDetail;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PunchingService  {

      //TEMP add 2 Parameter
    String insertLoginTime(String username, String ipAddress ,String hostName);
    public void create(PunchingDetail pd);
    List<PunchingDetail> getLast30DaysDetails(String username);
    public PunchingDetail read(String username);
    PunchingDetail getCurrentDetails(String username);
    PunchingDetail getPunchingDetails(String username, Date date);
    public PunchingDetail getUserPunching(long id);
    public boolean isPunching(String username, Date punchdate);
    public long getLastID();
//    String getLoginTime(String username);
   String insertLogoutTime(String username);
//   
//    String getLogoutTime(String username);
    String insertBreakStartTime(String username);
//
   String insertBreakStopTime(String username);
   public Pageable createPageRequest(int pageno,int perpage,String sort,String title,String punchstatus);   
   public Page<PunchingDetail> getAllPunchingRecords(int pageno,int perpage,String sort,String title,String punchstatus,String generalSearch,String username);
   public Page<PunchingDetail> findAll(Pageable p,String punchstatus,String generalSearch,String username);
   public void deleteRecord(long id);
   public Page<PunchingDetail> getAdminPunchingRecords(int pageno,int perpage,String sort,String title,String punchstatus,String generalSearch);


   //Generate Punching Records Methods
   
   public void generatePunchingReports(String filePath, Date fromDate, Date toDate);
   
   public void generateMonthlyReport(String filepath1, int month);
   public int getTotalWorkingdays(int month);
   public int getMonthHoliday(int month);
   public List<OfficialLeaveDetail> getMonthHolidayDate(int month);
   
//
//    String getBreakTime(String username);
//    List<PunchingDetail> getLast7DaysDetails(String username);

     
     
  

    
	
// void generatePunchingReports(String filePath, Date fromDate, Date toDate);
//

//    
//  
//    
//    public PunchingDetail deleteRecord(long id);
//    
//   public long duplicateEntryExist(String username, Date logindate);
//    
//   public BigDecimal getActiveCollabehours(Date date , int acid);
//    
//   public int getTotalWorkingdays(int month);
//    
//   public  int getMonthHoliday(int month) ;
    
    //public void generateMonthlyReport(String filepath1,int month);
    
    
    
    
    
    
    
    
 
}
