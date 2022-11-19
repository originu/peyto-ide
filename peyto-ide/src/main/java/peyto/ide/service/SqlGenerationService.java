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
import peyto.ide.data.TableData;

@Service
public class SqlGenerationService {

	@Autowired
	private DBCatalogDao dbCatalogDao;
	
	@Autowired
	private FreemarkerTemplateService templateService;
	
	public byte[] generateByTableName(TableData tableData) {
		DBTableDto table = dbCatalogDao.getTable(tableData.getTableCatalog(),
				tableData.getTableSchema(),
				tableData.getTableName());

		
		List<DBColumnDto> columns = dbCatalogDao.getColumns(
				tableData.getTableCatalog(),
				tableData.getTableSchema(),
				tableData.getTableName()
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
		dataModel.put("namespace", tableData.getNamespace());
		dataModel.put("selectId", tableData.getSelectId());
		dataModel.put("selectResultType", tableData.getSelectResultType());
		dataModel.put("tableName", table.getTableName());
		dataModel.put("tableColumns", columns);

		try {
			ByteArrayOutputStream	bos = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(bos);
			templateService.generate("mybatis/basic_mapper.ftlh", dataModel, out);
			return bos.toByteArray();
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