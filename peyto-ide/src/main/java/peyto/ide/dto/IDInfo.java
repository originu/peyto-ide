package peyto.ide.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class IDInfo {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
