package com.monarch.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.ExitEmployeeDetail;
import com.monarch.domain.LicenseDetail;
import com.monarch.repository.LicenseRepository;

@Service
public class LicenseDetailServiceImpl implements LicenseDetailService {

	@Autowired
	LicenseRepository licenseRepo;

	@Override
	public long save(LicenseDetail ld) {

		LicenseDetail ld1=licenseRepo.save(ld);
		
		return ld1.getIdLicenseDetail();	
	}
	
	

	@Override
	public LicenseDetail readInfo(long id) {
		
		LicenseDetail ld=licenseRepo.findOne(id);

		return ld;
	}

	@Override
	public String generateLicenseKey(long id, Date date1) {
		 LicenseDetail ld = readInfo(id);
	        String regKey = ld.getRegistrationkey();

	        Date date2 = new Date();
	        date1.setDate(date1.getDate() + 1);
	        long oldMillis = date1.getTime();

	        String appName = LicenseManager.getApplicationName2(regKey);
	        String licenseKey = LicenseManager.generateLicenseKey2(regKey, oldMillis);
	        ld.setApplication(appName);
	        ld.setLicensekey(licenseKey);
	        ld.setLicensedate(new Date());
	        ld.setValidtilldate(date1);
	        save(ld);
	        return licenseKey;
	}

	@Override
	public String generateLicenseKey(long id) {
		
			LicenseDetail ld=readInfo(id);
			String regKey = ld.getRegistrationkey();
	        String appName = LicenseManager.getApplicationName2(regKey);
	        String licenseKey = LicenseManager.generateLicenseKeyPerpetual(regKey);
	        ld.setApplication(appName);
	        ld.setLicensekey(licenseKey);
	        ld.setLicensedate(new Date());
	        save(ld);
		return licenseKey;
	}

	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title) {
		String sort1 = "asc";
		String title1 = "firstname";

		if (sort == null || title == null) 
		{
			sort = sort1;
			title = title1;
		}
		Pageable p = new PageRequest(pageno - 1, perpage,Sort.Direction.fromString(sort1),title1);

		System.out.println(p);

		return p;
	}

	@Override
	public Page<LicenseDetail> getPendingLicenseDetails(int pageno, int perpage, String sort, String title,
			String generalSearch) {
		
		
		Pageable p = createPageRequest(pageno, perpage, sort, title);

		
		Page<LicenseDetail> eed = null;
		
//		if(generalSearch!=null) 
//		{
//			eed = eer.findBySearchTerm(p, generalSearch);
//		
//		}
//	
//		else 
//		{
			eed=licenseRepo.findBySearchTerm(p);
//		}

		return eed;

		// TODO Auto-generated method stub
	}
	public Page<LicenseDetail> getPendingLicenseDetails(int pageno, int perpage, String sort, String title,
			String generalSearch,long licenseid) {
		
		
		Pageable p = createPageRequest(pageno, perpage, sort, title);

		
			Page<LicenseDetail> eed = null;
		
			eed=licenseRepo.findBySearchTerm(p,licenseid);
			
	
//		if(generalSearch!=null) 
//		{
//			eed = eer.findBySearchTerm(p, generalSearch);
//		
//		}
//	
//		else 
//		{
			

		return eed;

		// TODO Auto-generated method stub
	}

	@Override
	public Pageable createPageRequest2(int pageno, int perpage, String sort, String title) {
		String sort1 = "asc";
		String title1 = "firstname";

		if (sort == null || title == null) 
		{
			sort = sort1;
			title = title1;
		}
		Pageable p = new PageRequest(pageno - 1, perpage,Sort.Direction.fromString(sort1),title1);

		System.out.println(p);

		return p;
	}

	@Override
	public Page<LicenseDetail> getAllLicenseDetails(int pageno, int perpage, String sort, String title,
			String generalSearch) {

		Pageable p = createPageRequest(pageno, perpage, sort, title);

		
		Page<LicenseDetail> eed = null;
		
//		if(generalSearch!=null) 
//		{
//			eed = eer.findBySearchTerm(p, generalSearch);
//		
//		}
//	
//		else 
//		{
			eed=licenseRepo.getLicenseDetails(p);
			return eed;
		
	}

	@Override
	public void delete(long id) {
		licenseRepo.delete(id);
	}

	
	
	
	
}
