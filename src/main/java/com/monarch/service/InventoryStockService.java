package com.monarch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.InventoryStockDetail;
import com.monarch.domain.PendriveRequestDetail;

public interface InventoryStockService {

	
	public InventoryStockDetail readInfo(long id1);
	 
	public int getTotalQuantity(String item);

	public InventoryStockDetail getItemDetailsByName(String item); 
	 
	public String isItemNameExist(String item);
	
	public void save(InventoryStockDetail isd);
	
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	public Page<InventoryStockDetail> getInventoryStocks(int pageno, int perpage, String sort, String title,String search);
	
}
