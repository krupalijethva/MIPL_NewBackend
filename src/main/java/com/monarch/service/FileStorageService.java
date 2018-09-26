package com.monarch.service;

import java.nio.file.Path;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.monarch.domain.candidate.FileStorageProperties;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	private final Path permanentFileStorage;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getTempDir()).toAbsolutePath().normalize();
		this.permanentFileStorage = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {

		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				System.out.println("Sorry! Filename contains invalid path sequence ");
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			System.out.println(targetLocation);

			return fileName;
		} catch (IOException ex) {
			return "";
		}
	}

	public String storePermanentFile(String oldFilename, String newFilename) throws IOException {
		// Normalize file name

		System.out.println(oldFilename);
		System.out.println(newFilename);
		String TempPath = this.fileStorageLocation + "\\" + oldFilename;
		String PermanentPath = this.permanentFileStorage + "\\" + newFilename;

		File file = new File(TempPath);

		if (file.renameTo(new File(PermanentPath))) {
			// if file copied successfully then delete the original file
			file.delete();
			System.out.println("File moved successfully");
			return newFilename;
		} else {
			System.out.println("Failed to move the file");
			return null;
		}

	}

	public String editPermanentFile(String oldFilename, String newFilename, String tempFile) throws IOException 
	{
		System.out.println(oldFilename);
		System.out.println(newFilename);
		String TempPath = this.fileStorageLocation + "\\" + oldFilename;
		String PermanentPath = this.permanentFileStorage + "\\" + newFilename;
		String tempRenameFile = (this.permanentFileStorage.resolve(tempFile)).toString();

		File file = new File(TempPath);

		if (file.renameTo(new File(PermanentPath))) {
			file.delete();
			System.out.println("File moved successfully");
			File tempfile = new File(tempRenameFile);
			tempfile.delete();
			return newFilename;
		} else {
			File file1 = new File(tempRenameFile);
			file1.renameTo(new File(PermanentPath));
			return null;
		}

	}

	public Path getFiles(String filename) {
		Path targetLocation = this.permanentFileStorage.resolve(filename);
		return targetLocation;
	}

	public boolean renameFile(String filename, String tempfilename) {
		boolean rename = false;
		try {
			Path targetLocation = this.permanentFileStorage.resolve(filename);
			String filename1 = targetLocation.toString();
			String TempPath = this.permanentFileStorage + "\\" + tempfilename;

			File file1 = new File(filename1);
			File file2 = new File(TempPath);

			if (file1.renameTo(file2)) {
				rename = true;
				System.out.println("rename successful");
			} else {
				System.out.println("Rename failed");
			}
			return rename;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean deleteFile(String filename) {
		boolean deleted = false;
		try {
			Path targetLocation = this.permanentFileStorage.resolve(filename);
			String filename1 = targetLocation.toString();

			if (filename != null) {
				File file = new File(filename1);
				file.delete();
				deleted = true;

			} else {
				deleted = false;
			}

			return deleted;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
