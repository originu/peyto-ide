package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProjectModel {

	private String name;

	public ProjectModel() {
	}

	public ProjectModel(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
