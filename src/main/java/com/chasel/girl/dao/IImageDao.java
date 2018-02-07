package com.chasel.girl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.chasel.girl.vo.Image;

@Mapper
public interface IImageDao extends IBaseDao<Image> {
	
	public List<Map<String, Object>> getType();
}
