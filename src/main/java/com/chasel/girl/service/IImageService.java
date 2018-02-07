package com.chasel.girl.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.chasel.girl.exception.DuplicateRecordException;
import com.chasel.girl.vo.Image;
import com.github.pagehelper.PageInfo;

public interface IImageService extends IBaseService<Image> {

	public void save(MultipartFile file, String typeId, String memo, double width, double height, double textHeight)throws DuplicateRecordException;

	public void delete(int id) throws DuplicateRecordException;

	public void update(Image image) throws DuplicateRecordException;

	public List<Image> findAll(Image image) throws DuplicateRecordException;

	public Image findById(int id) throws DuplicateRecordException;

	PageInfo<Image> listPage(Image image, PageInfo<Image> pageInfo) throws DuplicateRecordException;

	public List<Map<String, Object>> getMusic();

}
