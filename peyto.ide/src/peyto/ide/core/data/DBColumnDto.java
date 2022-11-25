package peyto.ide.core.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DBColumnDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7794218674579393781L;
	
	private String tableCatalog;
	private String tableSchema;
	private String tableName;
	private String columnName;
	private String ordinalPosition;
	private String columnDefault;
	private String isNullable;
	private String dataType;
	private String characterMaximumLength;
	private String numericPrecision;
	private String numericPrecisionRadix;
	private String numericScale;
	private String datetimePrecision;
	private int pkOrder;

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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getCharacterMaximumLength() {
		return characterMaximumLength;
	}

	public void setCharacterMaximumLength(String characterMaximumLength) {
		this.characterMaximumLength = characterMaximumLength;
	}

	public String getNumericPrecision() {
		return numericPrecision;
	}

	public void setNumericPrecision(String numericPrecision) {
		this.numericPrecision = numericPrecision;
	}

	public String getNumericPrecisionRadix() {
		return numericPrecisionRadix;
	}

	public void setNumericPrecisionRadix(String numericPrecisionRadix) {
		this.numericPrecisionRadix = numericPrecisionRadix;
	}

	public String getNumericScale() {
		return numericScale;
	}

	public void setNumericScale(String numericScale) {
		this.numericScale = numericScale;
	}

	public String getDatetimePrecision() {
		return datetimePrecision;
	}

	public void setDatetimePrecision(String datetimePrecision) {
		this.datetimePrecision = datetimePrecision;
	}

	public int getPkOrder() {
		return pkOrder;
	}

	public void setPkOrder(int pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}