package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TableData {

	private String tableCatalog;
	private String tableSchema;
	private String tableName;

	private String namespace;
	private String selectId;
	private String selectResultType;

	public String getTableCatalog() {
		return tableCatalog;
	}

	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public String getSelectResultType() {
		return selectResultType;
	}

	public void setSelectResultType(String selectResultType) {
		this.selectResultType = selectResultType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}