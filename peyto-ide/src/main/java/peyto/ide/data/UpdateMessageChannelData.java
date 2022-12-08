package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateMessageChannelData {

	private long messageChannelId;
	private long applicatoinId;
	private String messageChannelName;
	private String messageChannelDescription;

	public long getMessageChannelId() {
		return messageChannelId;
	}

	public void setMessageChannelId(long messageChannelId) {
		this.messageChannelId = messageChannelId;
	}
	
	public long getApplicatoinId() {
		return applicatoinId;
	}

	public void setApplicatoinId(long applicatoinId) {
		this.applicatoinId = applicatoinId;
	}

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
