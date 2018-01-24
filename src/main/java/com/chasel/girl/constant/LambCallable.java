package com.chasel.girl.constant;

import com.chasel.girl.exception.DuplicateRecordException;

/**
 * Lambda表达式，没有返回值
 * 
 * @author chasel
 *
 */
public interface LambCallable {

	Object run() throws DuplicateRecordException;

}
