package peyto.ide.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;
import peyto.ide.dao.api.DBCatalogDao;
import peyto.ide.dto.DBColumnDto;
import peyto.ide.dto.DBTableDto;
import peyto.ide.data.DaoData;
import peyto.ide.data.DtoData;

@Service
public class DaoGenerationService {

	@Autowired
	private FreemarkerTemplateService templateService;
	
	@Autowired
	private DBCatalogDao dbCatalogDao;
	
	public byte[] generateDao(DaoData daoData) {
		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("packageName", daoData.getPackageName());
		dataModel.put("className", daoData.getClassName());
		dataModel.put("returnType", daoData.getReturnType());
		dataModel.put("selectId", daoData.getSelectId());
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
	
	public byte[] generateDaoTest(DaoData daoData) {
		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("packageName", daoData.getPackageName());
		dataModel.put("className", daoData.getClassName());
		dataModel.put("returnType", daoData.getReturnType());
		dataModel.put("selectId", daoData.getSelectId());
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

	public byte[] generateDto(DtoData dtoData) {
		DBTableDto table = dbCatalogDao.getTable(dtoData.getTableCatalog(),
				dtoData.getTableSchema(),
				dtoData.getTableName());

		List<DBColumnDto> columns = dbCatalogDao.getColumns(
				dtoData.getTableCatalog(),
				dtoData.getTableSchema(),
				dtoData.getTableName()
				);
	
		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("packageName", dtoData.getPackageName());
		dataModel.put("className", table.getLogicalTableName());
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
