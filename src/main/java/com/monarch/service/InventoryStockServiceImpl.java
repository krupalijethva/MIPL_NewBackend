package com.monarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.InventoryStockDetail;
import com.monarch.domain.PendriveRequestDetail;
import com.monarch.repository.InventoryStockRepository;

@Service
public class InventoryStockServiceImpl implements InventoryStockService {

	@Autowired
	InventoryStockRepository inventoryRepo;
	
	@Override
	public InventoryStockDetail readInfo(long id1) {
		InventoryStockDetail isd=inventoryRepo.findOne(id1);
		return isd;
	}

	@Override
	public int getTotalQuantity(String item) {
		int total=inventoryRepo.getTotalQuantity(item);
		return total;
	}

	@Override
	public InventoryStockDetail getItemDetailsByName(String item) {
		InventoryStockDetail isd=inventoryRepo.getItemDetailsByName(item);
		return isd;
	}

	@Override
	public String isItemNameExist(String item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(InventoryStockDetail isd) {
		inventoryRepo.save(isd);
		
	}

	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title) {
		String sort1="asc";
    	String title1="item";
    	if(sort == null && title == null)
    	{
    		sort=sort1;
    		title=title1;
    	}
    	
    	Pageable p=new PageRequest(pageno-1,perpage,Sort.Direction.fromString(sort),title);
        
        return p;	
	}

	@Override
	public Page<InventoryStockDetail> getInventoryStocks(int pageno, int perpage, String sort, String title,String search) {
		Pageable p=createPageRequest(pageno, perpage, sort,title);
	    
    	Page<InventoryStockDetail> inventoryObj=null;
    	
    	if(search != null)
    	{
    		inventoryObj=inventoryRepo.findAllDetails(p,search);
    	}
    	else
    	{
    		inventoryObj=inventoryRepo.findAllDetails(p);
    	}
    	
    	
    	return inventoryObj;
	}
	
	

}
