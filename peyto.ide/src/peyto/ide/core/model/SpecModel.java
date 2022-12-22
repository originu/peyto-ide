package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SpecModel {

	private ProjectModel project;
	private TargetModel target;
	private TemplateModel template;

	public SpecModel() {
	}

	public SpecModel(ProjectModel project, TargetModel target, TemplateModel template) {
		super();
		this.project = project;
		this.target = target;
		this.template = template;
	}

	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	public TargetModel getTarget() {
		return target;
	}

	public void setTarget(TargetModel target) {
		this.target = target;
	}

	public TemplateModel getTemplate() {
		return template;
	}

	public void setTemplate(TemplateModel template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
