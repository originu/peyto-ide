package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TemplateModel {

	private long id;
	private long revision;

	public TemplateModel() {
	}

	public TemplateModel(long id, long revision) {
		super();
		this.id = id;
		this.revision = revision;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRevision() {
		return revision;
	}

	public void setRevision(long revision) {
		this.revision = revision;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
