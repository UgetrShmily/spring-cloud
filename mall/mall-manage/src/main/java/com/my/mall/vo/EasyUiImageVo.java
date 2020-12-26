package com.my.mall.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EasyUiImageVo implements Serializable{
	private static final long serialVersionUID = 8607435713177668162L;
	private Integer error; //0表示正常  1失败
	private String url;
	private Integer width;
	private Integer height;

	//1.失败方法
	public static EasyUiImageVo fail() {

		return new EasyUiImageVo(1, null, null, null);
	}

	//2.成功方法
	public static EasyUiImageVo success(String url,Integer width,Integer height) {

		return new EasyUiImageVo(0, url, width, height);
	}

}
