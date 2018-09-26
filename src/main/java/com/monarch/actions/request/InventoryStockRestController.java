package com.monarch.actions.request;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.InventoryStockDetail;
import com.monarch.domain.PendriveRequestDetail;
import com.monarch.service.InventoryStockService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class InventoryStockRestController {

	@Autowired
	InventoryStockService inventoryService;
	
	
	@RequestMapping("/getInventoryStocks")
	public HashMap<String,Object> getInventoryStocks(HttpServletRequest request)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String search=request.getParameter("query[generalSearch]");			 
			Page<InventoryStockDetail> pd=inventoryService.getInventoryStocks(pageno, perpage, sort, title,search);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("/addQuantity")
	public void addQuantity(@RequestParam(value="id") long id,@RequestParam(value="quantity") int quantity)
	{
		try
		{
			InventoryStockDetail isd=inventoryService.readInfo(id);
			
			int availQuantity=isd.getTotalavailablequantity();
			
			int result=availQuantity+quantity;
			isd.setTotalavailablequantity(result);
			inventoryService.save(isd);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/addItem")
	public void addQuantity(@RequestParam(value="item") String item,@RequestParam(value="quantity") int quantity)
	{
		try
		{
			InventoryStockDetail isd=new InventoryStockDetail();
			isd.setItem(item);
			isd.setTotalavailablequantity(quantity);
			inventoryService.save(isd);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
