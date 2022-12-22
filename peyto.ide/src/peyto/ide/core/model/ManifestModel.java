package peyto.ide.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ManifestModel {

	private String formatVersion;
	private MetadataModel metadata;

	public ManifestModel() {
	}

	public ManifestModel(String formatVersion, MetadataModel metadata) {
		this.formatVersion = formatVersion;
		this.metadata = metadata;
	}

	public String getFormatVersion() {
		return formatVersion;
	}

	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
	}

	public MetadataModel getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataModel metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
