package com.monarch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.FolderAccessRequestDetail;

public interface FolderAccessRequestService {
	
	public FolderAccessRequestDetail save(FolderAccessRequestDetail fard);
	public FolderAccessRequestDetail readInfo(long id);
	public Page<FolderAccessRequestDetail> getFolderReqDetails(int pageno, int perpage, String sort, String title,String username,String status,String searchterm);
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
}
