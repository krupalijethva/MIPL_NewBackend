package com.monarch.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.PunchingDetailEdit;

public interface PunchingEditService {
	
		public int getPendingRecords();
		public PunchingDetailEdit read(String username);
		public List<PunchingDetailEdit> getLast7DaysDetails(String username) ;
		public PunchingDetailEdit saveEditPunching(PunchingDetailEdit pde);
		public List<PunchingDetailEdit> readAll();
		 public Page<PunchingDetailEdit> findAll(Pageable p,String generalSearch,String punchstatus);
		 public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
		 public Page<PunchingDetailEdit> getModifiedRecords(int pageno, int perpage, String sort, String title, String generalSearch,String punchstatus);
		
		 public PunchingDetailEdit findById(long id);
		 public void create(PunchingDetailEdit pde);

}
