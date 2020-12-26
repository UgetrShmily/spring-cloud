package com.my.mall.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.mall.service.FileService;
import com.my.mall.vo.EasyUiImageVo;

@RestController
@RequestMapping("/pic")
public class FileController {
	@Autowired
	private FileService fileService;
	//url:http://localhost:8091/pic/upload?dir=image 
	@RequestMapping("/upload")
	public EasyUiImageVo test(MultipartFile uploadFile) throws IOException {
		System.out.println("==========文件上传==========");
		return fileService.uploadFile(uploadFile);
	}
}
