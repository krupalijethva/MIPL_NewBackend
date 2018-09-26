package com.monarch.actions.punching;

import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.service.PunchingService;

@RestController
@PropertySource("classpath:jpa.properties")
@CrossOrigin(origins="*",allowedHeaders="*")
public class GenerateRecordsRestController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PunchingService ps;
	
	
	@RequestMapping(value="/generateRecords/{fromdate}/{todate}",method = RequestMethod.GET)
	public  ResponseEntity<byte[]> generatePunchingRecords(@PathVariable String fromdate,@PathVariable String todate,HttpServletResponse response)
	{
		if(fromdate !=null && todate != null )
		{
            String filepath =env.getProperty("savefilepath");
            try
            {
		            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		            Date fromDate1 = df.parse(fromdate);
		            Date toDate1 = df.parse(todate);
		            ps.generatePunchingReports(filepath, fromDate1, toDate1);
//		            response.getWriter().write(filepath);
		            byte[] array = Files.readAllBytes(new File(filepath).toPath());
		            		            System.out.println(array);
		            return ResponseEntity.ok()
							.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new File(filepath).getName() + "\"")
							.body(array);	
            } 
            catch (Exception e)
            {
            		System.out.println(e.getMessage());
            		return ResponseEntity.status(404).body(null);	
            }
        
        }
		else
		{
		
			return ResponseEntity.status(404).body(null);
		}		
	}
	
	@RequestMapping(value="/generateMonthlyRecords/{month}",method = RequestMethod.GET)
	public  ResponseEntity<byte[]> generateMonthlyRecords(@PathVariable int month,HttpServletResponse response)
	{
		 	String filepath =env.getProperty("savefilepath2");
	        try
	        {  
		        ps.generateMonthlyReport(filepath,month);
		        byte[] array = Files.readAllBytes(new File(filepath).toPath());
	            
	            System.out.println(array);
	            return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new File(filepath).getName() + "\"")
						.body(array);	
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
        		return ResponseEntity.status(404).body(null);	
	        }
	}

}
