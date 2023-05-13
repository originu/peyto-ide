package peyto.ide.core.model.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiModel {

	private String formatVersion;
	private ApiMetadataModel metadata;
	
	public ApiModel() {
	}
	
	public ApiModel(String formatVersion, ApiMetadataModel metadata) {
		super();
		this.formatVersion = formatVersion;
		this.metadata = metadata;
	}

	public String getFormatVersion() {
		return formatVersion;
	}

	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
	}

	public ApiMetadataModel getMetadata() {
		return metadata;
	}

	public void setMetadata(ApiMetadataModel metadata) {
		this.metadata = metadata;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
