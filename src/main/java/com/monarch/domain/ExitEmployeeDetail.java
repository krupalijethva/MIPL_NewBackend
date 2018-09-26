package com.monarch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "exitemployeedetail")
public class ExitEmployeeDetail {

	@Id
    @GeneratedValue
    @Column(name = "idexitemployee")
    private Long idexitemployee;

    @Column(name = "username")
    private String username;

   @Column(name = "reason")
    private String reason;
   
   @Temporal(TemporalType.DATE)
   @Column(name = "applieddate")
    private Date applieddate;

   @Column(name = "idcard")
    private Boolean idcard;
   
   @Column(name = "deskkey")
   private Boolean deskkey;
 
   @Column(name = "otherKey")
   private Boolean otherKey;
   
   @Column(name = "material")
   private Boolean material;
   
   @Column(name = "companyvehicle")
   private Boolean companyvehicle;
   
   @Column(name = "otherEquipment")
   private String otherEquipment;

public Long getIdexitemployee() {
	return idexitemployee;
}

public void setIdexitemployee(Long idexitemployee) {
	this.idexitemployee = idexitemployee;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}

public Date getApplieddate() {
	return applieddate;
}

public void setApplieddate(Date applieddate) {
	this.applieddate = applieddate;
}

public Boolean getIdcard() {
	return idcard;
}

public void setIdcard(Boolean idcard) {
	this.idcard = idcard;
}

public Boolean getDeskkey() {
	return deskkey;
}

public void setDeskkey(Boolean deskkey) {
	this.deskkey = deskkey;
}

public Boolean getOtherKey() {
	return otherKey;
}

public void setOtherKey(Boolean otherKey) {
	this.otherKey = otherKey;
}

public Boolean getMaterial() {
	return material;
}

public void setMaterial(Boolean material) {
	this.material = material;
}

public Boolean getCompanyvehicle() {
	return companyvehicle;
}

public void setCompanyvehicle(Boolean companyvehicle) {
	this.companyvehicle = companyvehicle;
}

public String getOtherEquipment() {
	return otherEquipment;
}

public void setOtherEquipment(String otherEquipment) {
	this.otherEquipment = otherEquipment;
}

   
	
	
}
