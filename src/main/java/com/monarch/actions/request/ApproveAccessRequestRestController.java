package com.monarch.actions.request;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.FolderAccessRequestDetail;
import com.monarch.domain.InternetAccessRequestDetail;
import com.monarch.domain.InventoryStockDetail;
import com.monarch.domain.PendriveRequestDetail;
import com.monarch.domain.StationeryItemRequestDetail;
import com.monarch.service.FolderAccessRequestService;
import com.monarch.service.InternetAccessRequestService;
import com.monarch.service.InventoryStockService;
import com.monarch.service.PendriveReqestService;
import com.monarch.service.StationeryItemRequestService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ApproveAccessRequestRestController {

	
	@Autowired
	PendriveReqestService pendriveService;
	
	@Autowired
	InternetAccessRequestService internetService;
	
	@Autowired
	FolderAccessRequestService folderService;
	
	@Autowired
	StationeryItemRequestService stationeryService;
	
	@Autowired
	InventoryStockService inventoryService;
	
	@RequestMapping("/pendriveDetailsById")
	public HashMap<String, Object> pendriveDetailsById(@RequestParam(value="id") long id)
	{
		try
		{
			HashMap< String, Object> hm=new HashMap<>();
			PendriveRequestDetail pendriveObj=pendriveService.readInfo(id);
			hm.put("pendrive_id", pendriveObj.getPendrive_id());
			return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("/actionOnPendriveRequest")
	public void pendriveAction(@RequestParam(value="id") long id,@RequestParam(value="action") String action,@RequestParam(value="comments",required=false) String comments)
	{
		try
		{
			PendriveRequestDetail pendriveObj=pendriveService.readInfo(id);
			if (action.equals("approve")) {
	            Date date = new Date();
	            pendriveObj.setStatus(PendriveRequestDetail.Status.Approved);
	            pendriveObj.setIssuedate(date);
	            pendriveObj.setComments(comments);
	        } else if (action.equals("deny")) {
	        	pendriveObj.setStatus(PendriveRequestDetail.Status.Denied);
	        	pendriveObj.setComments(comments);
	        } else if (action.equals("receive")) {
	            Date date = new Date();
	            pendriveObj.setStatus(PendriveRequestDetail.Status.Return);
	            pendriveObj.setReturndate(date);
	            pendriveObj.setComments(comments);
	        }
	       
			pendriveService.save(pendriveObj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/internetReqDetailsById")
	public HashMap<String, Object> internetReqDetailsById(@RequestParam(value="id") long id)
	{
		try
		{
			HashMap< String, Object> hm=new HashMap<>();
			InternetAccessRequestDetail iard=internetService.readInfo(id);
			hm.put("internet_id", iard.getInternetaccessid());
			return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("/actionOnInternetRequest")
	public void internetAction(@RequestParam(value="id") long id,@RequestParam(value="action") String action,@RequestParam(value="comments",required=false) String comments)
	{
		try
		{
			 InternetAccessRequestDetail iard = internetService.readInfo(id);
	            if (action.equals("approve")) {
	                Date date = new Date();
	                iard.setStatus(InternetAccessRequestDetail.Status.Approved);
	                iard.setAccessdate(date);
	                iard.setComments(comments);
	            } else if(action.equals("deny")) {
	                iard.setStatus(InternetAccessRequestDetail.Status.Denied);
	                iard.setComments(comments);
	            } else if(action.equals("receive")) {
	                Date date = new Date();
	                iard.setStatus(InternetAccessRequestDetail.Status.RemovedAccess);
	                iard.setReturndate(date);
	                iard.setComments(comments);
	            }
	            internetService.save(iard);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/folderReqDetailsById")
	public HashMap<String, Object> folderReqDetailsById(@RequestParam(value="id") long id)
	{
		try
		{
			HashMap< String, Object> hm=new HashMap<>();
			FolderAccessRequestDetail fard=folderService.readInfo(id);
			hm.put("folder_id", fard.getFolderaccessid());
			return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("/actionOnFolderRequest")
	public void folderAction(@RequestParam(value="id") long id,@RequestParam(value="action") String action,@RequestParam(value="comments",required=false) String comments)
	{
		try
		{
			FolderAccessRequestDetail fard=folderService.readInfo(id);
			 if (action.equals("approve")) {
	                Date date = new Date();
	                fard.setStatus(FolderAccessRequestDetail.Status.Approved);
	                fard.setAccessdate(date);
	                fard.setComments(comments);
	            } else if(action.equals("deny")) {
	                fard.setStatus(FolderAccessRequestDetail.Status.Denied);
	                fard.setComments(comments);
	            } else if(action.equals("receive")) {
	                Date date = new Date();
	                fard.setStatus(FolderAccessRequestDetail.Status.RemovedAccess);
	                fard.setAccessremoveddate(date);
	                fard.setComments(comments);
	            }
	          
			 folderService.save(fard);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/itemReqDetailsById")
	public HashMap<String, Object> itemReqDetailsById(@RequestParam(value="id") long id)
	{
		try
		{
			HashMap< String, Object> hm=new HashMap<>();
			StationeryItemRequestDetail sird=stationeryService.readInfo(id);
			hm.put("sitem_id", sird.getId());
			hm.put("item", sird.getSelectitem());
			hm.put("quantity", sird.getNoofquantity());
			return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@RequestMapping("/actionOnSItemRequest")
	public boolean sitemAction(@RequestParam(value="id") long id,@RequestParam(value="action") String action,@RequestParam(value="comments",required=false) String comments,@RequestParam(value="item",required=false) String item,@RequestParam(value="noofquantity",required=false) String noofquantity)
	{
		try
		{
			StationeryItemRequestDetail sird=stationeryService.readInfo(id);
			InventoryStockDetail inventoryObj = new InventoryStockDetail(); 
			int noofquantity1=Integer.parseInt(noofquantity);
			
			boolean presentQuantity=true;
			
			 if (action.equals("approve")) 
			 {
				 
				 int quantity = inventoryService.getTotalQuantity(item);
	             int result = quantity - noofquantity1;
				 
	             if (quantity <= noofquantity1) {

	            	 presentQuantity=false;
	            			 
	              }
	             else
	             {
	            	 	Date date = new Date();
	            	 	sird.setStatus(StationeryItemRequestDetail.Status.Approved);
	            	 	sird.setIssuedate(date);
	            	 	sird.setComments(comments);
	            	 	InventoryStockDetail isd=inventoryService.getItemDetailsByName(item);
	                    
	            	 	isd.setTotalavailablequantity(result);
	                    inventoryService.save(isd);
	             }
	        } 
			else if(action.equals("deny")) 
	        {
	        	  sird.setStatus(StationeryItemRequestDetail.Status.Denied);
	                sird.setComments(comments);
	        } 
			else if(action.equals("return")) 
			{
				Date date = new Date();
				sird.setStatus(StationeryItemRequestDetail.Status.Return);
				sird.setReturndate(date);
				sird.setComments(comments);
				
				 int quantity = inventoryService.getTotalQuantity(item);
	             int result = quantity + noofquantity1;

	             InventoryStockDetail isd=inventoryService.getItemDetailsByName(item);
                 
         	 	 isd.setTotalavailablequantity(result);
                 inventoryService.save(isd);
	        }
	          
			 stationeryService.save(sird);
			 
			 return presentQuantity;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
}
