package com.my.mall.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.mall.config.ImagePathConfig;
import com.my.mall.service.FileService;
import com.my.mall.vo.EasyUiImageVo;
/**
 * 实现思路:
 * 	1.校验是否为图片 jpg|png|gif...
 *  2.防止恶意程序
 *  3.分目录保存图片 1.按照图片类型 2.按照时间划分 yyyy/MM/dd
 *  4.防止图片重名    1.原有名称+随机数3位.jpg
 *  			   2.UUID.jpg
 */
@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private ImagePathConfig imagePathConfig;
	@Override
	public EasyUiImageVo uploadFile(MultipartFile uploadFile) {
		//后缀校验
		String fileName=uploadFile.getOriginalFilename().toLowerCase();
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			return EasyUiImageVo.fail();
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width=bufferedImage.getWidth();
			int height=bufferedImage.getHeight();
			if(width==0||height==0) return EasyUiImageVo.fail();
			//格式正确
			String datePath=new SimpleDateFormat("YYYY/MM/dd/").format(new Date());
			//E:/image/yyyy/MM/dd/
			String fileDirPath=imagePathConfig.getFileDirPath();
			String fileLocalPath=fileDirPath+datePath;
			System.out.println(fileLocalPath);
			File dirFile=new File(fileLocalPath);
			if(!dirFile.exists())dirFile.mkdirs();
			//实现文件上传
			String uuid=UUID.randomUUID().toString().replace("-", "");
			String fileType=fileName.substring(fileName.lastIndexOf("."));//文件后缀
			String realFileName=uuid+fileType;//uuid文件名
			//上传文件
			uploadFile.transferTo(new File(fileLocalPath+realFileName));//E:/image/2020/12/15/uuid.png
			String url=imagePathConfig.getUrlPath()+datePath+realFileName;//文件路径
			System.out.println(url);
			return EasyUiImageVo.success(url, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EasyUiImageVo.fail();
		}
	}

}
