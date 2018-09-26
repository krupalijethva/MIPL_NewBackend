package com.monarch.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventorystationeryitem")
public class InventoryStockDetail {
	
	
	
	@Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
     
    @Column(name = "item")
    private String item;
     
    @Column(name = "totalavailablequantity")
    private int totalavailablequantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getTotalavailablequantity() {
        return totalavailablequantity;
    }

    public void setTotalavailablequantity(int totalavailablequantity) {
        this.totalavailablequantity = totalavailablequantity;
    }


}
