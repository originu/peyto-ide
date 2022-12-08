package peyto.ide.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateMessageData {
    private long messageId;
    private long applicationId;
    private long messageChannelId;
    private String messageName;
    private String messageDescription;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

	public long getMessageChannelId() {
		return messageChannelId;
	}

	public void setMessageChannelId(long messageChannelId) {
		this.messageChannelId = messageChannelId;
	}

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
