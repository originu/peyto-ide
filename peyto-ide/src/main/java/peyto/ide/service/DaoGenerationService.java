package peyto.ide.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;
import peyto.ide.dto.DaoInfo;

@Service
public class DaoGenerationService {

	@Autowired
	private FreemarkerTemplateService templateService;
	
	public byte[] generateDao(DaoInfo daoInfo) {
		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("packageName", daoInfo.getPackageName());
		dataModel.put("className", daoInfo.getClassName());
		dataModel.put("returnType", daoInfo.getReturnType());
		dataModel.put("selectId", daoInfo.getSelectId());
		try {
			ByteArrayOutputStream	bos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(bos);
			templateService.generate("dao/dao_api.ftlh", dataModel, out);
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] generateDaoTest(DaoInfo daoInfo) {
		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("packageName", daoInfo.getPackageName());
		dataModel.put("className", daoInfo.getClassName());
		dataModel.put("returnType", daoInfo.getReturnType());
		dataModel.put("selectId", daoInfo.getSelectId());
		try {
			ByteArrayOutputStream	bos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(bos);
			templateService.generate("dao/dao_test.ftlh", dataModel, out);
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
