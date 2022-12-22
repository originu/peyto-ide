package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class IDData {

	private long id;

	public IDData() {
	}

	public IDData(long id) {
		this.id = id;
	}

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
