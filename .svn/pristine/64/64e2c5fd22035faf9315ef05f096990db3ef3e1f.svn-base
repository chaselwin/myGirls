package com.chasel.girl.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.chasel.girl.exception.DuplicateRecordException;
import com.chasel.girl.service.IExportBaseService;
import com.chasel.girl.util.IoUtils;

@Service
public class ExportBaseService implements IExportBaseService {

	@Override
	public void downloadFile(byte[] bytes, String fileName) throws DuplicateRecordException {
		try {
			IoUtils.downloadFile(bytes, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void downloadFile(HSSFWorkbook workBook, String fileName) throws DuplicateRecordException {
		try {
			IoUtils.downloadFile(workBook, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
