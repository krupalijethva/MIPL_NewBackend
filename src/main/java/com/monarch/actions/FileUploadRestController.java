package com.monarch.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monarch.domain.User;
import com.monarch.domain.candidate.Candidatedetail;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.service.FileStorageService;
import com.monarch.service.UserService;
import com.monarch.service.candidate.EmployeeService;
import com.monarch.service.candidate.InterviewService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileUploadRestController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadRestController.class);

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	EmployeeService es;
	
	@Autowired
	UserService us;
	
	@Autowired
	InterviewService is;

	@PostMapping("/uploadFile")
	public HashMap<String, String> uploadFile(@RequestParam("file") MultipartFile file) 
	{
		try
		{
			HashMap<String, String> metamap = new HashMap<String, String>();
			String fileName = fileStorageService.storeFile(file);
			metamap.put("fileName", fileName);
			if(!metamap.isEmpty())
			{
				return metamap;
			}
			else
			{
				return null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/getImage")
	public ResponseEntity<InputStreamResource> getImage(@RequestParam(value = "username",required=false) String username,@RequestParam(value = "userid",required=false) String userid)throws IOException {
		try
		{ 
			System.out.println(userid);
			System.out.println(username);
			String filename="";
			if(userid == null || userid.equals("undefined"))
			{
				User us1=us.getUser(username);
				filename=us1.getEmployeedetail().getNewphoto();
				
			}
			else
			{
				long id=Long.parseLong(userid);
				EmployeeDetail ed=es.readInfo(id);
				filename=ed.getNewphoto();
				
			}
			if(filename == null)
			{
				filename="user-image.jpg";
			}
			Path path = fileStorageService.getFiles(filename);
			String filepath = path.toString();
			
			File file = new File(filepath);

			InputStream stream = new FileInputStream(file);
			return ResponseEntity.ok().body(new InputStreamResource(stream));
//			return null;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	
	@GetMapping(value = "/getImageById")
	public ResponseEntity<InputStreamResource> getImageById(@RequestParam(value = "userid") long user_id) throws IOException {
		try
		{ 
			System.out.println("candidate id"+user_id);
			Candidatedetail cd=is.getbyID(user_id);
			String filename=cd.getNewphoto();
			if(filename == null || filename.equals(""))
			{
				filename="user-image.jpg";
			}
			Path path = fileStorageService.getFiles(filename);
			String filepath = path.toString();
			
			File file = new File(filepath);
			InputStream stream = new FileInputStream(file);
			
			return ResponseEntity.ok().body(new InputStreamResource(stream));
		}
		catch(Exception ex)
		{
			return null;
		}
		
	}
	

}
