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
import peyto.ide.dto.TableInfo;

@Service
public class SqlGenerationService {

	@Autowired
	private DBCatalogDao dbCatalogDao;
	
	@Autowired
	private FreemarkerTemplateService templateService;
	
	public String generateByTableName(TableInfo tableInfo) {
		
		DBTableDto table = dbCatalogDao.getTable(tableInfo.getTableCatalog(), 
				tableInfo.getTableSchema(), 
				tableInfo.getTableName());

		
		List<DBColumnDto> columns = dbCatalogDao.getColumns(
				tableInfo.getTableCatalog(), 
				tableInfo.getTableSchema(), 
				tableInfo.getTableName()
				);
		
		ToIntBiFunction<String, String> function = String::compareTo;
		DBColumnDto maxColumn = Collections.max(
				columns,
				(o1, o2) -> function.applyAsInt(o1.getColumnName(), o2.getColumnName())
				);
		
		// add padding
		int padding = maxColumn.getColumnName().length() + 4;
		columns.forEach( (item) -> {
			item.setColumnName(StringUtils.rightPad(item.getColumnName(), padding));
		});

		HashMap<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("namespace", tableInfo.getNamespace());
		dataModel.put("selectId", tableInfo.getSelectId());
		dataModel.put("selectResultType", tableInfo.getSelectResultType());
		dataModel.put("tableName", table.getTableName());
		dataModel.put("tableColumns", columns);

		try {
			ByteArrayOutputStream	bos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(bos);
			templateService.generate(dataModel, out);
			return new String(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		// remove padding
		columns.forEach( (item) -> {
			item.setColumnName(item.getColumnName().trim());
		});
		
		return null;
	}
	
}