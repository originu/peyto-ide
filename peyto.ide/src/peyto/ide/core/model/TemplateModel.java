package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TemplateModel {

	private String categoryCode;
	private int revision;

	public TemplateModel() {
	}

	public TemplateModel(String categoryCode, int revision) {
		super();
		this.categoryCode = categoryCode;
		this.revision = revision;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
