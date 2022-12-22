package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ItemModel {

	private String name;
	private SpecModel spec;

	public ItemModel() {
	}

	public ItemModel(String name, SpecModel spec) {
		super();
		this.name = name;
		this.spec = spec;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpecModel getSpec() {
		return spec;
	}

	public void setSpec(SpecModel spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
