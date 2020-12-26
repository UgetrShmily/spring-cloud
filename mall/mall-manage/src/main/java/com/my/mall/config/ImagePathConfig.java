package com.my.mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
@Data
@Configuration
@PropertySource("classpath:/properties/ImagePath.properties")
public class ImagePathConfig {
	@Value("${image.fileDirPath}")
	private String fileDirPath;
	@Value("${image.urlPath}")
	private String urlPath;
}
