package com.chasel.girl.constant;

import org.apache.log4j.Logger;
import static com.chasel.girl.constant.MessagesConstant.NOT_LOGIN;
import com.chasel.girl.exception.DuplicateRecordException;
import com.chasel.girl.util.I18NSupport;
import com.chasel.girl.util.ResourceUtil;
import com.github.pagehelper.PageInfo;

/**
 * 基础Controller
 * 
 * @author chasel
 *
 */
public abstract class BaseController extends I18NSupport {

	private Logger log = Logger.getLogger(BaseController.class);

	/**
	 * 分页
	 * 
	 * @param run
	 * @return
	 */
	protected PageInfo<?> doQuery(LambCallable run) {
		PageInfo<?> pageInfo = new PageInfo<>();
		try {
			log.info("-----Welcome to doQuery-----");
			pageInfo = (PageInfo<?>) run.run();
		} catch (DuplicateRecordException e) {
			log.error("doQuery has an error: {} {}" + e.getErrCode() + "------and------" + e.getErrMsg());

		} catch (Exception e) {
			log.error("doQuery has an error: {}" + e.getMessage());

		}
		return pageInfo;
	}

	/**
	 * 无返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult process(LamCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			log.info("-----Welcome to process-----");
			boolean bool = ResourceUtil.getSession();
			if (!bool) {
				return new ResponseResult(ResponseStatus.NOT_LOGIN.getCode(), getMassage(NOT_LOGIN));
			}else{
				run.run();
				return new ResponseResult(ResponseStatus.SUCCESS.getCode(), getMassage(successMsg));
			}
		} catch (DuplicateRecordException e) {
			log.error("process has an error: {} {}" + e.getErrCode() + "------and-------" + e.getErrMsg());
			responseMsg = (String) e.getErrMsg();

		} catch (Exception e) {
			log.error("process has an error: {}" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL.getCode(), getMassage(responseMsg));
	}

	/**
	 * 有返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult value(LambCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			log.info("-----Welcome to value-----");
			boolean bool = ResourceUtil.getSession();
			if (!bool) {
				return new ResponseResult(ResponseStatus.NOT_LOGIN.getCode(), getMassage(NOT_LOGIN));
			}else{
				Object obj = run.run();
				return new ResponseResult(ResponseStatus.SUCCESS.getCode(), getMassage(successMsg), obj);
			}
		} catch (DuplicateRecordException e) {
			responseMsg = (String) e.getErrMsg();
			log.error("value has an error: {}" + e.getErrCode() + "------and-------" + e.getErrMsg());
		} catch (Exception e) {
			log.error("value has an error: {}" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL.getCode(), getMassage(responseMsg), null);
	}
	
	/**
	 * 无返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult processz(LamCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			log.info("-----Welcome to processz-----");
			run.run();
			return new ResponseResult(ResponseStatus.SUCCESS.getCode(), getMassage(successMsg));

		} catch (DuplicateRecordException e) {
			log.error("process has an error: {} {}" + e.getErrCode() + "------and-------" + e.getErrMsg());
			responseMsg = (String) e.getErrMsg();

		} catch (Exception e) {
			log.error("process has an error: {}" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL.getCode(), getMassage(responseMsg));
	}

	/**
	 * 有返回值
	 * 
	 * @param run
	 * @param successMsg
	 * @param failMsg
	 * @return
	 */
	protected ResponseResult valuez(LambCallable run, String successMsg, String failMsg) {
		String responseMsg = "";
		try {
			log.info("-----Welcome to valuez-----");
			Object obj = run.run();
			return new ResponseResult(ResponseStatus.SUCCESS.getCode(), getMassage(successMsg), obj);
		} catch (DuplicateRecordException e) {
			responseMsg = (String) e.getErrMsg();
			log.error("value has an error: {}" + e.getErrCode() + "------and-------" + e.getErrMsg());
		} catch (Exception e) {
			log.error("value has an error: {}" + e.getMessage());
			responseMsg = failMsg;
		}
		return new ResponseResult(ResponseStatus.FAIL.getCode(), getMassage(responseMsg), null);
	}
}
