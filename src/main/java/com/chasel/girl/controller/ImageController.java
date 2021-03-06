package com.chasel.girl.controller;

import static com.chasel.girl.constant.MessagesConstant.ADD_FAIL;
import static com.chasel.girl.constant.MessagesConstant.ADD_SUCCESS;
import static com.chasel.girl.constant.MessagesConstant.DEL_FAIL;
import static com.chasel.girl.constant.MessagesConstant.DEL_SUCCESS;
import static com.chasel.girl.constant.MessagesConstant.QUERY_FAIL;
import static com.chasel.girl.constant.MessagesConstant.QUERY_SUCCESS;
import static com.chasel.girl.constant.MessagesConstant.UPDATE_FAIL;
import static com.chasel.girl.constant.MessagesConstant.UPDATE_SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chasel.girl.constant.BaseController;
import com.chasel.girl.constant.ResponseResult;
import com.chasel.girl.service.IImageService;
import com.chasel.girl.vo.Image;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("处理图片接口文档")
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

	@Autowired
	private IImageService imageService;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@ApiOperation("图片分页查询:type、name、creator、memo")
	@RequestMapping(path = "/list/page/{pageSize}/{pageNum}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PageInfo<?> listPage(@RequestBody Image image, PageInfo<Image> pageInfo) {

		return doQuery(() -> {return imageService.listPage(image, pageInfo);});
	}
	
	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@ApiOperation("图片分页查询:type、name、creator、memo")
	@RequestMapping(path = "/query/page/{pageSize}/{pageNum}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult imageListPage(@RequestBody Image image, PageInfo<Image> pageInfo) {

		return value(() -> {return imageService.listPage(image, pageInfo);},QUERY_SUCCESS, QUERY_FAIL);
	}

	/** 查询所有 **/
	@ApiOperation("查询所有图片")
	@RequestMapping(path = "/findAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseResult findAll() {

		return value(() -> {return imageService.findAll(new Image());}, QUERY_SUCCESS, QUERY_FAIL);
	}

	/**
	 * 添加照片
	 * 
	 * @param image
	 * @return
	 * @throws Exception
	 */
	@ApiOperation("上传图片")
	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult save(@RequestParam String type,@RequestParam String memo,@RequestParam MultipartFile file) {

		return process(() -> {imageService.save(file, type, memo);}, ADD_SUCCESS, ADD_FAIL);
	}

	/** 删除文件 **/
	@ApiOperation("删除图片")
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseResult delete(@PathVariable int id) {

		return process(() -> {imageService.delete(id);}, DEL_SUCCESS, DEL_FAIL);
	}

	/**
	 * 编辑照片
	 * 
	 * @param image
	 * @return
	 */
	@ApiOperation("修改图片名称,根据图片ID:id 修改图片类型:type和图片描述:memo")
	@RequestMapping(path = "/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseResult update(@RequestBody Image image) {

		return process(() -> {imageService.update(image);}, UPDATE_SUCCESS, UPDATE_FAIL);
	}

}
