package com.my.mall.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUiTableVo<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer total;
	private List<T> rows;
}
