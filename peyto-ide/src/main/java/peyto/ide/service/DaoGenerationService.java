package peyto.ide.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToIntBiFunction;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;
import peyto.ide.dao.api.DBCatalogDao;
import peyto.ide.dto.DBColumnDto;
import peyto.ide.dto.DBTableDto;
import peyto.ide.dto.DaoInfo;
import peyto.ide.dto.DtoInfo;

@Service
public class DaoGenerationService {

	@Autowired
	private FreemarkerTemplateService templateService;
	
	@Autowired
	private DBCatalogDao dbCatalogDao;
	
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

	public byte[] generateDto(DtoInfo dtoInfo) {
		DBTableDto table = dbCatalogDao.getTable(dtoInfo.getTableCatalog(), 
				dtoInfo.getTableSchema(), 
				dtoInfo.getTableName());

		List<DBColumnDto> columns = dbCatalogDao.getColumns(
				dtoInfo.getTableCatalog(), 
				dtoInfo.getTableSchema(), 
				dtoInfo.getTableName()
				);
	
		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("packageName", dtoInfo.getPackageName());
		dataModel.put("className", table.getLocalTableName());
		dataModel.put("tableColumns", columns);

		try {
			ByteArrayOutputStream	bos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(bos);
			templateService.generate("dao/dto.ftlh", dataModel, out);
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
