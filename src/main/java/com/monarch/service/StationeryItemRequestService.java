package com.monarch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.StationeryItemRequestDetail;

public interface StationeryItemRequestService {

	public StationeryItemRequestDetail save(StationeryItemRequestDetail sird);
	public Page<StationeryItemRequestDetail> getItemReqDetails(int pageno, int perpage, String sort, String title,String username,String status,String searchterm);
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	
	public StationeryItemRequestDetail readInfo(long id);
	
}
