package peyto.ide.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DBTableDto {

	private String tableCatalog;
	private String tableSchema;
	private String tableName;
	private String tableType;
	private String logicalTableName;

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

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getLocalTableName() {
		return logicalTableName;
	}

	public void setLogicalTableName(String logicalTableName) {
		this.logicalTableName = logicalTableName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}