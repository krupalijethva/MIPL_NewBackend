package com.monarch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licensedetail")
public class LicenseDetail {

	public enum APP_NAME {
        IPROPERTY,

    }

    @Id
    @GeneratedValue
    @Column(name = "idLicenseDetail")
    private long idLicenseDetail;

    @Column(name = "company")
    private String company;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "os")
    private String os;

    @Column(name = "downloaddate")
    private Date downloaddate;

    @Column(name = "licensedate")
    private Date licensedate;

    @Column(name = "validtilldate")
    private Date validtilldate;

    @Column(name = "registrationkey")
    private String registrationkey;

    @Column(name = "licensekey")
    private String licensekey;

    @Column(name = "contactno")
    private String contactno;

    @Column(name = "address")
    private String address;

    @Column(name = "application")
    private String application;

    @Column(name = "internal")
    private boolean internal;

    @Column(name = "remarks")
    private String remarks;

    public long getIdLicenseDetail() {
        return idLicenseDetail;
    }

    public void setIdLicenseDetail(int idLicenseDetail) {
        this.idLicenseDetail = idLicenseDetail;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Date getDownloaddate() {
        return downloaddate;
    }

    public void setDownloaddate(Date downloaddate) {
        this.downloaddate = downloaddate;
    }

    public Date getLicensedate() {
        return licensedate;
    }

    public void setLicensedate(Date licensedate) {
        this.licensedate = licensedate;
    }

    public Date getValidtilldate() {
        return validtilldate;
    }

    public void setValidtilldate(Date validtilldate) {
        this.validtilldate = validtilldate;
    }

    public String getRegistrationkey() {
        return registrationkey;
    }

    public void setRegistrationkey(String registrationkey) {
        this.registrationkey = registrationkey;
    }

    public String getLicensekey() {
        return licensekey;
    }

    public void setLicensekey(String licensekey) {
        this.licensekey = licensekey;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
		
}
