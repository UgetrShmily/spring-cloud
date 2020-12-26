package com.my.mall.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUiTreeVo implements Serializable{
	private static final long serialVersionUID = 6326619259967048854L;
	private Long id;
	private String text;//节点名称
	private String state;//节点打开状态open|close
}
