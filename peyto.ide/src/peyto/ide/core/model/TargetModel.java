package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TargetModel {

	private String path;
	private String packageName;
	private String name;
	private String extension;
	private String templateCategoryCode;
	private int templateRevision;

//	public TargetModel() {
//	}
//
//	public TargetModel(String path, String packageName, String name, String extension, String templateCategoryCode, int templateRevision) {
//		this.path = path;
//		this.packageName = packageName;
//		this.name = name;
//		this.extension = extension;
//		this.templateCategoryCode = templateCategoryCode;
//		this.templateRevision = templateRevision;
//	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTemplateCategoryCode() {
		return templateCategoryCode;
	}

	public void setTemplateCategoryCode(String templateCategoryCode) {
		this.templateCategoryCode = templateCategoryCode;
	}

	public int getTemplateRevision() {
		return templateRevision;
	}

	public void setTemplateRevision(int templateRevision) {
		this.templateRevision = templateRevision;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
