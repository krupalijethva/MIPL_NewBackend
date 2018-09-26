package com.monarch.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.InventoryStockDetail;
import com.monarch.domain.PunchingDetail;



public interface InventoryStockRepository extends JpaRepository<InventoryStockDetail, Long> {
	
	
	@Query("Select totalavailablequantity FROM InventoryStockDetail as isd WHERE isd.item = :item")
	public int getTotalQuantity(@Param("item") String item);
	
	@Query("FROM InventoryStockDetail as isd WHERE isd.item = :item")
	public InventoryStockDetail getItemDetailsByName(@Param("item") String item);
	
	@Query("from InventoryStockDetail")
	public Page<InventoryStockDetail> findAllDetails(Pageable p);
	
	@Query("select isd from InventoryStockDetail isd WHERE ("
			+"LOWER(isd.item) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "isd.totalavailablequantity LIKE CONCAT('%',:searchterm,'%'))")
	public Page<InventoryStockDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm);

	
	

	
}
