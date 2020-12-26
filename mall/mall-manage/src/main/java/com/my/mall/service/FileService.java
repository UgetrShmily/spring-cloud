package com.my.mall.service;

import org.springframework.web.multipart.MultipartFile;

import com.my.mall.vo.EasyUiImageVo;

public interface FileService {
	public EasyUiImageVo uploadFile(MultipartFile uploadFile);
}
