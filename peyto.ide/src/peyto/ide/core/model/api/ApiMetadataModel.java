package peyto.ide.core.model.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ApiMetadataModel {

	private String kind;
	private String application;
	private String messageChannel;

	public ApiMetadataModel() {
	}
	
	public ApiMetadataModel(String kind, String application, String messageChannel) {
		this.kind = kind;
		this.application = application;
		this.messageChannel = messageChannel;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getMessageChannel() {
		return messageChannel;
	}

	public void setMessageChannel(String messageChannel) {
		this.messageChannel = messageChannel;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}