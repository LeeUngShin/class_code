package com.example.demo;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UploadFileService {

	public String upload(MultipartFile file, HttpServletRequest request) {

		boolean result = false;

		String fileOriName = file.getOriginalFilename(); // 실제 이름
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		//String uploadDir = "C:\\upload\\";
		String uploadDir = request.getServletContext().getRealPath("/") + "/../resources/static/upload";

		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File saveFolder = new File(uploadDir);
		if (!saveFolder.exists())
			saveFolder.mkdir();

		File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension); // 업로드패쓰

		try {
			file.transferTo(saveFile);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result) {
			System.out.println("파일업로드 성공");
			return uniqueName + fileExtension; // 파일명 + 확장자
		} else {
			System.out.println("파일업로드 실패");
			return null;
		}
	}
}
