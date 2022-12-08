package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddMessageChannelData {

	private String messageChannelName;
	private String messageChannelDescription;

	public String getMessageChannelName() {
		return messageChannelName;
	}

	public void setMessageChannelName(String messageChannelName) {
		this.messageChannelName = messageChannelName;
	}

	public String getMessageChannelDescription() {
		return messageChannelDescription;
	}

	public void setMessageChannelDescription(String messageChannelDescription) {
		this.messageChannelDescription = messageChannelDescription;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
