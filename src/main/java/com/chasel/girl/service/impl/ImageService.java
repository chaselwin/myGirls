package com.chasel.girl.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chasel.girl.constant.CodeConstants;
import com.chasel.girl.dao.IImageDao;
import com.chasel.girl.exception.DuplicateRecordException;
import com.chasel.girl.service.IImageService;
import com.chasel.girl.service.IMinioService;
import com.chasel.girl.util.ResourceUtil;
import com.chasel.girl.vo.Image;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ImageService implements IImageService {

	private static final String MSG_ID_NULL = "该ID不存在";
	private static final String MSG_IMAGE_NULL = "图片信息不能为空";
	public static final String FILE_NULL = "请选择文件";
	public static final String FILE_SIZE = "请选择小于10M图片";
	public static final String UPLOAD_ERROR = "上传图片出错";
	@Autowired
	private IImageDao imageDao;

	@Autowired
	private IMinioService minioService;
	
	@Override
	public void save(MultipartFile file, String typeId,String memo,double width, double height,double textHeight)throws DuplicateRecordException {
		if (file == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, FILE_NULL);
		}
		if (file.getSize() >= 10485000) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, FILE_SIZE);
		} else {
			String fileType=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String fileName=UUID.randomUUID().toString()+fileType.replaceAll("", "-");
			String objectName=typeId+"/"+fileName;
			boolean bool = minioService.upload(file,objectName);
			if (bool) {
				Image image = new Image();
				image.setObjectName(objectName);
				image.setName(file.getOriginalFilename());
				image.setWidth(width);
				image.setHeight(height);
				image.setType(typeId);
				image.setMemo(memo);
				image.setTextHeight(textHeight);
				image.setCreator(ResourceUtil.getSessionName());
				image.setCreateTime(new Timestamp(System.currentTimeMillis()));
				image.setUpdator(ResourceUtil.getSessionName());
				image.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				imageDao.save(image);
			}else{
				throw new DuplicateRecordException(UPLOAD_ERROR);
			}
		}
	}

	@Override
	public void delete(int id) throws DuplicateRecordException {
		Image image = findById(id);
		if (image == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, MSG_ID_NULL);
		} else {
			minioService.remove(image.getObjectName());
			imageDao.delete(id);
		}
	}

	@Override
	public void update(Image image) throws DuplicateRecordException {
		Image img = findById(image.getId());
		if (img == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, MSG_ID_NULL);
		} else {
			image.setCreateTime(img.getCreateTime());
			image.setUpdator(ResourceUtil.getSessionName());
			image.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			imageDao.update(image);
		}

	}

	@Override
	public List<Image> findAll(Image imagev) throws DuplicateRecordException {
		List<Image> list = imageDao.findAll(imagev);
		List<Image> imageList = new ArrayList<Image>();
		for (Image image : list) {
			String imageName = image.getObjectName();
			image.setUrl(minioService.getUrl(imageName));
			imageList.add(image);
		}
		return imageList;
	}

	@Override
	public Image findById(int id) throws DuplicateRecordException {
		return imageDao.findById(id);
	}

	@Override
	public PageInfo<Image> listPage(Image image, PageInfo<Image> pageInfo) throws DuplicateRecordException {
		if (image == null || pageInfo == null) {
			throw new DuplicateRecordException(CodeConstants.ERR_CODE_99, MSG_IMAGE_NULL);
		}
		List<Image> list = new ArrayList<>();
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		list = imageDao.findAll(image);
		PageInfo<Image> pageInfo2 = new PageInfo<Image>(list);
		List<Image> imageList = new ArrayList<Image>();
		for (Image img : list) {
			String imageName = img.getObjectName();
			img.setUrl(minioService.getUrl(imageName));
			imageList.add(img);
		}
		pageInfo2.setList(imageList);
		return pageInfo2;
	}

	@Override
	public List<Map<String, Object>> getMusic(){
		List<Map<String, Object>> list = new ArrayList<>();
		String luckyUrl = minioService.getUrl("/static/lucky.mp3");
		String lightUrl = minioService.getUrl("/static/light.mp3");
		String summerLoveUrl = minioService.getUrl("/static/summerLove.mp3");
		for(int i=0;i<3;i++){
			Map<String, Object> map=new HashMap<>();
			map.put("id", i+1);
			if (i==0) {
				map.put("cover", "../images/indexMusic1.jpg");
				map.put("path", luckyUrl);
			}
			else if (i==1) {
				map.put("cover", "../images/indexMusic2.png");
				map.put("path", lightUrl);
			}
			else if (i==2) {
				map.put("cover", "../images/indexMusic3.png");
				map.put("path", summerLoveUrl);
			}
			else{
				continue;
			}
			list.add(map);
		}
		return list;
	}

}
