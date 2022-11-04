package peyto.ide.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DBSchemaDto {

	private String catalogName;
	private String schemaName;
	private String schemaOwner;

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getSchemaOwner() {
		return schemaOwner;
	}

	public void setSchemaOwner(String schemaOwner) {
		this.schemaOwner = schemaOwner;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}